package classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import IA.IAMonstreConsole;
import competences.Acide;
import competences.IEM;
import competences.Missile;
import competences.Piege;
import competences.Saut;
import competences.Statut;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestCompetences extends Application{
	static Plateau jeu = new Plateau();
	static Personnage monstre = new IAMonstreConsole(new Position(9,9));
	static Personnage chasseur = new Chasseur(new Position(0,0));
	
	
	public static void main(String[] args) {
		testCompetences();
	}
	
	public static void testCompetences() {
		
		jeu.initPlateau();
		jeu.startPersonnage(chasseur, monstre);
		jeu.ajoutLoot(2);
		
		monstre.getCompetences()[0].utilisation(jeu, monstre, chasseur);
		System.out.println("Shield début : " + monstre.getStatut());
		for(int i=0; i<3; i++) {
			monstre.gestionStatuts();
		}
		System.out.println("Shield fin : " + monstre.getStatut());
		
		monstre.getCompetences()[1]= new Acide();
		chasseur.setPosition(new Position(8,9));
		System.out.println("Tirez en 8,9");
		monstre.getCompetences()[1].utilisation(jeu, monstre, chasseur);
		System.out.println("Acide début : " + chasseur.getStatut());
		for(int i=0; i<4; i++) {
			chasseur.gestionStatuts();
		}
		System.out.println("Acide fin : " + chasseur.getStatut());
		
		monstre.getCompetences()[1]= new Saut();
		System.out.println("Sautez en 7,9");
		System.out.println("Saut coordonnees monstre debut : " + monstre.getPosition()+ " " + chasseur.getStatut().getNbTour());
		monstre.getCompetences()[1].utilisation(jeu, monstre, chasseur);
		System.out.println("Saut coordonnees monstre fin : " + monstre.getPosition()+ " " + chasseur.getStatut().getNbTour());
		System.out.println("Saut début : " + chasseur.getStatut()+ " " + chasseur.getStatut().getNbTour());
		for(int i=0; i<3; i++) {
			chasseur.gestionStatuts();
			System.out.println("Saut milieu : " + chasseur.getStatut()+ " " + chasseur.getStatut().getNbTour());
		}
		System.out.println("Saut fin : " + chasseur.getStatut()+ " " + chasseur.getStatut().getNbTour());
		
		chasseur.getCompetences()[0].utilisation(jeu, chasseur, monstre);
		System.out.println("IEM début : " + monstre.getStatut()+ " " + monstre.getStatut().getNbTour());
		for(int i=0; i<3; i++) {
			monstre.gestionStatuts();
			System.out.println("IEM milieu : " + monstre.getStatut()+ " " + monstre.getStatut().getNbTour());
		}
		System.out.println("IEM fin : " + monstre.getStatut()+ " " + monstre.getStatut().getNbTour());
		
		chasseur.getCompetences()[1]= new Missile();
		System.out.println("Tirez en 6,9");
		System.out.println("Missile debut : " + monstre.getStatut()+ " " + monstre.getStatut().getNbTour() +" " + chasseur.getPosition());
		chasseur.getCompetences()[1].utilisation(jeu, chasseur, monstre);
		System.out.println("Missile fin : " + monstre.getStatut() + " " + monstre.getStatut().getNbTour());
		
		monstre.setStatut(Statut.Vivant);
		
		chasseur.getCompetences()[1]= new Piege();
		chasseur.getCompetences()[1].utilisation(jeu, chasseur, monstre);
		System.out.println("Est piege : "+jeu.getCase(chasseur.getPosition()).getTypeCase());
		chasseur.estDeplace(jeu, "z");
		monstre.estDeplace(jeu, "d");
		System.out.println("Est piege : "+jeu.getCase(monstre.getPosition()).getTypeCase());
		System.out.println("Saut coordonnees monstre debut : " + monstre.getPosition()+ " " + chasseur.getStatut().getNbTour());
		System.out.println("Piege debut C: " + chasseur.getStatut()+ " " + chasseur.getStatut().getNbTour() +" " + chasseur.getPosition());
		System.out.println("Piege debut M: " + monstre.getStatut()+ " " + monstre.getStatut().getNbTour() +" " + monstre.getPosition());
		for(int i=0; i<3; i++) {
			monstre.gestionStatuts();
		}
		System.out.println("Piege fin : " + monstre.getStatut()+ " " + monstre.getStatut().getNbTour());
		
		monstre.getCompetences()[0].utilisation(jeu, monstre, chasseur);
		System.out.println("Shield début : " + monstre.getStatut()+ " " + monstre.getStatut().getNbTour());
		chasseur.getCompetences()[0].utilisation(jeu, chasseur, monstre);
		System.out.println("Shield milieu : " + monstre.getStatut()+ " " + monstre.getStatut().getNbTour());
		monstre.getCompetences()[0].utilisation(jeu, monstre, chasseur);
		System.out.println("Shield fin : " + monstre.getStatut()+ " " + monstre.getStatut().getNbTour());
	}




	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
