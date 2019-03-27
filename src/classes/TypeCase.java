package classes;

public enum TypeCase {
	NORMAL(' '),LOOT('O'),PORTAIL('P'),MONSTRE('M'),MONSTRE_HIDE(' '),CHASSEUR('C'),CHASSEUR_HIDE(' ');
	
	private char icon;
	
	private TypeCase(char icon) {
		this.icon = icon;
	}
	
	public char getIcon() {
		return this.icon;
	}
}
