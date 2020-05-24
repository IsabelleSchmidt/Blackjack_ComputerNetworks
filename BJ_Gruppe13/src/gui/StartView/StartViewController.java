package gui.StartView;

import gui.Scenes;
import gui.ViewController.ViewController;

import java.io.IOException;

import application.Main;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class StartViewController extends ViewController<Main> {

	Button start;
	Button exit;
	Label title;
	Label errorLabel;
	
	Pane background;
	
	StartView view;
	
	public StartViewController(Main application) {
		
		super(application);
		
		rootView = new StartView();
		StartView view = (StartView) rootView;
		
		start = view.start;
		exit = view.exit;
		title = view.title;
		errorLabel = view.errorLabel;
		
		initialize();
		
	}
	
	@Override
	public void initialize() {
		start.setOnAction(event -> {
			try {
				application.connectToServer();
				errorLabel.setText("");
				application.startGame(); 
				application.switchScene(Scenes.GAME_VIEW);
			} catch (IOException e) {
				errorLabel.setText("NO CONNECTION TO SERVER. PLEASE RUN THE SERVER AND TRY AGAIN.");
			}
		});
		
		exit.setOnAction(event -> application.stop());
	}

}
