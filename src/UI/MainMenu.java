package UI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenu extends Application{

	private Label title;
	private Button onePlayer;
	private Button twoPlayer;
	private Button regle;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		Scene sc = new Scene(this.getRoot(),1000,1000);
		stage.setScene(sc);
		stage.setTitle("Mut-Hunter");
		stage.show();
		
	}
	
	private VBox getRoot() {
		VBox root  = new VBox();
		HBox nbPlayer = new HBox();
		this.title = new Label("Mut'Hunter");
		this.title.setFont(new Font(80));
		
		this.onePlayer = new Button("Un joueur");
		this.twoPlayer = new Button("Deux joueur");
		
		this.regle = new Button("Regles");
		
		nbPlayer.getChildren().addAll(this.onePlayer,this.twoPlayer);
		HBox.setMargin(this.onePlayer, new Insets(5));
		HBox.setMargin(this.twoPlayer, new Insets(5));
		nbPlayer.setAlignment(Pos.TOP_CENTER);
		
		VBox.setMargin(this.title, new Insets(5));
		VBox.setMargin(this.onePlayer, new Insets(5));
		VBox.setMargin(this.twoPlayer, new Insets(5));
		VBox.setMargin(this.regle, new Insets(5));
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(this.title,nbPlayer,this.regle);		
		return root;
	}

}
