package IA;

public enum Direction {
	HAUT('z',0), BAS('s',1), GAUCHE('q',2), DROITE('d',3);
	
	private char val;
	private int dir;
	
	private Direction(char c, int dir) {
		this.val = c;
		this.dir = dir;
	}
	
	public char getDirection() { return val; }
	
	public int getNumDir() { return dir; }
}
