package UI;



import classes.Chasseur;
import classes.Monstre;
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

public class Main extends Application{
	
	Plateau jeu = new Plateau();
	Chasseur chasseur = new Chasseur(new Position(0,0));
	Monstre monstre = new Monstre(new Position(9,9));
	boolean tourChasseur = true;

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage stage){
		DisplayPlateau disP = new DisplayPlateau(jeu, chasseur, monstre);
		jeu.initPlateau();
		jeu.startPersonnage(chasseur, monstre);
		
		Pane pane = new Pane();
		pane.setPrefSize(1000,1000);
		
		Canvas canvas = new Canvas(1000,1000);
		GraphicsContext plateau = canvas.getGraphicsContext2D();
		
		disP.affichagePlateauVisionChasseur(plateau);
		
		pane.getChildren().add(canvas);
		
		Scene scene = new Scene(pane, 1000, 1000);
		
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
