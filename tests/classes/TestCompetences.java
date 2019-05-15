package classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import competences.Acide;
import competences.Missile;
import competences.Piege;
import competences.Saut;
import competences.Statut;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestCompetences extends Application{
	static Plateau jeu = new Plateau();
	static Personnage monstre = new IAMonstre(new Position(9,9));
	static Personnage chasseur = new Chasseur(new Position(0,0));
	
	@Test
	public void testCompetences() {
		
		jeu.initPlateau();
		jeu.startPersonnage(chasseur, monstre);
		jeu.ajoutLoot(2);
		
		monstre.getCompetences()[0].utilisation(jeu, monstre, chasseur);
		for(int i=0; i<2; i++) {
			monstre.gestionStatuts();
		}
		assertTrue(monstre.gestionStatuts());
		monstre.getCompetences()[1]= new Acide();
		
		monstre.getCompetences()[1]= new Saut();

		chasseur.getCompetences()[1]= new Missile();
		
		chasseur.getCompetences()[1]= new Piege();
	}
	
	
	
	
	public void resetStatut() {
		monstre.setStatut(Statut.Vivant);
		chasseur.setStatut(Statut.Vivant);
	}




	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
