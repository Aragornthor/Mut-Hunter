package UI;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
/**
 * Interface des regles du jeu
 * @author Bankaert Benoit
 *
 */
public class RegleMenu /*extends Application*/{
	
	private Label title;
	private Button retour;
	private Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
	private Scene sc;

	
	/*
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = getRoot();
		Scene sc = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
		stage = primaryStage;
		stage.setScene(sc);
		stage.setTitle("Mut'Hunter");
		
		
				
		stage.show();	
	}
	*/
	/**
	 * Initialise le menu des regles
	 */
	public RegleMenu() {
		this.sc = new Scene(getRoot(),screenSize.getWidth(),screenSize.getHeight());
	}
	
	/**
	 * @return la scene du menu des regles
	 */
	public Scene getScene() {		
		return sc;
	}
	
	/**
	 * @return le menu des regles
	 */
	public VBox getRoot() {
		VBox root = new VBox();
		Pane top = new Pane();
		top.setPrefWidth(1000);
		this.title = new Label("Les règles");
		this.title.setFont(new Font(50));
		this.title.setPrefWidth(275);
		this.title.setLayoutX(screenSize.getWidth()/2 - this.title.getPrefWidth()/2);

		this.title.setTextFill(Color.WHITE);
		
		
		Label tBut = new Label("Le but du jeu");
		tBut.setFont(new Font(30));
		tBut.setTextFill(Color.WHITE);
		Label but = new Label("Au cours de la partie, deux entités vont s’affronter : un monstre et un chasseur. Le but pour le monstre est de parcourir tout le terrain sans se faire attraper par le chasseur et sans revenir sur ses pas. Le but du chasseur est d’attraper le monstre avant qu’il n’est pu explorer l’ensemble de la carte.");
		but.setPadding(new Insets(25));
		but.setTextFill(Color.WHITE);
		
		Label tRegle = new Label("Les règles du jeu");
		tRegle.setFont(new Font(30));
		tRegle.setTextFill(Color.WHITE);
	
		//DESCRIPTION DES TYPES DE TERRAIN
		VBox regles = new VBox();
		Label regle1 = new Label("Les règles sont simples, chaque entités disposent de trois points de déplacements qui seront consommés en quantité variable selon le type de la case cible (plaine, forêt, montagne, ville).");	
		Canvas typeTerrain = new Canvas(screenSize.getWidth(), 100);
		GraphicsContext gcType = typeTerrain.getGraphicsContext2D();
		Image plaine = new Image("file:ressources/images/plaines.png");
		double posX = screenSize.getWidth()/2-(4*plaine.getWidth());
		gcType.drawImage(plaine, posX, 0);
		gcType.drawImage(new Image("file:ressources/images/foret.png"), posX+150, 0);
		gcType.drawImage(new Image("file:ressources/images/montagnes.png"), posX+300, 0);
		gcType.drawImage(new Image("file:ressources/images/ville2.png"), posX+450, 0);
	
		regle1.setTextFill(Color.WHITE);
		
		//DESCRIPTION DES COMPETENCES
		Label regle2 = new Label("Durant votre tour, vous avez la possibilité d’utiliser des compétences qui vous sont fournies dès le début pour les plus rudimentaires et obtenables sur certaines cases pour les plus violentes. Toutefois, l’utilisation de ces compétences est réglementée par un niveau d’énergie et une portée… Vous pouvez, bien sûr, n’utiliser qu’une seule compétence par tour !");
		Canvas comp = new Canvas(screenSize.getWidth(), 50);
		GraphicsContext gcComp = comp.getGraphicsContext2D();
		Image btAcide = new Image("file:ressources/images/boutonAcide.png");
		posX = (screenSize.getWidth()-(6*btAcide.getWidth()))/2;
		gcComp.drawImage(btAcide, posX, 0);
		gcComp.drawImage(new Image("file:ressources/images/boutonIem.png"), posX+150, 0);
		gcComp.drawImage(new Image("file:ressources/images/boutonMissile.png"), posX+300, 0);
		gcComp.drawImage(new Image("file:ressources/images/boutonPiege.png"), posX+450, 0);
		gcComp.drawImage(new Image("file:ressources/images/boutonSaut.png"), posX+600, 0);
		gcComp.drawImage(new Image("file:ressources/images/boutonShield.png"), posX+750, 0);
		
		regle2.setTextFill(Color.WHITE);
		
		//DESCRIPTION DES TOURS
		Label regle3 = new Label("Lorsque vous avez terminé votre tour, il vous suffit d’appuyer sur le bouton « Fin de tour ».");
		
		regle3.setTextFill(Color.WHITE);
		
		Label regle4 = new Label("Votre adversaire joue suivant le même schéma que vous et c’est de nouveau à votre tour.");
		
		regle4.setTextFill(Color.WHITE);
		
		//DESCRIPTION DE L'IA
		Label regle5 = new Label("Il vous est possible de jouer seul, contre un ordinateur qui n’a ni foi ni loi !");
		
		regle5.setTextFill(Color.WHITE);
		
		regle1.setPadding(new Insets(5));
		regle1.setWrapText(true);
		regle2.setPadding(new Insets(5));
		regle2.setWrapText(true);
		regle3.setPadding(new Insets(5));
		regle3.setWrapText(true);
		regle4.setPadding(new Insets(5));
		regle4.setWrapText(true);
		regle5.setPadding(new Insets(5));
		regle5.setWrapText(true);
		
		
		regles.setAlignment(Pos.CENTER);
		regles.getChildren().addAll(regle1,typeTerrain,regle2,comp,regle3,regle4,regle5);
		
		this.retour = initRetour();
		
		top.getChildren().addAll(this.title,retour);
		
		Separator sep = new Separator();
		sep.setPadding(new Insets(5));
		
		VBox.setMargin(top, new Insets(5));
		root.getChildren().addAll(top,sep, tBut, but, tRegle, regles);
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
		Button retour = new Button("Retour");
		retour.setPrefHeight(60);
		retour.setLayoutX(10);
		
		
		
		return retour;
	}
	
	public Button getRetour() {
		return this.retour;
	}

}
