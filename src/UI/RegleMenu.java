package UI;

import classes.TestMainMenu;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegleMenu extends Application{
	
	private Label title;
	private Stage stage;
	

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		Pane top = new Pane();
		top.setPrefWidth(1000);
		this.title = new Label("Regles");
		this.title.setFont(new Font(50));
		
		this.title.setLayoutX(top.getPrefWidth()/2+this.title.getWidth());
		
		Button retour = initRetour();
		
		top.getChildren().addAll(this.title,retour);
		
		Separator sep = new Separator();
		sep.setPadding(new Insets(5));
		
		VBox.setMargin(top, new Insets(5));
		root.getChildren().addAll(top,sep);
		root.setAlignment(Pos.TOP_CENTER);
		Scene sc = new Scene(root,Double.MAX_VALUE,Double.MAX_VALUE);
		stage = primaryStage;
		stage.setScene(sc);
		stage.setTitle("Mut'Hunter");
		stage.show();		
		
	}
	
	private Button initRetour() {
		Button retour = new Button("Retour");
		retour.setPrefHeight(60);
		retour.setLayoutX(10);
		
		retour.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			TestMainMenu m = new TestMainMenu();
			try {
				m.start(stage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		return retour;
	}

}
