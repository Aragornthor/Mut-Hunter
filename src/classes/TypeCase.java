package classes;

public enum TypeCase {
	NORMAL(' '),LOOT('O'),PORTAIL('P');
	
	private char icon;
	
	private TypeCase(char icon) {
		this.icon = icon;
	}
	
	public char getIcon() {
		return this.icon;
	}
}
