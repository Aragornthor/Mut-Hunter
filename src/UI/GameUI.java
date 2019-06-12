package UI;

import java.util.ArrayList;
import java.util.Arrays;

import Event.CompetenceEvent;
import IA.IAChasseurGUI;
import IA.IAMonstreGUI;
import classes.Chasseur;
import classes.Monstre;
import classes.Personnage;
import classes.Plateau;
import classes.Position;
import competences.Acide;
import competences.Competences;
import competences.Missile;
import competences.Piege;
import competences.Saut;
import competences.Statut;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
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
import javafx.stage.Screen;

public class GameUI {
	
	private VBox root;
	private PlayerInfo pI;
	private DisplayPlateau dP;
	private Chasseur chasseur;
	private Monstre monstre;
	private Plateau jeu;
	boolean tourChasseur = true;
	final double DECAL_X = 30;
	boolean compUtilisee = false;
	int boutonComp = 0;
	boolean fini = false;
	LancementGameUI mv3;
	private int nbComp;
	private static int vainqueur;
	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
	
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
	
	public GameUI(IAChasseurGUI chasseur, Monstre monstre, Plateau jeu, LancementGameUI mv3) {
		this((Chasseur)chasseur, (Monstre)monstre, jeu, mv3);
	}
	
	public GameUI(Chasseur chasseur, IAMonstreGUI monstre, Plateau jeu, LancementGameUI mv3) {
		this((Chasseur)chasseur, (Monstre)monstre, jeu, mv3);
	}
	
