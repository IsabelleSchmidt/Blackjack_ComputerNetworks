package gui.GameView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameView extends BorderPane {
	
	BorderPane game;
	Button stay;
	Button give;	
	Button menu;
	
	Label blackjack;
	Label score;
	Label letzteKarte;
	Label gameResult;
	Label dielerScore;
	Label errorLabel;
	
	StackPane topBox;
	GridPane centerBox;
	StackPane bottomBox;
	
	HBox buttons;
	HBox title;
	HBox gameInfoBox;
	VBox errorBox;
	HBox header;
	
	Image img = new Image(getClass().getResource("../../ressources/image/DealerinPNG.png").toExternalForm());
	ImageView imgview;
	
	
	public GameView() {
		
		game = new BorderPane();
		
		topBox = new StackPane();
		topBox.setId("stack");
		
		centerBox = new GridPane();
		centerBox.setId("stack2");
		
		bottomBox = new StackPane();
		bottomBox.setId("stack3");
		
		buttons = new HBox();
		buttons.setPrefSize(150, 150);
		
		title = new HBox();
		gameInfoBox = new HBox();
		header = new HBox();
		errorBox = new VBox();
		
		imgview = new ImageView(img);
		imgview.setId("dealerin");
		imgview.setFitHeight(600.0);
		imgview.setFitWidth(450.0);
		
		blackjack = new Label();
		blackjack.setId("blackjack");
		blackjack.setText("BLACKJACK");
		blackjack.setAlignment(Pos.TOP_CENTER);
		blackjack.setPadding(new Insets(30));
		
		score = new Label();
		score.setId("score");
		score.setText("SCORE: ");
		score.setAlignment(Pos.CENTER_RIGHT);
		score.setPadding(new Insets(40));
		
		letzteKarte = new Label("Ihre letzte Karte: ");
		letzteKarte.setId("karte");
		letzteKarte.setAlignment(Pos.CENTER);
		HBox.setMargin(letzteKarte,new Insets(20,20,40,20));
		
		gameResult = new Label();
		gameResult.setId("karte");
		gameResult.setAlignment(Pos.CENTER);
		HBox.setMargin(gameResult,new Insets(20,20,40,20));
		
		dielerScore = new Label();
		dielerScore.setId("karte");
		dielerScore.setAlignment(Pos.CENTER);
		HBox.setMargin(dielerScore,new Insets(20,20,40,20));
		
		
		gameInfoBox.getChildren().addAll(letzteKarte, gameResult, dielerScore);
		
		errorLabel = new Label();
		errorLabel.setId("error-label");
		VBox.setMargin(errorLabel,new Insets(20,20,40,20));
		
		errorBox.getChildren().add(errorLabel);
		errorBox.setAlignment(Pos.BOTTOM_CENTER);
		
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
		
		header.getChildren().addAll(title, score);
		title.getChildren().addAll(blackjack);
		title.setAlignment(Pos.CENTER);
		header.setAlignment(Pos.TOP_CENTER);
		
		buttons.getChildren().addAll(give, stay, menu);
		buttons.setAlignment(Pos.BOTTOM_CENTER);
		
		topBox.getChildren().addAll(header);
		centerBox.getChildren().addAll(gameInfoBox, errorBox, imgview);
		bottomBox.getChildren().addAll(buttons);
		
		centerBox.setAlignment(Pos.BOTTOM_CENTER);
		
		this.setStyle("-fx-background-color: #006400");
		this.setPadding(new Insets(50));	
		this.setTop(topBox);
		this.setCenter(centerBox);
		this.setBottom(bottomBox);
			
	}

}
