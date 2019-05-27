package classes;

import UI.DisplayPlateau;
import UI.PlayerInfo;
import competences.Acide;
import competences.Competences;
import competences.Missile;
import competences.Saut;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
	int boutonComp = 0;
	boolean compUtilise = false;

	
	public static void main(String[] args) {
		chasseur.setCompetence(new Missile(), 1);
		chasseur.rechargeEnergie(25);
		System.out.println(chasseur.getEnergie());
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
		TextField positionX = new TextField();
		
		TextField positionY = new TextField();
		Button utiliseComp = new Button("Activer compétence");
		
		canvas.setLayoutX(pane.getPrefWidth()-canvas.getWidth());
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
					boutonComp = 0;
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
					boutonComp = 1;
					pane.getChildren().clear();
					pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase,positionX,positionY,utiliseComp);
				}else {
					pI.getEnergyValue().perdreEnergy(pI.getComp2().getComp().getCout());
					if(tourChasseur) {
						pI.getComp2().getComp().utilisation(jeu, chasseur, monstre, new Position(0,0));
					}else {
						pI.getComp2().getComp().utilisation(jeu, monstre, chasseur, new Position(0,0));
					}
				}
			}	
		});
		
		
		utiliseComp.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			System.out.println(Integer.parseInt(positionX.getText())+"  "+Integer.parseInt(positionY.getText()));
			System.out.println(chasseur.getPosition().getX()+"  "+chasseur.getPosition().getY());
			if(positionX.getText() != null && positionY.getText() != null) {
				Competences comp = pI.getListBouton().get(boutonComp).getComp();
				if(comp.equals(new Saut()) || comp.equals(new Acide())) {
					if(tourChasseur) {
						if(Integer.parseInt(positionX.getText()) < chasseur.getPosition().getX()-2 ||
								Integer.parseInt(positionX.getText()) > chasseur.getPosition().getX()+2 ||
								Integer.parseInt(positionY.getText()) < chasseur.getPosition().getY()-2 ||
								Integer.parseInt(positionY.getText()) > chasseur.getPosition().getY()+2) {
							pane.getChildren().clear();
							pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase,positionX,positionY,utiliseComp,erreurComp);
						}else {
							pI.getEnergyValue().perdreEnergy(pI.getListBouton().get(boutonComp).getComp().getCout());
							pI.getListBouton().get(boutonComp).getComp().utilisation(jeu, chasseur, monstre, new Position(Integer.parseInt(positionX.getText()),Integer.parseInt(positionY.getText())));
							pane.getChildren().clear();
							pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase);
						}
					}else {
						if(Integer.parseInt(positionX.getText()) < monstre.getPosition().getX()-2 ||
								Integer.parseInt(positionX.getText()) > monstre.getPosition().getX()+2 ||
								Integer.parseInt(positionY.getText()) < monstre.getPosition().getY()-2 ||
								Integer.parseInt(positionY.getText()) > monstre.getPosition().getY()+2) {
							pane.getChildren().clear();
							pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase,positionX,positionY,utiliseComp,erreurComp);
						}else {
							pI.getEnergyValue().perdreEnergy(pI.getListBouton().get(boutonComp).getComp().getCout());
							pI.getListBouton().get(boutonComp).getComp().utilisation(jeu, chasseur, monstre, new Position(Integer.parseInt(positionX.getText()),Integer.parseInt(positionY.getText())));
							pane.getChildren().clear();
							pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase);
						}
					}
				}else if(comp.equals(new Missile())) {
					if(tourChasseur) {
						if(Integer.parseInt(positionX.getText()) > chasseur.getPosition().getX()-2 ||
								Integer.parseInt(positionX.getText()) < chasseur.getPosition().getX()+2 ||
								Integer.parseInt(positionY.getText()) > chasseur.getPosition().getY()-2 ||
								Integer.parseInt(positionY.getText()) < chasseur.getPosition().getY()+2) {
							pane.getChildren().clear();
							pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase,positionX,positionY,utiliseComp,erreurComp);
						}else {
							pI.getEnergyValue().perdreEnergy(pI.getListBouton().get(boutonComp).getComp().getCout());
							pI.getListBouton().get(boutonComp).getComp().utilisation(jeu, chasseur, monstre, new Position(Integer.parseInt(positionX.getText()),Integer.parseInt(positionY.getText())));
							pane.getChildren().clear();
							pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase);
						}
					}else {
						if(Integer.parseInt(positionX.getText()) > monstre.getPosition().getX()-2 ||
								Integer.parseInt(positionX.getText()) < monstre.getPosition().getX()+2 ||
								Integer.parseInt(positionY.getText()) > monstre.getPosition().getY()-2 ||
								Integer.parseInt(positionY.getText()) < monstre.getPosition().getY()+2) {							
							pane.getChildren().clear();
							pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase,positionX,positionY,utiliseComp,erreurComp);
						}else {
							pI.getEnergyValue().perdreEnergy(pI.getListBouton().get(boutonComp).getComp().getCout());
							pI.getListBouton().get(boutonComp).getComp().utilisation(jeu, monstre, chasseur, new Position(Integer.parseInt(positionX.getText()),Integer.parseInt(positionY.getText())));
							pane.getChildren().clear();
							pane.getChildren().addAll(canvas,nbTours,finDeTour,typeCase);
						}
					}
				}
				
			}
			
		});
		
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
