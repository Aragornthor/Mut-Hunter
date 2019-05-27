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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestPlayerInfo extends Application{

	static Plateau jeu = new Plateau();
	static Chasseur chasseur = new Chasseur(new Position(0,0));
	static Monstre monstre = new Monstre(new Position(9,9));
	boolean tourChasseur = true;
	final double DECAL_X = 30;

	
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
		
		Pane pane = new Pane();
		//ImageView logo = new ImageView();
		//logo.setImage(plaine);
		pane.setPrefSize(1000,1000);
		
		Canvas canvas = new Canvas(700,500);
		
		pI.getPlayerStatut().setTextFill(Color.WHITE);
		GraphicsContext plateau = canvas.getGraphicsContext2D();
		
		disP.affichagePlateau(plateau);
		Label nbTours = new Label("Tour n°"+jeu.getTours());
		Label typeCase = new Label("Type de case : "+jeu.getCase(chasseur.getPosition()).getTypeTerrain().toString().toLowerCase());
		nbTours.setTextFill(Color.WHITE);
		typeCase.setTextFill(Color.WHITE);
		Button finDeTour = new Button("Fin de tour");
		
		canvas.setLayoutX(pane.getPrefWidth()-canvas.getWidth());
		finDeTour.setLayoutX(DECAL_X);
		finDeTour.setLayoutY(100);
		nbTours.setLayoutX(DECAL_X);
		nbTours.setLayoutY(10);
		typeCase.setLayoutX(DECAL_X);
		typeCase.setLayoutY(nbTours.getLayoutY()+15);
		
		pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase);
		
		
		playerInfo.setAlignment(Pos.CENTER);
		playerInfo.setMaxWidth(Double.MAX_VALUE);
		
	
		
		
		root.getChildren().addAll(pane,playerInfo);
		
		pI.getInfoDisplay().setTxtBase(jeu.getTours(),false,0);
		
		Scene scene = new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE);
		
		root.setBackground(new Background(
				new BackgroundImage(
						new Image("file:ressources/images/fond.png"), BackgroundRepeat.NO_REPEAT,
																	   BackgroundRepeat.NO_REPEAT, 
																	   BackgroundPosition.CENTER, 
																	   null)));

		
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
