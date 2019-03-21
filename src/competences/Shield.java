package competences;

public class Shield implements CompetencesMonster {
	private String element;
	private int effet;
	private int duree;
	
	public Shield() {
		this.element = "protection";
		this.effet = 1; //Protection totale -> 1 tour
		this.duree = 1;
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
