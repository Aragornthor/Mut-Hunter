package classes;

/**
 * Cette classe permet de gérer la position des personnages
 * @author Quentin Delmarre, Benoit Bankaert
 *
 */
public class Position {
	private int x;
	private int y;
	
	/**
	 * 
	 * @param x Prend en paramètre l'abscisse du Personnage
	 * @param y Prend en paramètre l'ordonnée du Personnage
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return Renvoie la forme textuelle de la Position du personnage
	 */
	@Override
	public String toString() {
		return "["+this.x+";"+this.y+"]";
	}

	/**
	 * 
	 * @return Renvoie l'abscisse du Personnage
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return Renvoie l'ordonnée du Personnage
	 */
	public int getY() {
		return y;
	}

	/**
	 * Modifie l'abscisse de la Postion
	 * @param x Prend en paramètre l'abscisse du personnage
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Modifie l'ordonnée de la Postion
	 * @param y Prend en paramètre l'ordonnée du personnage
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * 
	 * @param p Prend en paramètre la Position à comparer
	 * @return Retourne VRAI si l'abscisse et l'ordonnée sont identiques
	 */
	public boolean equals(Position p) {
		return (this.x == p.getX() && this.y == p.getY());
	}
}