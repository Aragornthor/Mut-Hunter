package competences;

public class IEM  implements Competences {
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
	
	@Override
	public String toString() {
		return "["+this.element+":"+this.effet+":"+this.duree+"]";
	}
}
