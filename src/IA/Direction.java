package IA;

/**
 * 
 * @author Quentin Delmarre, Xavier Lezzoche
 *
 */
public enum Direction {
	HAUT('z',0), BAS('s',1), GAUCHE('q',2), DROITE('d',3);
	
	private char val;
	private int dir;
	
	/**
	 * Instancie l'énumération Direction
	 * @param c La touche associée à la Direction
	 * @param dir La valeur associée à la Direction
	 */
	private Direction(char c, int dir) {
		this.val = c;
		this.dir = dir;
	}
	
	/**
	 * 
	 * @return Renvoie la touche associée à la Direction
	 */
	public char getDirection() { return val; }

	/**
	 * 
	 * @return Renvoie la valeur associée à la Direction
	 */
	public int getNumDir() { return dir; }
}
