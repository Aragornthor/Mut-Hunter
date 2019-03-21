package classes;

import competences.Competences;
import competences.IEM;

public class Chasseur implements Personnage {
	private int energie;
	private int maxEnergie;
	private Competences[] competences;
	private Position position;
	private int deplacement;
	
	public Chasseur() {
		this.energie = 75;
		this.position = new Position(0,0);
		this.competences = new Competences[] {new IEM(), null};
		this.deplacement = 1;
	}
	
	@Override
	public String toString() {
		return "["+this.energie+":"+this.competences.toString()+":"+this.position.toString()+":"+this.deplacement+"]";
	}

	public int getEnergie() {
		return energie;
	}
	
	public int getMaxEnergie() {
		return maxEnergie;
	}
	
	public Competences[] getCompetences() {
		return competences;
	}

	public Position getPosition() {
		return position;
	}

	public int getDeplacement() {
		return deplacement;
	}

	@Override
	public void seDeplace() {
		
	}

	@Override
	public void rechargeEnergie() {
		this.rechargeEnergie(10);;
	}

	@Override
	public void rechargeEnergie(int i) {
		this.energie += i;
		if(this.energie > this.maxEnergie) this.energie = this.maxEnergie;
	}
	
	
}
