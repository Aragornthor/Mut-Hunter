package UI;





import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu extends Application{

	private VBox root;
	private HBox nbPlayer;
	
	private Label title;
	private Label displayMenu;
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
		
		stage.addEventHandler(KeyEvent.KEY_TYPED, e ->{
			FadeTransition ft = new FadeTransition();
			ft.setDuration(Duration.millis(1000));
			ft.setNode(this.root);
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setOnFinished((ActionEvent event) ->{
				VBox newRoot = new VBox(this.title,this.nbPlayer,this.regle);
				this.root = newRoot;
				this.root.setAlignment(Pos.TOP_CENTER);
				Scene newScene = new Scene(this.root,1000,1000);
				stage.setScene(newScene);
			});
			
			ft.play();
		});
		
	}
	
	private VBox getRoot() {
		this.root  = new VBox();
		this.nbPlayer = new HBox();
		this.title = new Label("Mut'Hunter");
		this.title.setFont(new Font(80));
		
		this.displayMenu = new Label("Appuyer sur une touche pour continuer ...");
		
		this.onePlayer = new Button("Un joueur");
		this.twoPlayer = new Button("Deux joueur");
		
		this.regle = new Button("Regles");
		
		this.nbPlayer.getChildren().addAll(this.onePlayer,this.twoPlayer);
		HBox.setMargin(this.onePlayer, new Insets(5));
		HBox.setMargin(this.twoPlayer, new Insets(5));
		this.nbPlayer.setAlignment(Pos.TOP_CENTER);
		
		VBox.setMargin(this.title, new Insets(5));
		VBox.setMargin(this.displayMenu, new Insets(5));
		VBox.setMargin(this.nbPlayer, new Insets(5));
		VBox.setMargin(this.regle, new Insets(5));
		this.root.setAlignment(Pos.CENTER);
		this.root.getChildren().addAll(this.title,this.displayMenu);	
		
		
		return root;
	}

}
