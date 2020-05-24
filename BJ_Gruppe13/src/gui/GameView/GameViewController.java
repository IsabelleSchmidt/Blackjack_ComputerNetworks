package gui.GameView;


import java.io.IOException;

import application.Main;
import client_server.Client;
import game.GameState;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import gui.Scenes;
import gui.ViewController.ViewController;

public class GameViewController extends ViewController<Main> {

	Button stay;
	Button give;
	Button newGame;
	
	Label blackjack;
	Label score;
	Label letzteKarte;
	Label gameResult;
	Label dielerScore;
	Label errorLabel;
	
	Client gameService;
	GameState game;
	GameView view;
	Main application;
	
	
	public GameViewController(Main application, GameState game) {
		super(application);
		this.application = application;
		this.game = game;
		
		rootView = new GameView();
		view = (GameView) rootView;
		
		this.gameService = game.client;
		
		stay = view.stay;
		give = view.give;
		newGame = view.menu;
		
		blackjack = view.blackjack;
		score = view.score;
		letzteKarte = view.letzteKarte;
		gameResult = view.gameResult;
		dielerScore = view.dielerScore;
		errorLabel = view.errorLabel;

		initialize();
		
	}

	@Override
	public void initialize() {
		give.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				Platform.runLater(new Runnable() {
		            
					@Override public void run() {
	                	try {
							gameService.getCard();
						} catch (IOException e) {
							errorLabel.setText("NO CONNECTION TO SERVER. PLEASE TRY AGAIN LATER.");
		                	newGame.setDisable(false);
							give.setDisable(true);
							stay.setDisable(true);
						}
		            }
					
		        });
			}
			
		});
		
		stay.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				Platform.runLater(new Runnable() {
					
		            @Override public void run() {
	            		try {
							gameService.openCards();
						} catch (IOException e) {
							errorLabel.setText("NO CONNECTION TO SERVER. PLEASE TRY AGAIN LATER.");
		            		newGame.setDisable(false);
							give.setDisable(true);
							stay.setDisable(true);
						}
		            }
		        });
			}
			
		});
		
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				Platform.runLater(new Runnable() {
		            @Override public void run() {		               
		                newGame.setDisable(true);
		                application.switchScene(Scenes.START_VIEW);
		            }
		        });
			}
			
		});
		
		game.playerScore.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(() -> score.setText("SCORE: " + newValue));
			}
			
		});
		
		game.letzteKarte.addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				Platform.runLater(() -> letzteKarte.setText("Ihre letzte Karte: " + newValue));
			}
			
		});
		
		game.running.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				Platform.runLater(new Runnable() {
					
		            @Override 
		            public void run() {
		            	if (newValue == false) {
		            		gameResult.setText(game.getGameResult().toString());
							dielerScore.setText("Dieler Score: " + game.getDielerScore()); 
							
							newGame.setDisable(false);
							give.setDisable(true);
							stay.setDisable(true);
		            	}
		            }
		            
		        });
				
			}
			
		});
		
	}

}
