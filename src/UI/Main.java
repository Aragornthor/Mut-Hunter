package UI;



import java.util.Random;

import classes.Plateau;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage stage){
		Plateau jeu = new Plateau();
		jeu.initPlateau();
		
		Pane pane = new Pane();
		//ImageView logo = new ImageView();
		//logo.setImage(plaine);
		
		Canvas canvas = new Canvas(1000,1000);
		GraphicsContext plateau = canvas.getGraphicsContext2D();
		
		for(int i=0; i<jeu.getLargeur(); i++) {
			for(int j=0; j<jeu.getHauteur(); j++) {
				
				plateau.drawImage(jeu.getPlateau()[i][j].getTypeTerrain().getImage(),
						((9-j))*(jeu.getPlateau()[i][j].getTypeTerrain().getImage().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImage().getWidth()/2),
						  j*jeu.getPlateau()[i][j].getTypeTerrain().getImage().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImage().getHeight()/6));
				
				/*if(rand.nextInt(100)>20) {
					plateau.drawImage(plaine, 
									  ((9-j))*(plaine.getWidth()/2)+(i*plaine.getWidth()/2), //((9-j))*(plaine.getWidth()/2)+(j*plaine.getWidth())
									  j*plaine.getHeight()/6+(i*plaine.getHeight()/6));
				} else {
					plateau.drawImage(montagne, 
							  ((9-j))*(montagne.getWidth()/2)+(i*montagne.getWidth()/2), //((9-j))*(plaine.getWidth()/2)+(j*plaine.getWidth())
							  j*montagne.getHeight()/6+(i*montagne.getHeight()/6));
				}*/
			}
		}
		
		pane.getChildren().add(canvas);
		
		Scene scene = new Scene(pane, 1000, 1000);
		
		stage.setTitle("Mut'Hunter");
		stage.setScene(scene);
		stage.show();
		
	}
	
}
