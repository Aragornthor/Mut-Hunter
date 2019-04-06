package classes;

import java.util.Random;

import competences.*;

/**
 * 
 * La classe Loot permet d'instancier un loot qui est une competence en relation avec le type du personnage
 * @author Robin Gallifa
 *
 */

public class Loot {
	private static Competences[] competencesChasseur = new Competences[]{new IEM(), new Missile(), new Piege()};
	private static Competences[] competencesMonstre = new Competences[]{new Shield(), new Acide(), new Saut()};
	
	/**
	 * 
	 * @param p Personnage par rapport auquel la competence doit etre selectionnee 
	 * @return Retourne une competence aleatoire selon si le personnage est un chasseur ou un monstre
	 */
	private static Competences competenceAleatoire(Personnage p) {
		Random rand = new Random();
		Competences[] comp = p.getCompetences();
		Competences[] competences;
		int i=0;
		if(p.getType() == "chasseur") competences = competencesChasseur;
		else competences = competencesMonstre;
		
		if(comp[1] == null) return competences[rand.nextInt(competences.length-1)+1];
		else{
			while(comp[0].equals(competences[i]) || comp[1].equals(competences[i])){
				i++;
			}
			return competences[i];
		}			
	}
	
	/**
	 * Change la competence vide en une competence aleatoire
	 * @param p Personnage ou l'on desire changer une de ses competences, en l'occurence la deuxieme qui est vide
	 */
	public static void changeCompetence(Personnage p) {
		changeCompetence(p, 2);
	}
	
	/**
	 * Change la competence desiree en une competence aleatoire
	 * @param p Personnage ou l'on desire changer une de ses competences, en l'occurence la deuxieme qui est vide
	 * @param nbChange Numero de la competence a changer (soit 1 pour la premiere, 2 pour la 2eme)
	 */
	public static void changeCompetence(Personnage p, int nbChange) {
		nbChange--;
		p.setCompetence(competenceAleatoire(p), nbChange);
	}
}