	public Scene getScene() {
		root = new VBox();
		GridPane playerInfo = pI.getGridPane();
		this.pI.ajoutCompetence(chasseur.getCompetences());
		this.pI.setPlayerStatut(chasseur.getStatut().name().toLowerCase());
		this.pI.setPlayerIcon(chasseur.getImage());
		

		jeu.startPersonnage(chasseur, monstre);
		
		Pane pane = new Pane();
		pane.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
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
		
		canvas.setLayoutX(screenSize.getWidth()/2-canvas.getWidth()/2);
		canvas.setLayoutY(100);
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
			Competences c = pI.getComp1().getComp();
			Personnage p;
			if(tourChasseur) p = chasseur;
			else p = monstre;
			System.out.println(p);
			if(!compUtilisee && p.getEnergie()>=c.getCout()) {
				if(c.equals(new Saut()) || c.equals(new Missile()) || c.equals(new Acide()) ) {
					nbComp = 0;
					boutonComp = 0;
					pane.getChildren().clear();
					pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase,positionX,positionY,utiliseComp);
					positionX.setText("");
					positionY.setText("");
				}else {
					this.pI.getEnergyValue().perdreEnergy(pI.getComp1().getComp().getCout());
					if(tourChasseur) {
						this.pI.getComp1().getComp().utilisation(jeu, chasseur, monstre, new Position(0,0));
					}else {
						this.pI.getComp1().getComp().utilisation(jeu, monstre, chasseur, new Position(0,0));
					}
					compUtilisee = true;
				}
			}
		});
		
	
		this.pI.getComp2().addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			System.out.println("Utilisee "+compUtilisee);
			Competences c = pI.getComp2().getComp();
			Personnage p;
			if(tourChasseur) p = chasseur;
			else p = monstre;
			System.out.println(p);
			if(!compUtilisee && p.getEnergie()>=c.getCout()) {
				if(c.equals(new Saut()) || c.equals(new Missile()) || c.equals(new Acide()) ) {
					nbComp = 1;
					boutonComp = 1;
					System.out.println("BoutonComp = "+boutonComp);
					pane.getChildren().clear();
					pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase,positionX,positionY,utiliseComp);
					positionX.setText("");
					positionY.setText("");
				}else {
					this.pI.getEnergyValue().perdreEnergy(pI.getComp2().getComp().getCout());
					System.out.println("Perte energie");
					if(tourChasseur) {
						c.utilisation(jeu, chasseur, monstre, new Position(0,0));
					}else {
						c.utilisation(jeu, monstre, chasseur, new Position(0,0));
					}
					compUtilisee = true;
				}
			}	
		});
		
		utiliseComp.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> {
			compUtilisee = true;
		});
		
		utiliseComp.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new CompetenceEvent(this.pI.getEnergyValue(),
									this,
									pane,
									new ArrayList<Node>(Arrays.asList(canvas,nbTours,finDeTour,typeCase,positionX,positionY,utiliseComp,erreurComp)),
									new ArrayList<Node>(Arrays.asList(canvas,nbTours,finDeTour,typeCase)),
									jeu,
									chasseur,
									monstre,
									tourChasseur,
									positionX,
									positionY,
									dP,
									plateau));
		
		playerInfo.setAlignment(Pos.CENTER);
		playerInfo.setMaxWidth(Double.MAX_VALUE);
		
		finDeTour.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			finDeTour(plateau, typeCase, nbTours, pane, canvas, finDeTour);
		});
		
		
		
		root.setAlignment(Pos.CENTER);
		
		root.getChildren().addAll(pane,playerInfo);
				
		Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
		
		root.setBackground(new Background(
				new BackgroundImage(
						new Image("file:ressources/images/fondTest.png"),  BackgroundRepeat.NO_REPEAT,
																	   BackgroundRepeat.NO_REPEAT, 
																	   BackgroundPosition.CENTER, 
																	   null)));

	
		
		//scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyListenerMovement(jeu, chasseur, monstre, tourChasseur, fini, plateau, dP));
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if(KeyEvent.KEY_PRESSED != null) {
				System.out.println("Fini event Key : "+fini);
				if(e.getCode().toString().equalsIgnoreCase("z") || e.getCode().toString().equalsIgnoreCase("q") || e.getCode().toString().equalsIgnoreCase("s") || e.getCode().toString().equalsIgnoreCase("d"))
					if(tourChasseur) {
						if(chasseur.getStatut().equals(Statut.Stun)) {
							finDeTour(plateau, typeCase, nbTours, pane, canvas, finDeTour);
						}
						else {
							if(!chasseur.getClass().equals(IAChasseurGUI.class) && tourChasseur) {
								System.out.println(monstre.getClass().toString());
								if(chasseur.estDeplace(jeu, e.getCode().toString())) {
									fini = chasseur.changeCase(jeu);
								}
								dP.affichagePlateauVisionChasseur(plateau);
								if(chasseur.getDeplacement() == 0) {
									finDeTour(plateau, typeCase, nbTours, pane, canvas, finDeTour);
								}
								if(jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition())) {
									fini = true;
									vainqueur = 0;
									System.out.println("Victoire chasseur"+fini);
								}
							}
						}
						
					} else {
						if(monstre.getStatut().equals(Statut.Stun)) {
							finDeTour(plateau, typeCase, nbTours, pane, canvas, finDeTour);
						}
						else {
							if(!monstre.getClass().equals(IAMonstreGUI.class)) {
								if(monstre.estDeplace(jeu, e.getCode().toString())) {
									System.out.println(monstre.getDeplacement()+" "+monstre.getPosition());
									//monstre.eventCase(jeu);
									fini = monstre.changeCase(jeu);
								}
								dP.affichagePlateauVisionMonstre(plateau);
								System.out.println(monstre.getDeplacement());
								if(monstre.getDeplacement() <= 0) {
									finDeTour(plateau, typeCase, nbTours, pane, canvas, finDeTour);
									if(this.chasseur.getClass().equals(IAChasseurGUI.class)) {
										((IAChasseurGUI)this.chasseur).jouer(jeu);
										tourChasseur = false;
									}
								}
								if(jeu.victoireMonstre() || jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition())) {
									fini = true;
									if(jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition())) vainqueur = 0;
									else vainqueur = 1;
									System.out.println("defaite monstre"+fini);
								}
								
								
							}
						}
						
						
					}
				else {
					e.consume();
				}
			}
		});
		
		scene.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
			if(fini) {
				System.out.println("Bonjour");
				try {
					mv3.stop();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					MenuVictoire mv = new MenuVictoire();
					mv.start(Main.getStage());
					System.out.println("C FINI");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
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
					MenuVictoire mv = new MenuVictoire();
					mv.start(Main.getStage());
					System.out.println("C FINI");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		scene.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
			if(fini) {
				System.out.println("Bonjour");
				try {
					mv3.stop();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					MenuVictoire mv = new MenuVictoire();
					mv.start(Main.getStage());
					System.out.println("C FINI");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		scene.addEventHandler(MouseEvent.MOUSE_MOVED, e ->{
			if(tourChasseur) this.pI.setPlayerStatut(chasseur.getStatut().name().toLowerCase());
			else this.pI.setPlayerStatut(monstre.getStatut().name().toLowerCase());

			if(monstre.getStatut() == Statut.Mort) fini = true;
		});
		
		return scene;
		
	}
	
	
	
	public VBox getRoot() {
		return this.root;
	}
	
	public void finDeTour(GraphicsContext plateau, Label typeCase, Label nbTours, Pane pane, Canvas canvas, Button finDeTour) {
		if(tourChasseur) {
			monstre.gestionStatuts();
			tourChasseur = false;
			chasseur.resetMouvement();
			if(!monstre.getClass().equals(IAMonstreGUI.class)) {
				dP.affichagePlateauVisionMonstre(plateau);
				pI.ajoutCompetence(monstre.getCompetences());
				pI.setPlayerStatut(monstre.getStatut().name().toLowerCase());
				pI.setPlayerIcon(monstre.getImage());
				pI.setTourChasseur(tourChasseur);
				pI.displayEnergy();
				typeCase.setText("Type de case : "+jeu.getCase(monstre.getPosition()).getTypeTerrain().toString().toLowerCase());
				System.out.println("Chasseur "+chasseur);
			}
			monstre.rechargeEnergie();
			this.pI.getEnergyValue().gainEnergy(10);
			jeu.ajoutLoot(1);
			compUtilisee = false;
			pane.getChildren().clear();
			pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase);
			if(this.monstre.getClass().equals(IAMonstreGUI.class)) {
				((IAMonstreGUI)this.monstre).jouer(jeu);
				this.monstre.changeCase(jeu);
				tourChasseur = true;
			}
		}else {
			chasseur.gestionStatuts();
			tourChasseur = true;
			monstre.resetMouvement();
			if(!chasseur.getClass().equals(IAChasseurGUI.class)) {
				dP.affichagePlateauVisionChasseur(plateau);
				pI.ajoutCompetence(chasseur.getCompetences());
				pI.setPlayerStatut(chasseur.getStatut().name().toLowerCase());
				pI.setPlayerIcon(chasseur.getImage());
				pI.setTourChasseur(tourChasseur);
				pI.displayEnergy();
				typeCase.setText("Type de case : "+jeu.getCase(chasseur.getPosition()).getTypeTerrain().toString().toLowerCase());
				System.out.println("Monstre "+monstre);
			}
				
			chasseur.rechargeEnergie();
			this.pI.getEnergyValue().gainEnergy(10);
			jeu.ajoutLoot(1);
			jeu.addTours();
			nbTours.setText("Tour n°"+jeu.getTours());
			compUtilisee = false;
			pane.getChildren().clear();
			pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase);
			if(chasseur.getClass().equals(IAChasseurGUI.class) && tourChasseur) {
				((IAChasseurGUI)chasseur).jouer(jeu);
				chasseur.changeCase(jeu);
				tourChasseur = false;
			}
		}
	}
	
	public PlayerInfo getpI() {
		return pI;
	}
	
	public int getNbComp() {
		return nbComp;
	}
	
	public static String getVainqueur() {
		if(vainqueur == 0) return "Chasseur";
		else return "Monstre";
	}
}
