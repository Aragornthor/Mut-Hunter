package classes;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import competences.Competences;
import competences.Statut;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestMain extends Application{
	
	static Plateau jeu = new Plateau();
	static Personnage monstre = new IAMonstre(new Position(9,9));
	static Personnage chasseur = new Chasseur(new Position(0,0));
	
	public static void main(String[] args) {
		
		jeu.initPlateau();
		jeu.startPersonnage(chasseur, monstre);
		jeu.ajoutLoot(2);
		
		boolean fini = false;
		
		while(!fini) { //Tant que la partie n'est pas finie, lance les tours des joueurs
			if(monstre.gestionStatuts()) {
				if(choixAction()) {
					System.out.println("TEST1");
					fini = tourMonstre();
				}
				else {
					jeu.getCase(monstre.getPosition()).show(); //Montre le chasseur
					jeu.getCase(chasseur.getPosition()).hide();	//et cache le monstre
					jeu.affichePlateau(monstre);
					Competences c[] = monstre.getCompetences();
					if(choixCompetences(monstre)) {
						System.out.println("LE MONSTRE");
						c[0].utilisation(jeu, monstre, chasseur);
					}
					else {
						c[1].utilisation(jeu, monstre, chasseur);
					}
					System.out.println("AHHHH"+monstre.getStatut());
				}
			}
			if(fini) break; //Si le monstre perds ou gagne la partie, fini la partie
			if(chasseur.gestionStatuts()) {
				if(choixAction()) {
					System.out.println("TEST2");
					fini = tourChasseur();
				}
				else {
					jeu.getCase(chasseur.getPosition()).show(); //Montre le chasseur
					jeu.getCase(monstre.getPosition()).hide();	//et cache le monstre
					jeu.affichePlateau(chasseur);
					Competences c[] = chasseur.getCompetences();
					if(!choixCompetences(chasseur)) {
						System.out.println("LE CHASSEUR");
						c[0].utilisation(jeu, chasseur, monstre);
					}
					else {
						c[1].utilisation(jeu, chasseur, monstre);
					}
				}
			}
			if(monstre.getStatut() == Statut.Mort) fini = true;
			jeu.addTours();
		}
		
		System.out.println("Partie Finie !");

	}

	public static boolean tourChasseur() {
		jeu.getCase(chasseur.getPosition()).show(); //Montre le chasseur
		jeu.getCase(monstre.getPosition()).hide();	//et cache le monstre
		int deplacementsRestant = chasseur.getDeplacement();
		
		while(deplacementsRestant>0) {
			jeu.affichePlateau(chasseur);
			if(jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition())) return true;	//si le chasseur gagne, fini le tour
			if(jeu.victoireMonstre()) return true;	//Si le monstre découvre toutes les cases, le partie se fini
			chasseur.seDeplace(jeu);	//Le joueur se déplace
			chasseur.changeCase(jeu);	//Change la case comme chasseur
			if(jeu.getCase(chasseur.getPosition()).getEstPortail()) deplacementsRestant++;
			if(jeu.getTours()-jeu.getDernierLoot()>5) { 	//Si le dernier loot a été pris il y a 6 tours, en ajoute un
				jeu.ajoutLoot(1);			
			}
			deplacementsRestant--;
		}
		return false;
	}
	
	public static boolean tourMonstre() {
		jeu.getCase(monstre.getPosition()).show();	//Montre le monstre
		jeu.getCase(chasseur.getPosition()).hide();	//et cache le chasseur
		boolean perdu = false;
		
		int deplacementsRestant = monstre.getDeplacement();
		
		while(deplacementsRestant>0) {
			jeu.affichePlateau(monstre);
			if(jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition())) return true; //Si le monstre est sur la position du chasseur, la partie se fini
			if(jeu.victoireMonstre()) return true;	//Si le monstre découvre toutes les cases, le partie se fini
			monstre.seDeplace(jeu);		//Le joueur se déplace
			if(! jeu.getCase(monstre.getPosition()).getEstPortail() && !jeu.getCase(monstre.getPosition()).getEstDecouvert()) jeu.setCompteurCasesDecouvertes();	//Si le monstre est sur une case qui n'est pas découverte et qui n'est pas un portail, incrémente le nombre de cases découvertes
			perdu = monstre.changeCase(jeu);	//Si le monstre est sur une case qu'il a déjà decouvert, la partie se fini. Sinon, change la case où il se trouve en case monstre
			if(jeu.getCase(monstre.getPosition()).getEstPortail()) deplacementsRestant++;
			if(jeu.getTours()-jeu.getDernierLoot()>5) {		//Si le dernier loot a été pris il y a 6 tours, en ajoute un
				jeu.ajoutLoot(1);			
			}
			deplacementsRestant--;
			if(perdu) return perdu;
		}
		return false;
	}
	
	public static boolean choixAction() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Que voulez vous faire : 1 - Deplacement / 2 - Compétence");
		int choix = sc.nextInt();
		if(choix == 1) return true;
		else return false;
	}
	
	public static boolean choixCompetences(Personnage p) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Quelle compétence utiliser : 1 - +"+ p.getCompetences()[0] +" / 2 - "+p.getCompetences()[1] );
		int choix = sc.nextInt();
		if(choix == 1) return true;
		else return false;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
}