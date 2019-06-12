package UI;

import IA.IAChasseurGUI;
import IA.IAMonstreGUI;
import UI.GameUI;
import classes.Chasseur;
import classes.Monstre;
import classes.Plateau;
import classes.Position;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

public class LancementGameUI extends Application{

	Plateau jeu;
	Chasseur chasseur;
	Monstre monstre;
	final double DECAL_X = 30;
	boolean compUtilise = false;
	int boutontComp = 0;
	boolean fini = false;
	private static Stage stage;
	//MenuAlone ma = new MenuAlone();
	//MenuAlone2J ma2 = new MenuAlone2J();

	
	public static void main(String[] args) {
		Application.launch(args);
		

	}

	@Override
	public void start(Stage stage) throws Exception {
		int climat;
		if(MenuAlone2J.use) {
			climat = MenuAlone2J.getClimat();
		} else {
			climat = MenuAlone.getClimat();
		}
		
		GameUI gUI;
		if(MenuAlone.getForme() == 0 || MenuAlone2J.getForme() == 0) {
			jeu = new Plateau(10,10, climat);
		} else if(MenuAlone.getForme() == 1 || MenuAlone2J.getForme() == 1) {
			jeu = new Plateau(14,8, climat);
		} else if(MenuAlone.getForme() == 2 || MenuAlone2J.getForme() == 2) {
			jeu = new Plateau(10,10, climat);
		}
		if(MenuAlone2J.use) {
			chasseur =  new Chasseur(new Position(0,0));
			monstre = new Monstre(new Position(jeu.getLargeur()-1,jeu.getHauteur()-1));
			gUI = new GameUI(chasseur, monstre, jeu, this);
		}
		else if(MenuAlone.getPerso() == 0) {
			chasseur =  new Chasseur(new Position(0,0));
			gUI = new GameUI(chasseur, new IAMonstreGUI(new Position(jeu.getLargeur()-1,jeu.getHauteur()-1)), jeu, this);
		} else {
			monstre = new Monstre(new Position(jeu.getLargeur()-1,jeu.getHauteur()-1));
			gUI = new GameUI(new IAChasseurGUI(new Position(0,0)), monstre, jeu, this);
		} /*else {
			chasseur =  new Chasseur(new Position(0,0));
			monstre = new Monstre(new Position(jeu.getLargeur()-1,jeu.getHauteur()-1));
			gUI = new GameUI(chasseur, monstre, jeu, this);
		}*/
		
		System.out.println("Création du GUI");
		LancementGameUI.stage = stage;
		Scene scene = gUI.getScene();
		
		gUI.getRoot().setBackground(new Background(
				new BackgroundImage(
						new Image("file:ressources/images/fondTest.png"),  BackgroundRepeat.NO_REPEAT,
																	   BackgroundRepeat.NO_REPEAT, 
																	   BackgroundPosition.CENTER, 
																	   null)));
		
		
		stage.setTitle("Mut'Hunter");
		stage.setScene(scene);
		stage.show();
		
		
	}

	public void stop() {
		stage.close();
	}
}
