package competences;

public enum Statut {
	
	Stun(1),Acide(3),Shield(2),Vivant(0),Mort(0);
	
	private int nbTours;
	
	private Statut(int i) {
		this.nbTours = i;
	}
	
	public void setTour(int i) {
		this.nbTours = i;
	}
	
	public int getNbTour() {
		return this.nbTours;
	}
}
