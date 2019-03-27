package competences;

public class IEM  implements Competences {
	private int id = 1;
	private String element;
	private int effet;
	private int duree;
	
	public IEM() {
		this.element = "deplacement";
		this.effet = 0;
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
