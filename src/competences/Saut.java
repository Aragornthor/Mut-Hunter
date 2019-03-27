package competences;

public class Saut implements Competences {
	private int id = 4;
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
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "["+this.element+":"+this.effet+":"+this.duree+"]";
	}
	
	@Override
	public boolean equals(Competences c) {
		return this.id == c.getId();
	}
}
