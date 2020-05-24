package gui.GameView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameView extends VBox {
	
	Button stay;
	Button give;	
	Button menu;

	Label score;
	Label letzteKarte;
	Label gameResult;
	Label dielerScore;
	Label errorLabel;
	
	Image img = new Image(getClass().getResource("../../ressources/image/DealerinPNG.png").toExternalForm());
	
	public GameView() {
		//TOP
		HBox header = new HBox();
		Label blackjack = new Label();
		blackjack.setId("blackjack");
		blackjack.setText("BLACKJACK");
		blackjack.setPadding(new Insets(30));
		
		HBox title = new HBox();
		title.setAlignment(Pos.TOP_CENTER);
		title.getChildren().addAll(blackjack);
		title.setPrefWidth(300);
		
		HBox scoreBox = new HBox();
		score = new Label();
		score.setId("score");
		score.setText("SCORE: ");
		
		scoreBox.getChildren().add(score);
		HBox.setMargin(score, new Insets(50));
		scoreBox.setAlignment(Pos.CENTER_LEFT);
		scoreBox.setPrefWidth(300);
		
		header.setAlignment(Pos.TOP_CENTER);
		header.getChildren().addAll(title, scoreBox);
		
		//INFO BOX
		HBox gameInfoBox = new HBox();
		
		HBox kartenBox = new HBox();
		letzteKarte = new Label("Ihre letzte Karte: ");
		letzteKarte.setId("karte");
		kartenBox.getChildren().add(letzteKarte);
		kartenBox.setPrefWidth(200);
		kartenBox.setAlignment(Pos.CENTER);
		HBox.setMargin(letzteKarte,new Insets(20));
		
		HBox resultBox = new HBox();
		gameResult = new Label();
		gameResult.setId("karte");
		resultBox.getChildren().add(gameResult);
		resultBox.setPrefWidth(150);
		resultBox.setAlignment(Pos.CENTER);
		HBox.setMargin(gameResult,new Insets(20));
		
		HBox dielerBox = new HBox();
		dielerScore = new Label();
		dielerScore.setId("karte");
		dielerBox.getChildren().add(dielerScore);
		dielerBox.setPrefWidth(200);
		dielerBox.setAlignment(Pos.CENTER);
		HBox.setMargin(dielerScore,new Insets(20));
		
		gameInfoBox.setAlignment(Pos.CENTER);
		gameInfoBox.getChildren().addAll(kartenBox, resultBox, dielerBox);
		
		//CENTER
		VBox forImg = new VBox();
		ImageView imgview = new ImageView(img);
		imgview.setFitHeight(250);
		imgview.setFitWidth(250);
		forImg.getChildren().add(imgview);
		forImg.setAlignment(Pos.CENTER);
		VBox.setMargin(imgview, new Insets(40));
		
		
		//ERROR BOX
		HBox errorBox = new HBox();
		errorLabel = new Label();
		errorLabel.setId("error-label");
		VBox.setMargin(errorLabel,new Insets(20,20,40,20));
		
		errorBox.setAlignment(Pos.CENTER);
		errorBox.getChildren().add(errorLabel);
		errorBox.setPrefHeight(50);
		HBox.setMargin(errorLabel, new Insets(20));
		
		//BOTTOM
		HBox buttons = new HBox();
		buttons.setPrefSize(150, 150);
		
		stay = new Button();
		stay.setText("STAY");
		stay.setId("stay");
		stay.setAlignment(Pos.CENTER);
		HBox.setMargin(stay,new Insets(20,20,40,20));
		
		give = new Button();
		give.setText("HIT");
		give.setId("give");
		give.setAlignment(Pos.CENTER);
		HBox.setMargin(give,new Insets(20,20,40,20));
		
		menu = new Button();
		menu.setText("MENU");
		menu.setId("aufdecken");
		menu.setAlignment(Pos.CENTER);
		menu.setDisable(true);
		HBox.setMargin(menu,new Insets(20,20,40,20));
		
		buttons.getChildren().addAll(give, stay, menu);
		buttons.setAlignment(Pos.BOTTOM_CENTER);
		
		
		
		this.setStyle("-fx-background-color: #006400");
		this.getChildren().addAll(header, gameInfoBox, forImg, errorBox, buttons);
	}

}
