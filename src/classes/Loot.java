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
	private Competences[] competencesChasseur = new Competences[]{new IEM(), new Missile(), new Piege()};
	private Competences[] competencesMonstre = new Competences[]{new Shield(), new Acide(), new Saut()};
	
	/**
	 * 
	 * @param p Personnage par rapport auquel la competence doit etre selectionnee 
	 * @return Retourne une competence aleatoire selon si le personnage est un chasseur ou un monstre
	 */
	Competences competenceAleatoire(Personnage p) {
		Random rand = new Random();
		Competences[] comp = p.getCompetences();
		int i=0;
		if(p.getType() == "chasseur") {
			if(comp[1] == null) return comp[rand.nextInt(competencesChasseur.length-2)+1];
			else{
				while(comp[0].equals(competencesChasseur[i]) || comp[1].equals(competencesChasseur[i])){
					i++;
				}
				return competencesChasseur[i];
			}
		}
		else if(p.getType() == "monstre") {
			if(comp[1] == null) return comp[rand.nextInt(competencesMonstre.length-2)+1];
			else{
				while(comp[0].equals(competencesMonstre[i]) || comp[1].equals(competencesMonstre[i])){
					i++;
				}
				return competencesMonstre[i];
			}			
		}
		return null;
	}
}
