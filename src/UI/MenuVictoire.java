package UI;

import classes.Personnage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
/**
 * Interface des regles du jeu
 * @author Bankaert Benoit
 *
 */
public class MenuVictoire extends Application{
	
	private Label title;
	private Button retour;
	private Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
	private static Scene sc;
	private String vainqueur;
	private String persoJoueur;
	private boolean victoireJoueur;
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		if(MenuAlone.getPerso() == 0) persoJoueur = "Chasseur";
		else persoJoueur = "Monstre";
		vainqueur = GameUI.getVainqueur();
		if(vainqueur==persoJoueur) victoireJoueur=true;
		VBox root = getRoot();
		Scene sc = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
		primaryStage.setScene(sc);
		primaryStage.setTitle("Mut'Hunter");
		
		retour.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			Main mm = new Main();
			try {
				mm.start(Main.getStage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
				
		primaryStage.show();	
	}

	
	
	/**
	 * Initialise le menu des regles
	 */
	public MenuVictoire() {
		MenuVictoire.sc = new Scene(getRoot(),screenSize.getWidth(),screenSize.getHeight());
	}
	
	/**
	 * @return la scene du menu des regles
	 */
	public static Scene getScene() {		
		return sc;
	}
	
	/**
	 * @return le menu des regles
	 */
	public VBox getRoot() {
		VBox root = new VBox();
		Pane top = new Pane();
		top.setPrefWidth(1000);
		if(victoireJoueur) this.title = new Label("VICTOIRE !");
		else this.title = new Label("DEFAITE...");
		this.title.setFont(new Font(50));
		this.title.setPrefWidth(275);
		this.title.setLayoutX(screenSize.getWidth()/2 - this.title.getPrefWidth()/2);

		this.title.setTextFill(Color.WHITE);
	
		
		
		this.retour = initRetour();
		
		top.getChildren().addAll(this.title,retour);
		
		Separator sep = new Separator();
		sep.setPadding(new Insets(5));
		
		VBox.setMargin(top, new Insets(5));
		root.getChildren().addAll(top,sep);
		root.setAlignment(Pos.TOP_CENTER);
			
		
		root.setBackground(new Background(
				new BackgroundImage(
						new Image("file:ressources/images/fondTest.png"),  BackgroundRepeat.NO_REPEAT,
																	   BackgroundRepeat.NO_REPEAT, 
																	   BackgroundPosition.CENTER, 
																	   null)));
		return root;
	}
	
	private Button initRetour() {
		Button retour = new Button("Menu principal");
		retour.setPrefHeight(60);
		retour.setLayoutX(10);
		
		
		
		return retour;
	}
	
	public Button getRetour() {
		return this.retour;
	}

}
