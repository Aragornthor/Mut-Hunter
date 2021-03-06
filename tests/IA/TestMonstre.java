package IA;

import classes.Chasseur;
import classes.Plateau;
import classes.Position;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestMonstre extends Application{
	
	Plateau jeu = new Plateau();
	Chasseur chasseur = new Chasseur(new Position(0,0));
	IAMonstreGUI monstre = new IAMonstreGUI(new Position(9,9));
	boolean tourChasseur = true;

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage stage){
		jeu.initPlateauTempere();
		jeu.startPersonnage(chasseur, monstre);
		
		Pane pane = new Pane();
		pane.setPrefSize(1000,1000);
		
		Canvas canvas = new Canvas(1000,1000);
		GraphicsContext plateau = canvas.getGraphicsContext2D();
		
		affichagePlateauVisionChasseur(plateau);
		
		pane.getChildren().add(canvas);
		
		Scene scene = new Scene(pane, 1000, 1000);
		
		class KeyListenerMovement implements EventHandler<KeyEvent>{
			public void handle(KeyEvent event) {
				if(KeyEvent.KEY_PRESSED != null) {
					if(tourChasseur) {
						System.out.println("Chasseur");
						chasseur.estDeplace(jeu, event.getCode().toString());
						affichagePlateauVisionChasseur(plateau);
						chasseur.setDeplacement(chasseur.getDeplacement()-jeu.getCase(chasseur.getPosition()).getTypeTerrain().getDeplacement());
						System.out.println(chasseur.getDeplacement());
						if(chasseur.getDeplacement() <= 0) {
							chasseur.resetMouvement();
							tourChasseur = false;
							monstre.jouer(jeu);
							tourChasseur = true;
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
	
	public void affichagePlateau(GraphicsContext p) {
		p.clearRect(0, 0, p.getCanvas().getWidth(), p.getCanvas().getHeight());
		for(int i=0; i<jeu.getLargeur(); i++) {
			for(int j=0; j<jeu.getHauteur(); j++) {
				
				p.drawImage(jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible(),
						((9-j))*(jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2),
						  j*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6));
				
				if(jeu.getPlateau()[i][j].getEstChasseur()) {
					p.drawImage(chasseur.getImage(),
							((9-j))*(chasseur.getImage().getWidth()/2)+(i*chasseur.getImage().getWidth()/2),
							  j*chasseur.getImage().getHeight()/6+(i*chasseur.getImage().getHeight()/6));
				}
				if(jeu.getPlateau()[i][j].getEstMonstre()) {
					p.drawImage(monstre.getImage(),
							((9-j))*(monstre.getImage().getWidth()/2)+(i*monstre.getImage().getWidth()/2),
							  j*monstre.getImage().getHeight()/6+(i*monstre.getImage().getHeight()/6));
				}
			}
		}
	}
	
	public void affichagePlateauVisionChasseur(GraphicsContext p) {
		p.clearRect(0, 0, p.getCanvas().getWidth(), p.getCanvas().getHeight());
		for(int i=0; i<jeu.getLargeur(); i++) {
			for(int j=0; j<jeu.getHauteur(); j++) {
				if(i>chasseur.getPosition().getX()-jeu.getCase(chasseur.getPosition()).getTypeTerrain().getVision()
						&& i<chasseur.getPosition().getX()+jeu.getCase(chasseur.getPosition()).getTypeTerrain().getVision()
						&& j>chasseur.getPosition().getY()-jeu.getCase(chasseur.getPosition()).getTypeTerrain().getVision()
						&& j<chasseur.getPosition().getY()+jeu.getCase(chasseur.getPosition()).getTypeTerrain().getVision()) {
					p.drawImage(jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible(),
							((9-j))*(jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2),
							  j*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6));
					
					if(jeu.getPlateau()[i][j].getEstChasseur()) {
						p.drawImage(chasseur.getImage(),
								((9-j))*(chasseur.getImage().getWidth()/2)+(i*chasseur.getImage().getWidth()/2),
								  j*chasseur.getImage().getHeight()/6+(i*chasseur.getImage().getHeight()/6));
					}
					if(jeu.getPlateau()[i][j].getEstMonstre()) {
						p.drawImage(monstre.getImage(),
								((9-j))*(monstre.getImage().getWidth()/2)+(i*monstre.getImage().getWidth()/2),
								  j*monstre.getImage().getHeight()/6+(i*monstre.getImage().getHeight()/6));
					}
				} else {
					p.drawImage(jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible(),
							((9-j))*(jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getWidth()/2),
							  j*jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getHeight()/6));
				}
			}
		}
	}
	
	public void affichagePlateauVisionMonstre(GraphicsContext p) {
		p.clearRect(0, 0, p.getCanvas().getWidth(), p.getCanvas().getHeight());
		for(int i=0; i<jeu.getLargeur(); i++) {
			for(int j=0; j<jeu.getHauteur(); j++) {
				if(i>monstre.getPosition().getX()-jeu.getCase(monstre.getPosition()).getTypeTerrain().getVision()
						&& i<monstre.getPosition().getX()+jeu.getCase(monstre.getPosition()).getTypeTerrain().getVision()
						&& j>monstre.getPosition().getY()-jeu.getCase(monstre.getPosition()).getTypeTerrain().getVision()
						&& j<monstre.getPosition().getY()+jeu.getCase(monstre.getPosition()).getTypeTerrain().getVision()) {
					p.drawImage(jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible(),
							((9-j))*(jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2),
							  j*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6));
					
					if(jeu.getPlateau()[i][j].getEstChasseur()) {
						p.drawImage(chasseur.getImage(),
								((9-j))*(chasseur.getImage().getWidth()/2)+(i*chasseur.getImage().getWidth()/2),
								  j*chasseur.getImage().getHeight()/6+(i*chasseur.getImage().getHeight()/6));
					}
					if(jeu.getPlateau()[i][j].getEstMonstre()) {
						p.drawImage(monstre.getImage(),
								((9-j))*(monstre.getImage().getWidth()/2)+(i*monstre.getImage().getWidth()/2),
								  j*monstre.getImage().getHeight()/6+(i*monstre.getImage().getHeight()/6));
					}
				} else {
					p.drawImage(jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible(),
							((9-j))*(jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getWidth()/2),
							  j*jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getHeight()/6));
				}
			}
		}
	}
	
}
