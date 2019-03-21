package competences;

public class Acide implements Competences {
	private String element;
	private int effet;
	private int duree;
	
	public Acide() {
		this.element = "energie";
		this.effet = -10; //Perte d'énergie (nettoyage) -> 3 tours
		this.duree = 3;
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
