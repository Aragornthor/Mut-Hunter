package classes;

public class Monstre implements Personnage {
	
	private int energie;
	private int maxEnergie;
	private Position position;
	private Competences[] competences;
	private int deplacement; 
	
	public Monstre() {
		this.energie = 75;
		this.position = new Position(9,9);
		this.competences = new Competences[] {Competences.SHIELD, null};
		this.deplacement = 1;
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



	@Override
	public void seDeplace() {
		
		
	}

	@Override
	public void rechargeEnergie() {
		rechargeEnergie(10);
	}

	@Override
	public void rechargeEnergie(int i) {
		if((this.energie + i) > maxEnergie) this.energie = maxEnergie;
		else this.energie += i;
	}

	
	
}
