package competences;

public class Missile implements CompetencesHunter {
	private String element;
	private int effet;
	private int duree;
	
	public Missile() {
		this.element = "vie";
		this.effet = -1; //Mort instantanée
		this.duree = 0;
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
