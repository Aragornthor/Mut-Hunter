package classes;

public class Case {

	private TypeCase type;
	private boolean estDecouvert;
	private int tempsDecouvert;
	private boolean loot;
	private boolean estPortail;
	private boolean estChasseur;
	private boolean estMonstre;
	
	public Case() {
		this.loot = false;
		this.type = TypeCase.NORMAL;
		this.estDecouvert = false;
		this.tempsDecouvert = 0;
		this.estPortail = false;
		this.estChasseur = false;
		this.estMonstre = false;
	}

	public boolean getEstChasseur() {
		return this.estChasseur;
	}
	
	public void setEstChasseur(boolean estChasseur) {
		this.estChasseur = estChasseur;
	}	
	
	public boolean getEstMonstre() {
		return this.estMonstre;
	}
	
	public void setEstMonstre(boolean estMonstre) {
		this.estMonstre = estMonstre;
	}
	
	public boolean getEstPortail() {
		return this.estPortail;
	}
	
	public void setEstPortail(boolean portail) {
		this.estPortail = portail;
	}
	
	public boolean getLoot() {
		return this.loot;
	}
	
	public void changeLoot() {
		if(this.loot) {
			this.loot = false;
			this.type = TypeCase.NORMAL;
		}else {
			this.loot = true;
			this.type = TypeCase.LOOT;
		}
	}
	public TypeCase getType() {
		return this.type;
	}
	
	public void setType(TypeCase type) {
		this.type = type;
	}
	
	public boolean getEstDecouvert() {
		return estDecouvert;
	}

	public boolean decouvrirCase() {
		if(this.estDecouvert) return false;
		else this.estDecouvert = true;
		return true;
	}

	public int getTempsDecouvert() {
		return tempsDecouvert;
	}

	public void setTempsDecouvert() {
		this.tempsDecouvert +=1;
	}

	public char getIcon() {
		return this.type.getIcon();
	}


}
