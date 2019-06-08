package UI;

import java.util.ArrayList;
import java.util.Arrays;

import Event.CompetenceEvent;
import classes.Chasseur;
import classes.Monstre;
import classes.Plateau;
import classes.Position;
import competences.Acide;
import competences.Competences;
import competences.Missile;
import competences.Saut;
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

public class GameUI {
	
	private VBox root;
	private PlayerInfo pI;
	private DisplayPlateau dP;
	private Chasseur chasseur;
	private Monstre monstre;
	private Plateau jeu;
	boolean tourChasseur = true;
	final double DECAL_X = 30;
	boolean compUtilise = false;
	int boutontComp = 0;
	boolean fini = false;
	LancementGameUI mv3;
	
	
	public GameUI(Chasseur chasseur, Monstre monstre, Plateau jeu, LancementGameUI mv3) {
		this.chasseur = chasseur;
		this.monstre = monstre;
		this.jeu = jeu;
		this.pI = new PlayerInfo(this.chasseur, this.monstre);
		this.dP = new DisplayPlateau(this.jeu, this.chasseur, this.monstre);
		this.mv3 = mv3;
		System.out.println("GUI CHASSEUR :"+chasseur.getPosition());
		System.out.println("GUI MONSTRE :"+monstre.getPosition());
		System.out.println("GUI TOUR "+jeu.getTours());
		System.out.println("GUI MENU "+mv3);
		System.out.println("GUI GUI "+this);
		System.out.println("GUI PLATEAU "+jeu);
	}
	
	public Scene getScene() {
		root = new VBox();
		GridPane playerInfo = pI.getGridPane();
		this.pI.ajoutCompetence(chasseur.getCompetences());
		this.pI.setPlayerStatut(chasseur.getStatut().name().toLowerCase());
		this.pI.setPlayerIcon(chasseur.getImage());
		

		jeu.initPlateau();
		jeu.startPersonnage(chasseur, monstre);
		
		Pane pane = new Pane();
		pane.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
		//ImageView logo = new ImageView();
		//logo.setImage(plaine);
		pane.setPrefSize(1000,1000);
		Canvas canvas = new Canvas(1000,1000);
		
		this.pI.getPlayerStatut().setTextFill(Color.WHITE);
		GraphicsContext plateau = canvas.getGraphicsContext2D();
		
		this.dP.affichagePlateau(plateau);
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
		
		this.pI.getComp1().addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			if(!compUtilise) {
				Competences c = pI.getComp1().getComp();
				if(c.equals(new Saut()) || c.equals(new Missile()) || c.equals(new Acide()) ) {
					boutontComp = 0;
					pane.getChildren().clear();
					pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase,positionX,positionY,utiliseComp);
				}else {
					this.pI.getEnergyValue().perdreEnergy(pI.getComp1().getComp().getCout());
					if(tourChasseur) {
						this.pI.getComp1().getComp().utilisation(jeu, chasseur, monstre, new Position(0,0));
					}else {
						this.pI.getComp1().getComp().utilisation(jeu, monstre, chasseur, new Position(0,0));
					}
					compUtilise = true;
				}
			}
		});
	
		this.pI.getComp2().addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			if(!compUtilise) {
				Competences c = pI.getComp2().getComp();
				if(c.equals(new Saut()) || c.equals(new Missile()) || c.equals(new Acide()) ) {
					boutontComp = 1;
					System.out.println("BoutonComp = "+boutontComp);
					pane.getChildren().clear();
					pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase,positionX,positionY,utiliseComp);
				}else {
					this.pI.getEnergyValue().perdreEnergy(pI.getComp2().getComp().getCout());
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
				new CompetenceEvent(this.pI.getEnergyValue(),
									this.pI.getComp2().getComp(),
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
				chasseur.resetMouvement();
				dP.affichagePlateauVisionMonstre(plateau);
				pI.ajoutCompetence(monstre.getCompetences());
				pI.setPlayerStatut(monstre.getStatut().name().toLowerCase());
				pI.setPlayerIcon(monstre.getImage());
				pI.setTourChasseur(tourChasseur);
				pI.displayEnergy();
				typeCase.setText("Type de case : "+jeu.getCase(monstre.getPosition()).getTypeTerrain().toString().toLowerCase());
			}else {
				tourChasseur = true;
				monstre.resetMouvement();
				dP.affichagePlateauVisionChasseur(plateau);
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
				
		Scene scene = new Scene(root, 1920, 1000);
		
		root.setBackground(new Background(
				new BackgroundImage(
						new Image("file:ressources/images/fondTest.png"),  BackgroundRepeat.NO_REPEAT,
																	   BackgroundRepeat.NO_REPEAT, 
																	   BackgroundPosition.CENTER, 
																	   null)));

	
		
		//scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyListenerMovement(jeu, chasseur, monstre, tourChasseur, fini, plateau, dP));
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
				if(KeyEvent.KEY_PRESSED != null) {
					System.out.println(fini);
					if(e.getCode().toString().equalsIgnoreCase("z") || e.getCode().toString().equalsIgnoreCase("q") || e.getCode().toString().equalsIgnoreCase("s") || e.getCode().toString().equalsIgnoreCase("d"))
						if(tourChasseur) {
							chasseur.estDeplace(jeu, e.getCode().toString());
							fini = chasseur.changeCase(jeu);
							dP.affichagePlateauVisionChasseur(plateau);
							System.out.println(chasseur.getDeplacement()+" "+chasseur.getPosition());
							if(chasseur.getDeplacement() == 0) {
								chasseur.resetMouvement();
								tourChasseur = false;
								dP.affichagePlateauVisionMonstre(plateau);
								jeu.addTours();
							}
							if(jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition())) {
								fini = true;
								System.out.println("Victoire chasseur"+fini);
							}
							
						} else {
							if(monstre.estDeplace(jeu, e.getCode().toString())) {
								fini = monstre.changeCase(jeu);
							}
							dP.affichagePlateauVisionMonstre(plateau);
							System.out.println(monstre.getDeplacement());
							if(monstre.getDeplacement() <= 0) {
								monstre.resetMouvement();
								tourChasseur = true;
								dP.affichagePlateauVisionChasseur(plateau);
								jeu.addTours();
							}
							System.out.println("finito1"+fini);
							if(jeu.victoireMonstre() || jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition())) {
								fini = true;
								System.out.println("defaite monstre"+fini);
							}
							
							
						}
					else {
						e.consume();
					}
					System.out.println("finito2"+fini);
				}
			});
		
		scene.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
			if(fini) {
				System.out.println("Bonjour");
				try {
					mv3.stop();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					Main mm = new Main();
					mm.start(Main.getStage());
					System.out.println("C FINI");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		return scene;
		
	}
	
	
	
	public VBox getRoot() {
		return this.root;
	}
	
}
