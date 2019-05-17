package IA;

public enum Direction {
	HAUT('z'), BAS('s'), GAUCHE('q'), DROITE('d');
	
	private char val;
	
	private Direction(char c) {
		this.val = c;
	}
	
	public char getDirection() { return val; }
}
