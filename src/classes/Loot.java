package classes;

import java.util.Random;

import competences.*;

public class Loot {
	private Competences[] competencesChasseur = new Competences[]{new IEM(), new Missile(), new Piege()};
	private Competences[] competencesMonstre = new Competences[]{new Shield(), new Acide(), new Saut()};
	
	Competences competenceAleatoire(Personnage p) {
		Random rand = new Random();
		Competences[] comp = p.getCompetences();
		int i=0;
		if(p.type == "chasseur") {
			if(comp[1] == null) return comp[rand.nextInt(competencesChasseur.length-2)+1];
			else{
				while(comp[0].equals(competencesChasseur[i]) || comp[1].equals(competencesChasseur[i])){
					i++;
				}
				return competencesChasseur[i];
			}
		}
		else if(p.type == "monstre") {
			if(comp[1] == null) return comp[rand.nextInt(competencesMonstre.length-2)+1];
			else{
				while(comp[0].equals(competencesMonstre[i]) || comp[1].equals(competencesMonstre[i])){
					i++;
				}
				return competencesMonstre[i];
			}			
		}
	}
}
