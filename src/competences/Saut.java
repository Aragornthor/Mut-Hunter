package competences;

public class Saut implements CompetencesMonster {
	private String element;
	private int effet;
	private int duree;
	
	public Saut() {
		this.element = "deplacement";
		this.effet = 0; //Immobilisation -> 2 tours
		this.duree = 2;
	}

	public String getElement() {
		return element;
	}

	public int getEffet() {
		return effet;
	}

	public int getDuree() {
		return duree;
	}
	
	@Override
	public String toString() {
		return "["+this.element+":"+this.effet+":"+this.duree+"]";
	}
}
