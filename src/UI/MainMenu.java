package UI;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * 
 * @author Benoit Bankaert
 *
 */
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
	
	//public MediaPlayer music;
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
	
	/**
	 * Instancie MainMenu
	 */
	public MainMenu() {
		initRoot();
	}
	
	/**
	 * Créer et renvoyer la fenêtre d'accueil
	 * @return Renvoie la fénêtre d'accueil
	 */
	private VBox initRoot() {
		this.root  = new VBox();
		this.title = new Label("Mut'Hunter");
		this.title.setTextFill(Color.WHITE);
		this.title.setFont(new Font(80));
		
		this.displayMenu = new Label("Appuyer sur une touche pour continuer ...");
		this.displayMenu.setTextFill(Color.WHITE);
		
		this.onePlayer = initOnePlayer();
		this.twoPlayer = initTwoPlayer();
		
		this.regle = initRegle();
		
		this.nbPlayer = initNbPlayer();
		
		this.personnage = initPersonnage();
		
		VBox.setMargin(this.title, new Insets(5));
		VBox.setMargin(this.displayMenu, new Insets(5));
		VBox.setMargin(this.nbPlayer, new Insets(5));
		VBox.setMargin(this.regle, new Insets(5));
		this.root.setAlignment(Pos.CENTER);
		
		//Media pick = new Media(new File("ressources/sounds/MenuTheme.mp3").toURI().toString());
		//music = new MediaPlayer(pick);
		
		//music.setCycleCount(MediaPlayer.INDEFINITE);
		//music.setVolume(0.2);
		
		this.root.getChildren().addAll(this.title,this.displayMenu);	
		this.root.setBackground(new Background(
				new BackgroundImage(
						new Image("file:ressources/images/fondTest.png"),  BackgroundRepeat.NO_REPEAT,
																	   BackgroundRepeat.NO_REPEAT, 
																	   BackgroundPosition.CENTER, 
																	   null)));
		return root;
	}
	
	/**
	 * Créer et renvoyer un écran de "chargement"
	 * @return Renvoie un écran de "chargement"
	 */
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
	
	/**
	 * Créer et renvoyer une interface de choix de mode de jeu
	 * @return Renvoie l'interface de choix du mode de jeu
	 */
	private HBox initNbPlayer() {
		this.nbPlayer = new HBox();
		this.nbPlayer.getChildren().addAll(this.onePlayer,this.twoPlayer);
		HBox.setMargin(this.onePlayer, new Insets(5));
		HBox.setMargin(this.twoPlayer, new Insets(5));
		this.nbPlayer.setAlignment(Pos.TOP_CENTER);
		
		return this.nbPlayer;

	}
	
	/**
	 * 
	 * @return Renvoie le bouton en mode Solo
	 */
	private Button initOnePlayer() {
		Button onePlayer = new Button("Un joueur");
		onePlayer.setPrefSize(250, 70);
		onePlayer.setMaxSize(250, 70);
		return onePlayer;
	}
	
	/**
	 * 
	 * @return Renvoie le bouton en mode Versus
	 */
	private Button initTwoPlayer() {
		Button twoPlayer = new Button("Deux joueur");
		twoPlayer.setPrefSize(250, 70);
		return twoPlayer;
	}
	
	/**
	 * 
	 * @return Renvoie le bouton en mode Solo
	 */
	private Button initRegle() {
		Button regle = new Button("Regles");
		regle.setPrefSize(250, 70);
		return regle;
	}
	
	/**
	 * 
	 * @return Renvoie l'image associée à une entité
	 */
	public Canvas getPersonnage() {
		return this.personnage;
	}
	
	/**
	 * 
	 * @return TRUE si le menu est affiché sinon FALSE
	 */
	public boolean getIsMenuDisplay() {
		return this.isMenuDisplay;
	}
	
	/**
	 * Changer la visibilité du menu
	 * @param b TRUE pour afficher le menu
	 */
	public void setIsMenuDisplay(boolean b) {
		this.isMenuDisplay = b;
	}
	
	/**
	 * 
	 * @return Renvoie le conteneur principal de la fenêtre
	 */
	public VBox getRoot() {
		return this.root;
	}
	
	/**
	 * Permet de changer le conteneur principal de la fenêtre
	 * @param newRoot Le nouveau conteneur principal à afficher
	 */
	public void setRoot(VBox newRoot) {
		this.root = newRoot;
	}
	
	/**
	 * 
	 * @return Renvoie le titre de la page
	 */
	public Label getTitle() {
		return this.title;
	}
	
	/**
	 * 
	 * @return Renvoie le nombre de joueurs
	 */
	public HBox getNbPlayer() {
		return this.nbPlayer;
	}
	
	/**
	 * 
	 * @return Renvoie le bouton pour accéder aux règles du jeu
	 */
	public Button getRegle() {
		return this.regle;
	}
	
	/**
	 * 
	 * @return Renvoie le bouton pour jouer en Solo
	 */
	public Button getOnePlayer() {
		return this.onePlayer;
	}
	
	/**
	 * 
	 * @return Renvoie le bouton pour jouer en Duo
	 */
	public Button getTwoPlayer() {
		return this.twoPlayer;
	}
	
	

}
