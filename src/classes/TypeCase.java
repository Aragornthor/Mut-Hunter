package classes;

/**
 * Cette classe à pour but de définir les différents type de case avec leurs caractères.
 * @author Bankaert Benoit
 * @version 1.0
 */
public enum TypeCase {

	NORMAL(' '),LOOT('O'),PORTAIL('P'),MONSTRE('M'),MONSTRE_HIDE(' '),CHASSEUR('C'),CHASSEUR_HIDE(' ');
	
	private char icon;
	
	private TypeCase(char icon) {
		this.icon = icon;
	}
	
	/**
	 * 
	 * @return le caractere du type.
	 */
	public char getIcon() {
		return this.icon;
	}
}
