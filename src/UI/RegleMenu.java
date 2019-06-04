package UI;

import classes.TestMainMenu;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegleMenu extends Application{
	
	private Label title;
	private Stage stage;
	

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		Pane top = new Pane();
		top.setPrefWidth(1000);
		this.title = new Label("Les règles");
		this.title.setFont(new Font(50));
		
		this.title.setLayoutX(top.getPrefWidth()/2+this.title.getWidth());
		
		Label tBut = new Label("Le but du jeu");
		tBut.setFont(new Font(30));
		Label but = new Label("Au cours de la partie, deux entités vont s’affronter : un monstre et un chasseur. Le but pour le monstre est de parcourir tout le terrain sans se faire attraper par le chasseur et sans revenir sur ses pas. Le but du chasseur est d’attraper le monstre avant qu’il n’est pu explorer l’ensemble de la carte.");
		but.setPadding(new Insets(25));
		
		Label tRegle = new Label("Les règles du jeu");
		tRegle.setFont(new Font(30));
		Label regle = new Label("Les règles sont simples, chaque entités disposent de trois points de déplacements qui seront consommés en quantité variable selon le type de la case cible (plaine, forêt, montagne, ville).\n" + 
				"Durant votre tour, vous avez la possibilité d’utiliser des compétences qui vous sont fournies dès le début pour les plus rudimentaires et obtenables sur certaines cases pour les plus violentes. Toutefois, l’utilisation de ces compétences est réglementée par un niveau d’énergie et une portée… Vous pouvez, bien sûr, n’utiliser qu’une seule compétence par tour !\n" + 
				"Lorsque vous avez terminé votre tour, il vous suffit d’appuyer sur le bouton « Fin de tour ».\n" + 
				"Votre adversaire joue suivant le même schéma que vous et c’est de nouveau à votre tour.\n" + 
				"\n" + 
				"Il vous est possible de jouer seul, contre un ordinateur qui n’a ni foi ni loi !");
		regle.setPadding(new Insets(25));
		
		Button retour = initRetour();
		
		top.getChildren().addAll(this.title,retour);
		
		Separator sep = new Separator();
		sep.setPadding(new Insets(5));
		
		VBox.setMargin(top, new Insets(5));
		root.getChildren().addAll(top,sep, tBut, but, tRegle, regle);
		root.setAlignment(Pos.TOP_CENTER);
		Scene sc = new Scene(root,Double.MAX_VALUE,Double.MAX_VALUE);
		stage = primaryStage;
		stage.setScene(sc);
		stage.setTitle("Mut'Hunter");
		stage.show();		
		
	}
	
	private Button initRetour() {
		Button retour = new Button("Retour");
		retour.setPrefHeight(60);
		retour.setLayoutX(10);
		
		retour.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			TestMainMenu m = new TestMainMenu();
			try {
				m.start(stage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		return retour;
	}

}
