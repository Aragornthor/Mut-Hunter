package classes;

import java.util.ArrayList;
import java.util.Arrays;

import Event.CompetenceEvent;
import UI.DisplayPlateau;
import UI.PlayerInfo;
import competences.Acide;
import competences.Competences;
import competences.Missile;
import competences.Saut;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
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
	boolean compUtilise = false;
	int boutontComp = 0;

	
	public static void main(String[] args) {
		chasseur.setCompetence(new Missile(), 1);
		chasseur.rechargeEnergie(25);
		Application.launch(args);
		

	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox();
		PlayerInfo pI = new PlayerInfo(chasseur,monstre);
		GridPane playerInfo = pI.getGridPane();
		pI.ajoutCompetence(chasseur.getCompetences());
		pI.setPlayerStatut(chasseur.getStatut().name().toLowerCase());
		pI.setPlayerIcon(chasseur.getImage());
		
		DisplayPlateau disP = new DisplayPlateau(jeu, chasseur, monstre);

		jeu.initPlateau();
		jeu.startPersonnage(chasseur, monstre);
		
		Pane pane = new Pane();
		pane.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
		//ImageView logo = new ImageView();
		//logo.setImage(plaine);
		pane.setPrefSize(1000,1000);
		
		Canvas canvas = new Canvas(1000,1000);
		
		pI.getPlayerStatut().setTextFill(Color.WHITE);
		GraphicsContext plateau = canvas.getGraphicsContext2D();
		
		disP.affichagePlateau(plateau);
		Label nbTours = new Label("Tour n°"+jeu.getTours());
		Label typeCase = new Label("Type de case : "+jeu.getCase(chasseur.getPosition()).getTypeTerrain().toString().toLowerCase());
		nbTours.setTextFill(Color.WHITE);
		typeCase.setTextFill(Color.WHITE);
		Button finDeTour = new Button("Fin de tour");
		TextField positionX = new TextField();
		
		TextField positionY = new TextField();
		Button utiliseComp = new Button("Activer compétence");
		
		canvas.setLayoutX(canvas.getWidth()/2);
		canvas.setLayoutY(150);
		finDeTour.setLayoutX(DECAL_X);
		finDeTour.setLayoutY(100);
		nbTours.setLayoutX(DECAL_X);
		nbTours.setLayoutY(10);
		typeCase.setLayoutX(DECAL_X);
		typeCase.setLayoutY(nbTours.getLayoutY()+15);
		positionX.setLayoutX(DECAL_X);
		positionX.setLayoutY(finDeTour.getLayoutY()+50);
		positionY.setLayoutX(DECAL_X);
		positionY.setLayoutY(positionX.getLayoutY()+50);
		utiliseComp.setLayoutX(DECAL_X);
		utiliseComp.setLayoutY(positionY.getLayoutY()+100);
		
		Label erreurComp = new Label("Mauvaises coordonnées,\nveuillez recommencer !");
		erreurComp.setTextFill(Color.RED);
		erreurComp.setLayoutX(DECAL_X);
		erreurComp.setLayoutY(utiliseComp.getLayoutY()-35);
		
		pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase);
		
		pI.getComp1().addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			if(!compUtilise) {
				Competences c = pI.getComp1().getComp();
				if(c.equals(new Saut()) || c.equals(new Missile()) || c.equals(new Acide()) ) {
					boutontComp = 0;
					pane.getChildren().clear();
					pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase,positionX,positionY,utiliseComp);
				}else {
					pI.getEnergyValue().perdreEnergy(pI.getComp1().getComp().getCout());
					if(tourChasseur) {
						pI.getComp1().getComp().utilisation(jeu, chasseur, monstre, new Position(0,0));
					}else {
						pI.getComp1().getComp().utilisation(jeu, monstre, chasseur, new Position(0,0));
					}
					compUtilise = true;
				}
			}
		});
	
		pI.getComp2().addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			if(!compUtilise) {
				Competences c = pI.getComp2().getComp();
				if(c.equals(new Saut()) || c.equals(new Missile()) || c.equals(new Acide()) ) {
					boutontComp = 1;
					System.out.println("BoutonComp = "+boutontComp);
					pane.getChildren().clear();
					pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase,positionX,positionY,utiliseComp);
				}else {
					pI.getEnergyValue().perdreEnergy(pI.getComp2().getComp().getCout());
					System.out.println("Perte energie");
					if(tourChasseur) {
						c.utilisation(jeu, chasseur, monstre, new Position(0,0));
					}else {
						c.utilisation(jeu, monstre, chasseur, new Position(0,0));
					}
				}
			}	
		});
		
		
		utiliseComp.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new CompetenceEvent(pI.getEnergyValue(),
									pI.getComp2().getComp(),
									pane,
									new ArrayList<Node>(Arrays.asList(canvas,nbTours,finDeTour,typeCase,positionX,positionY,utiliseComp,erreurComp)),
									new ArrayList<Node>(Arrays.asList(canvas,nbTours,finDeTour,typeCase)),
									jeu,
									chasseur,
									monstre,
									tourChasseur,
									positionX,
									positionY));
		
		playerInfo.setAlignment(Pos.CENTER);
		playerInfo.setMaxWidth(Double.MAX_VALUE);
		
		finDeTour.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if(tourChasseur) {
				tourChasseur = false;
				monstre.resetMouvement();
				disP.affichagePlateauVisionMonstre(plateau);
				pI.ajoutCompetence(monstre.getCompetences());
				pI.setPlayerStatut(monstre.getStatut().name().toLowerCase());
				pI.setPlayerIcon(monstre.getImage());
				pI.setTourChasseur(tourChasseur);
				pI.displayEnergy();
				typeCase.setText("Type de case : "+jeu.getCase(monstre.getPosition()).getTypeTerrain().toString().toLowerCase());
			}else {
				tourChasseur = true;
				chasseur.resetMouvement();
				disP.affichagePlateauVisionChasseur(plateau);
				pI.ajoutCompetence(chasseur.getCompetences());
				pI.setPlayerStatut(chasseur.getStatut().name().toLowerCase());
				pI.setPlayerIcon(chasseur.getImage());
				pI.setTourChasseur(tourChasseur);
				pI.displayEnergy();
				typeCase.setText("Type de case : "+jeu.getCase(chasseur.getPosition()).getTypeTerrain().toString().toLowerCase());

			}
		});
		
		root.setAlignment(Pos.CENTER);
		
		root.getChildren().addAll(pane,playerInfo);
				
		Scene scene = new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE);
		
		root.setBackground(new Background(
				new BackgroundImage(
						new Image("file:ressources/images/fond.png"),  BackgroundRepeat.NO_REPEAT,
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
						typeCase.setText("Type de case : "+jeu.getCase(chasseur.getPosition()).getTypeTerrain().toString().toLowerCase());

					} else {
						monstre.estDeplace(jeu, event.getCode().toString());
						disP.affichagePlateauVisionMonstre(plateau);
						monstre.setDeplacement(monstre.getDeplacement()-jeu.getCase(monstre.getPosition()).getTypeTerrain().getDeplacement());
						System.out.println(monstre.getDeplacement());
						typeCase.setText("Type de case : "+jeu.getCase(monstre.getPosition()).getTypeTerrain().toString().toLowerCase());

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
