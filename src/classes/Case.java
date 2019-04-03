package classes;

/**
 * Cette classe permet de gérer les case du terrain
 * @author Bankaert Benoit
 * @version 1.0
 */
public class Case {

	private boolean estDecouvert;
	private int tempsDecouvert;
	private boolean loot;
	private boolean estPortail;
	private boolean estChasseur;
	private boolean estMonstre;
	private boolean hide;
	
	/**
	 * Initialise une case de type normal.
	 */
	public Case() {
		this.loot = false;
		this.estDecouvert = false;
		this.tempsDecouvert = 0;
		this.estPortail = false;
		this.estChasseur = false;
		this.estMonstre = false;
	}

	/**
	 * @return true si la case est un chasseur, false sinon.
	 */
	public boolean getEstChasseur() {
		return this.estChasseur;
	}
	
	/**
	 * Change le statut de estChasseur.
	 * @param estChasseur est le nouveau statut de estChasseur
	 */
	public void setEstChasseur(boolean estChasseur) {
		this.estChasseur = estChasseur;
	}	
	
	/**
	 * @return true si la case est monstre, false sinon
	 */
	public boolean getEstMonstre() {
		return this.estMonstre;
	}
	
	/**
	 * Change le statut de estMonstre.
	 * @param estMonstre est le nouveau statut de estMonstre
	 */	
	public void setEstMonstre(boolean estMonstre) {
		this.estMonstre = estMonstre;
	}
	
	/**
	 * @return true si la case est un portail, false sinon
	 */
	public boolean getEstPortail() {
		return this.estPortail;
	}
	
	/**
	 * Change le statut de estPortail.
	 * @param portail est le nouveau statut de estPortail
	 */
	public void setEstPortail(boolean portail) {
		this.estPortail = portail;
	}
	
	/**
	 * @return true si la case est un loot, false sinon
	 */
	public boolean getLoot() {
		return this.loot;
	}
	
	/**
	 * Change le type de la case : la case devient normal si la case était un loot et un loot si la case était normal.
	 */
	public void changeLoot() {
		if(this.loot) {
			this.loot = false;
		}else {
			this.loot = true;
		}
	}
	
	public void setLoot(boolean loot) {
		this.loot = loot;
	}
	
	/**
	 * Change le type de la case avec celui passé en parametre.
	 * @param type que l'on souhait mettre à la case
	 */
	
	/**
	 * @return true si la case à était découvert, false sinon
	 */
	public boolean getEstDecouvert() {
		return estDecouvert;
	}

	/**
	 * @return false si la case à déjà était découvert, true sinon
	 */
	public boolean decouvrirCase() {
		if(this.estDecouvert) return false;
		else this.estDecouvert = true;
		return true;
	}

	/**
	 * @return le nombre de tour passé depuis la decouvert de la case
	 */
	public int getTempsDecouvert() {
		return tempsDecouvert;
	}

	/**
	 * Augement le nombre de tour depuis la decouvert de la case
	 */
	public void setTempsDecouvert() {
		this.tempsDecouvert +=1;
	}

	/**
	 * @return le caracetre correspondant au type de la case
	 */
	public char getIcon() {
		if(!this.getHide()) {
			if(this.getEstChasseur()) return 'C';
			else if(this.getEstMonstre()) return 'M';
			else if(this.getEstPortail()) return 'P';
			else if(this.getLoot()) return 'O';
			else return ' ';
		}
		else if(this.getEstPortail()) return 'P';
		else if(this.getLoot()) return 'O';
		else return ' ';
	}
	
	public boolean getHide() {
		return hide;
	}
	
	public void setHide(boolean hide) {
		this.hide = hide;
	}
	
	/**
	 * Rend le personnage caché.
	 * 
	 */
	public void hide() {
		this.setHide(true);
		
	}
	
	/**
	 * Rend le personnage visible.
	 * 
	 */
	public void show() {
		this.setHide(false);	
	}


}
