package classes;

import UI.DisplayPlateau;
import UI.PlayerInfo;
import competences.Missile;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestPlayerInfo extends Application{

	static Plateau jeu = new Plateau();
	static Chasseur chasseur = new Chasseur(new Position(0,0));
	static Monstre monstre = new Monstre(new Position(9,9));
	boolean tourChasseur = true;

	
	public static void main(String[] args) {
		chasseur.setCompetence(new Missile(), 1);
		Application.launch(args);
		

	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox();
		PlayerInfo pI = new PlayerInfo(jeu,chasseur,monstre);
		GridPane playerInfo = pI.getGridPane();
		pI.ajoutCompetence(chasseur.getCompetences());
		pI.setPlayerStatut(chasseur.getStatut().name().toLowerCase());
		pI.setPlayerIcon(chasseur.getImage());
		
		DisplayPlateau disP = new DisplayPlateau(jeu, chasseur, monstre);

		jeu.initPlateau();
		jeu.startPersonnage(chasseur, monstre);
		
		VBox pane = new VBox();
		//ImageView logo = new ImageView();
		//logo.setImage(plaine);
		pane.setPrefSize(1000,1000);
		
		Canvas canvas = new Canvas(700,500);
		
		
		GraphicsContext plateau = canvas.getGraphicsContext2D();
		
		disP.affichagePlateau(plateau);
		
		pane.getChildren().add(canvas);
		
		
		pane.setAlignment(Pos.CENTER);
		
		playerInfo.setAlignment(Pos.CENTER);
		playerInfo.setMaxWidth(Double.MAX_VALUE);
		
		root.getChildren().addAll(pane,playerInfo);
		
		pI.getInfoDisplay().setTxtBase(jeu.getTours(),false,0);
		
		Scene scene = new Scene(root, 1000, 1000);
		
		class KeyListenerMovement implements EventHandler<KeyEvent>{
			public void handle(KeyEvent event) {
				if(KeyEvent.KEY_PRESSED != null) {
					if(tourChasseur) {
						chasseur.estDeplace(jeu, event.getCode().toString());
						disP.affichagePlateauVisionChasseur(plateau);
						chasseur.setDeplacement(chasseur.getDeplacement()-jeu.getCase(chasseur.getPosition()).getTypeTerrain().getDeplacement());
						System.out.println(chasseur.getDeplacement());
						if(chasseur.getDeplacement() <= 0) {
							chasseur.resetMouvement();
							tourChasseur = false;
							disP.affichagePlateauVisionMonstre(plateau);
							pI.ajoutCompetence(monstre.getCompetences());
							pI.setPlayerStatut(monstre.getStatut().name().toLowerCase());
							pI.setPlayerIcon(monstre.getImage());
							pI.setTourChasseur(tourChasseur);
							pI.displayEnergy();
							pI.getInfoDisplay().setTxtBase(jeu.getTours(),true,jeu.getCompteurCasesDecouvertes());
						}
						
					} else {
						monstre.estDeplace(jeu, event.getCode().toString());
						disP.affichagePlateauVisionMonstre(plateau);
						monstre.setDeplacement(monstre.getDeplacement()-jeu.getCase(monstre.getPosition()).getTypeTerrain().getDeplacement());
						System.out.println(monstre.getDeplacement());
						if(monstre.getDeplacement() <= 0) {
							monstre.resetMouvement();
							tourChasseur = true;
							disP.affichagePlateauVisionChasseur(plateau);
							pI.ajoutCompetence(chasseur.getCompetences());
							pI.setPlayerStatut(chasseur.getStatut().name().toLowerCase());
							pI.setPlayerIcon(chasseur.getImage());
							pI.setTourChasseur(tourChasseur);
							pI.displayEnergy();
							pI.getInfoDisplay().setTxtBase(jeu.getTours(),false,0);
						}
					}	
				}
			}
		}
		
		stage.addEventHandler(KeyEvent.KEY_PRESSED, new KeyListenerMovement());
		
		
		stage.setTitle("Mut'Hunter");
		stage.setScene(scene);
		stage.show();
		
		
	}
	
	

}
