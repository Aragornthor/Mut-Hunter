package classes;

public enum Competences {
	/*IEM, TRAP, MISSILE(""),*/ SHIELD(true), SAUT("deplacement", 0, 2), ACIDE("energie", -10, 3);
	
	private String elm;
	private int deplacement;
	private int duree;
	private boolean protection;
	
	public Competences(String elm, int deplacement, int duree) {
		this.elm = elm;
		this.deplacement = deplacement;
		this.duree = duree;
	}
	
	public Competences(boolean protection) {
		this.protection = protection;
	}
}
