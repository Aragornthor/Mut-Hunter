package UI;





import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainMenu /*extends Application*/{

	private VBox root;
	private HBox nbPlayer;
	private boolean isMenuDisplay = false;
	
	private Label title;
	private Label displayMenu;
	private Button onePlayer;
	private Button twoPlayer;
	private Button regle;
	private Canvas personnage;
	
	public MediaPlayer music;
	/*
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		Scene sc = new Scene(this.initRoot(),1000,1000);
		stage.setScene(sc);
		stage.setTitle("Mut-Hunter");
		stage.show();
		stage.addEventHandler(KeyEvent.KEY_TYPED, e ->{
			if(!menu) {
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
				this.menu = true;
			}
		});
		
	}
	*/
	
	public MainMenu() {
		initRoot();
	}
	
	private VBox initRoot() {
		this.root  = new VBox();
		this.title = new Label("Mut'Hunter");
		this.title.setFont(new Font(80));
		
		this.displayMenu = new Label("Appuyer sur une touche pour continuer ...");
		
		this.onePlayer = initOnePlayer();
		this.twoPlayer = new Button("Deux joueur");
		
		this.regle = new Button("Regles");
		
		this.nbPlayer = initNbPlayer();
		
		this.personnage = initPersonnage();
		
		VBox.setMargin(this.title, new Insets(5));
		VBox.setMargin(this.displayMenu, new Insets(5));
		VBox.setMargin(this.nbPlayer, new Insets(5));
		VBox.setMargin(this.regle, new Insets(5));
		this.root.setAlignment(Pos.CENTER);
		
		Media pick = new Media(new File("ressources/sounds/MenuTheme.mp3").toURI().toString());
		music = new MediaPlayer(pick);
		
		music.setCycleCount(MediaPlayer.INDEFINITE);
		music.setVolume(0.2);
		
		this.root.getChildren().addAll(this.title,this.displayMenu);	
		
		return root;
	}
	
	private Canvas initPersonnage() {
		Canvas personnage = new Canvas(700,400);
		personnage.getGraphicsContext2D().setFill(Color.BLACK);
		personnage.getGraphicsContext2D().fillRect(0, 0, personnage.getWidth(), personnage.getHeight());
		Image chasseur = new Image("file:ressources/images/mech.png");
		Image monstre = new Image("file:ressources/images/monstre.png");
		personnage.getGraphicsContext2D().drawImage(chasseur, 0,
				personnage.getHeight()/2 - chasseur.getHeight()/2);
		personnage.getGraphicsContext2D().drawImage(monstre, personnage.getWidth() - monstre.getWidth(),
				personnage.getHeight()/2 - monstre.getHeight()/2);
		return personnage;
		
	}
	
	private HBox initNbPlayer() {
		this.nbPlayer = new HBox();
		this.nbPlayer.getChildren().addAll(this.onePlayer,this.twoPlayer);
		HBox.setMargin(this.onePlayer, new Insets(5));
		HBox.setMargin(this.twoPlayer, new Insets(5));
		this.nbPlayer.setAlignment(Pos.TOP_CENTER);
		
		return this.nbPlayer;

	}
	
	private Button initOnePlayer() {
		Button onePlayer = new Button("Un joueur");;
		return onePlayer;
	}
	
	public Canvas getPersonnage() {
		return this.personnage;
	}
	
	public boolean getIsMenuDisplay() {
		return this.isMenuDisplay;
	}
	
	public void setIsMenuDisplay(boolean b) {
		this.isMenuDisplay = b;
	}
	
	public VBox getRoot() {
		return this.root;
	}
	
	public void setRoot(VBox newRoot) {
		this.root = newRoot;
	}
	
	public Label getTitle() {
		return this.title;
	}
	
	public HBox getNbPlayer() {
		return this.nbPlayer;
	}
	
	public Button getRegle() {
		return this.regle;
	}
	
	public Button getOnePlayer() {
		return this.onePlayer;
	}
	
	public Button getTwoPlayer() {
		return this.twoPlayer;
	}
	
	

}
