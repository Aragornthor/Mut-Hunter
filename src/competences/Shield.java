package competences;

public class Shield implements Competences {
	private int id = 5;
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
