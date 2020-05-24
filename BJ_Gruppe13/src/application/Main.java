package application;

import java.io.IOException;
import java.util.HashMap;

import client_server.Client;
import gui.GameView.GameViewController;
import gui.StartView.StartViewController;
import gui.ViewController.ViewController;
import gui.Scenes;
import game.GameState;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * 
 * @author Oksana Bystrova, Marie Ernst, Ann-Cathrin Fabian, Isabell Schmidt
 *
 */
public class Main extends Application {
	Scene scene;
	Pane currentScene;
	HashMap<Scenes, Pane> scenes;
	
	Client client;
	GameState gameState;
	
	@Override
	public void init() {		
		//Erzeuge Client
		client = new Client("127.0.0.1", 28888);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			ViewController<Main> controller;
			scenes = new HashMap<>();
			
			controller = new StartViewController(this);
			scenes.put(Scenes.START_VIEW,controller.getRootView());
			
			Pane root = scenes.get(Scenes.START_VIEW);
			
			scene = new Scene(root, 1000, 1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			       stop();
			    }
			});
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("BlackJack");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createGameView() {
		if (scenes.containsKey(Scenes.GAME_VIEW)) {
			scenes.remove(Scenes.GAME_VIEW);
		}
		ViewController<Main> controller = new GameViewController(this, gameState);
		scenes.put(Scenes.GAME_VIEW, controller.getRootView());
	}
	
	public void connectToServer() throws IOException {
		//Verbindung zum Server
		client.initialize();
	}
	
	public void startGame() {
		//Neues Spiel erstellen
		gameState = new GameState();
		gameState.setGameService(client);
		
		createGameView();
	}
	
	
	public void switchScene(Scenes sceneName) {
		Pane nextScene;

		if (scenes.containsKey(sceneName)) {
			nextScene = scenes.get(sceneName);
			scene.setRoot(nextScene);
			currentScene = nextScene;
		}
	}
	
	public HashMap<Scenes, Pane> getScenes() {
		return scenes;
	}
	
	
	public Client getClient() {
		return client;
	}

	@Override
	public void stop() {
		try {
			client.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Platform.exit();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
