package UI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuAlone extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(MenuAlone.getScene(stage, 720, 480));
		stage.setTitle("Test");
		stage.setFullScreen(true);
		stage.show();
	}
	
	
	public static Scene getScene(Stage s, double width, double height) {
		VBox root = new VBox();
		
		root.getChildren().addAll(getDifficulty(), getSep(), getPlateau(), getSep(), getPlayerType(), getSep(), getPseudo());
		root.setAlignment(Pos.CENTER);
		if(isFullscreen(s)) root.setPadding(new Insets(500));
		else root.setPadding(new Insets(200));
		return new Scene(root, width, height);
	}
	
	private static Separator getSep() {
		Separator sep = new Separator();
		sep.setPadding(new Insets(10));
		
		return sep;
	}
	
	private static VBox getDifficulty() {
		VBox root = new VBox();
		
		Label title = new Label("Difficulté");
		title.setStyle("-fx-font: 32px Verdana;");
		Slider slide = new Slider(0, 1, 0);
		
		root.getChildren().addAll(title, slide);
		root.setMargin(title, new Insets(5));
		root.setAlignment(Pos.TOP_CENTER);
		
		return root;
	}
	
	private static VBox getPlateau() {
		VBox root = new VBox();
		
		Label title = new Label("Plateau");
		title.setStyle("-fx-font: 32px Verdana;");
		
		HBox size = new HBox(); // Modifier les BUTTONs avec des IMG
		Button square = new Button("Square");
		Button rect = new Button("Rect");
		Button circle = new Button("Circle");
		size.getChildren().addAll(square, rect, circle);
		
		square.setMinSize(64, 64);
		square.setMaxSize(64, 64);
		size.setMargin(square, new Insets(25));
		rect.setMinSize(64, 64);
		rect.setMaxSize(64, 64);
		size.setMargin(rect, new Insets(25));
		circle.setMinSize(64, 64);
		circle.setMaxSize(64, 64);
		size.setMargin(circle, new Insets(25));
		
		size.setAlignment(Pos.CENTER);
		
		HBox type = new HBox(); // Modifier les BUTTONs avec des IMG
		Button temp = new Button("Temp");
		Button desert = new Button("Desertique");
		Button frozen = new Button("Frozen");
		Button mel = new Button("Mélange");
		type.getChildren().addAll(temp, desert, frozen, mel);
		
		temp.setMinSize(64, 64);
		temp.setMaxSize(64, 64);
		type.setMargin(temp, new Insets(5));
		desert.setMinSize(64, 64);
		desert.setMaxSize(64, 64);
		type.setMargin(desert, new Insets(5));
		frozen.setMinSize(64, 64);
		frozen.setMaxSize(64, 64);
		type.setMargin(frozen, new Insets(5));
		mel.setMinSize(64, 64);
		mel.setMaxSize(64, 64);
		type.setMargin(mel, new Insets(5));
		
		type.setAlignment(Pos.CENTER);
		
		root.getChildren().addAll(title, size, type);
		root.setMargin(title, new Insets(5));
		root.setMargin(size, new Insets(5));
		root.setMargin(type, new Insets(5));
		root.setAlignment(Pos.TOP_CENTER);
		
		return root;
	}
	
	private static VBox getPlayerType() {
		VBox root = new VBox();
		
		Label title = new Label("Joueur");
		title.setStyle("-fx-font: 32px Verdana;");
		
		HBox type = new HBox();
		Button hunter = new Button("Chasseur");
		Button monster = new Button("Monster");
		type.getChildren().addAll(hunter, monster);
		
		root.getChildren().addAll(title, type);
		root.setMargin(title, new Insets(5));
		root.setAlignment(Pos.TOP_CENTER);
		
		return root;
	}
	
	private static HBox getPseudo() {
		HBox root = new HBox();
		
		TextField pseudo = new TextField();
		pseudo.setPromptText("Saisissez votre pseudo");
		pseudo.setStyle("-fx-border-radius: 3 0 0 3;"
				+ "-fx-background-radius: 3 0 0 3;"
				+ "-fx-outline: none;"
				+ "-fx-border-color: rgb(179,205,224);"
				+ "-fx-padding: 5 0 5 0;"
				+ "-fx-background-color: rgb(179,205,224);"
				+ "-fx-text-fill: rgb(77,100,141);"
				+ "-fx-focus-color: transparent;"
				+ "-fx-faint-focus-color: transparent;"
				+ "-fx-font: 14px Verdana;"
				+ "-fx-height: 25px;");
		
		Button start = new Button("Jouer !");
		start.setStyle("-fx-border-radius: 0 3 3 0;"
				+ "-fx-background-radius: 0 3 3 0;"
				+ "-fx-border : 0 0 0 0;"
				+ "-fx-padding: 6 10 5 0;"
				+ "-fx-background-color: rgb(179,205,224);"
				+ "-fx-text-fill: rgb(77,100,141);"
				+ "-fx-font: 14px Verdana;"
				+ "-fx-height: 25px;");
		
		root.getChildren().addAll(pseudo, start);
		root.setAlignment(Pos.TOP_CENTER);
		
		return root;
	}
	
	private static boolean isFullscreen(Stage s) {
		return s.isFullScreen();
	}
}
