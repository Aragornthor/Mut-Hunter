package competences;

public enum Statut {
	
	Stun(1),Acide(3),Shield(2),Vivant(0),Mort(0);
	
	private int nbTours;
	
	/**
	 * Instancie l'énumération Statut
	 * @param i La durée de chaque Statut
	 */
	private Statut(int i) {
		this.nbTours = i;
	}
	
	/**
	 * Permet de changer la DUREE du Statut courant
	 * @param i Nouvelle DUREE à attribuer
	 */
	public void setTour(int i) {
		this.nbTours = i;
	}
	
	/**
	 * 
	 * @return Renvoie la DUREE du Statut courant
	 */
	public int getNbTour() {
		return this.nbTours;
	}
}
