package classes;

public class Case {

	private TypeCase type;
	private boolean estDecouvert;
	private int tempsDecouvert;
	private boolean loot;
	
	public Case() {
		this.loot = false;
		this.type = TypeCase.NORMAL;
		this.estDecouvert = false;
		this.tempsDecouvert = 0;
	}

	public boolean getLoot() {
		return this.loot;
	}
	
	public void changeLoot() {
		if(this.loot) this.loot = false;
		else this.loot = true;
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
