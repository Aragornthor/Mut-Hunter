package classes;

import competences.Competences;
import competences.Shield;

public class Personnage {
	
	private int energie;
	private int maxEnergie;
	private Position position;
	private Competences[] competences;
	private int deplacement;
	private int vie;
	
	public Personnage() {
		this.energie = 75;
		this.position = new Position(9,9);
		this.competences = new Competences[] {new Shield(), null};
		this.deplacement = 1;
		this.vie = 1;
	}
	
	public int getEnergie() {
		return energie;
	}

	public int getMaxEnergie() {
		return maxEnergie;
	}

	public Position getPosition() {
		return position;
	}

	public Competences[] getCompetences() {
		return competences;
	}

	public int getDeplacement() {
		return deplacement;
	}

	public int getVie() {
		return vie;
	}
	
	public void seDeplace() {
		
		
	}

	public void rechargeEnergie() {
		rechargeEnergie(10);
	}

	public void rechargeEnergie(int i) {
		if((this.energie + i) > maxEnergie) this.energie = maxEnergie;
		else this.energie += i;
	}
}
