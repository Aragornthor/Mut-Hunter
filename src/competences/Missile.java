package competences;

public class Missile implements Competences {
	private int id = 2;
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
