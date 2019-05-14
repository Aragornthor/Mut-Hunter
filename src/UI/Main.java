package UI;



import classes.Chasseur;
import classes.Monstre;
import classes.Plateau;
import classes.Position;
import javafx.application.Application;
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

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage stage){
		jeu.initPlateau();
		jeu.startPersonnage(chasseur, monstre);
		
		Pane pane = new Pane();
		//ImageView logo = new ImageView();
		//logo.setImage(plaine);
		pane.setPrefSize(1000,1000);
		
		Canvas canvas = new Canvas(1000,1000);
		GraphicsContext plateau = canvas.getGraphicsContext2D();
		
		for(int i=0; i<jeu.getLargeur(); i++) {
			for(int j=0; j<jeu.getHauteur(); j++) {
				
				plateau.drawImage(jeu.getPlateau()[i][j].getTypeTerrain().getImage(),
						((9-j))*(jeu.getPlateau()[i][j].getTypeTerrain().getImage().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImage().getWidth()/2),
						  j*jeu.getPlateau()[i][j].getTypeTerrain().getImage().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImage().getHeight()/6));
				
				if(jeu.getPlateau()[i][j].getEstChasseur()) {
					plateau.drawImage(chasseur.getImage(),
							((9-j))*(chasseur.getImage().getWidth()/2)+(i*chasseur.getImage().getWidth()/2),
							  j*chasseur.getImage().getHeight()/6+(i*chasseur.getImage().getHeight()/6));
				}
				
			}
		}
		
		pane.getChildren().add(canvas);
		
		Scene scene = new Scene(pane, 1000, 1000);
		
		stage.addEventHandler(KeyEvent.KEY_PRESSED, e->{
			//System.out.println(e.getCode().toString());
			chasseur.estDeplace(jeu, e.getCode().toString());
			affichagePlateau(plateau);
		});
		
		stage.setTitle("Mut'Hunter");
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void affichagePlateau(GraphicsContext p) {
		p.clearRect(0, 0, p.getCanvas().getWidth(), p.getCanvas().getHeight());
		for(int i=0; i<jeu.getLargeur(); i++) {
			for(int j=0; j<jeu.getHauteur(); j++) {
				
				p.drawImage(jeu.getPlateau()[i][j].getTypeTerrain().getImage(),
						((9-j))*(jeu.getPlateau()[i][j].getTypeTerrain().getImage().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImage().getWidth()/2),
						  j*jeu.getPlateau()[i][j].getTypeTerrain().getImage().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImage().getHeight()/6));
				
				if(jeu.getPlateau()[i][j].getEstChasseur()) {
					p.drawImage(chasseur.getImage(),
							((9-j))*(chasseur.getImage().getWidth()/2)+(i*chasseur.getImage().getWidth()/2),
							  j*chasseur.getImage().getHeight()/6+(i*chasseur.getImage().getHeight()/6));
				}
			}
		}
	}
	
}
