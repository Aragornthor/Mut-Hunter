package classes;

import UI.GameUI;
import competences.Missile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

public class TestPlayerInfo extends Application{

	static Plateau jeu = new Plateau();
	static Chasseur chasseur = new Chasseur(new Position(0,0));
	static Monstre monstre = new Monstre(new Position(9,9));
	boolean tourChasseur = true;
	final double DECAL_X = 30;
	boolean compUtilise = false;
	int boutontComp = 0;
	boolean fini = false;

	
	public static void main(String[] args) {
		chasseur.setCompetence(new Missile(), 1);
		chasseur.rechargeEnergie(25);
		Application.launch(args);
		

	}

	@Override
	public void start(Stage stage) throws Exception {
		GameUI gUI = new GameUI(chasseur, monstre, jeu);
				
		Scene scene = gUI.getScene();
		
		gUI.getRoot().setBackground(new Background(
				new BackgroundImage(
						new Image("file:ressources/images/fondTest.png"),  BackgroundRepeat.NO_REPEAT,
																	   BackgroundRepeat.NO_REPEAT, 
																	   BackgroundPosition.CENTER, 
																	   null)));

		
		stage.addEventHandler(KeyEvent.KEY_RELEASED, e->{
			if(fini) {
				System.out.println("Bonjour");
				try {
					this.stop();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				//TestMainMenu.launch(args);
				try {
					TestMainMenu mm = new TestMainMenu();
					mm.start(stage);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		stage.setTitle("Mut'Hunter");
		stage.setScene(scene);
		stage.show();
		
		
	}
	
	

}
