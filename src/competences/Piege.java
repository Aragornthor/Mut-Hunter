package competences;

public class Piege implements Competences {
	private String element;
	private int effet;
	private int duree;
	
	public Piege() {
		this.element = "deplacement";
		this.effet = 0;
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
