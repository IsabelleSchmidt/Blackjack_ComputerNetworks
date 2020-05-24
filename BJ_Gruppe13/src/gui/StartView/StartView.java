package gui.StartView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StartView extends VBox {

	Button start;
	Button exit;
	Label title;
	Label errorLabel;
	
	VBox bottomBox;
	VBox errorBox;
	HBox buttonsstart;
	VBox headerstart;
	VBox middle;
	
	Image img = new Image(getClass().getResource("../../ressources/image/bjchips.png/").toExternalForm());
	ImageView imgview2;
	
	public StartView() {
		
		bottomBox = new VBox();
		
		errorBox = new VBox();
		errorBox.setId("buttonsstart");
		
		errorLabel = new Label();
		errorLabel.setId("error-label");
		
		errorBox.getChildren().add(errorLabel);
		
		buttonsstart = new HBox();
		buttonsstart.setPrefSize(1000, 400);
		buttonsstart.setId("buttonsstart");
		
		middle = new VBox();
		middle.setPrefSize(1000, 300);
		middle.setId("middle");
		
		headerstart = new VBox();
		headerstart.setPrefSize(1000, 300);
		headerstart.setId("headerstart");
		
		imgview2 = new ImageView(img);
		imgview2.setId("imgview2");
		imgview2.setFitHeight(250.0);
		imgview2.setFitWidth(250.0);

		title = new Label("Blackjack");
		title.setId("text");
		title.setText("BLACKJACK");
		title.setAlignment(Pos.TOP_CENTER);
		title.setPadding(new Insets(30));
		
		start = new Button("START");
		HBox.setMargin(start,new Insets(20,20,40,20));
		
		exit = new Button("EXIT");
		HBox.setMargin(exit,new Insets(20,20,40,20));
		
		
		start.setId("start");
		exit.setId("exit");
		
		headerstart.getChildren().addAll(title, errorBox);
		headerstart.setAlignment(Pos.TOP_CENTER);
		
		middle.getChildren().addAll(imgview2);
		middle.setAlignment(Pos.CENTER);
		
		buttonsstart.getChildren().addAll(start, exit);
		
		this.getChildren().addAll(headerstart, middle, buttonsstart);
	}
}
