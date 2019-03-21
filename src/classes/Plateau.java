package classes;

public class Plateau {

	private Case[][] plateau;

	public Plateau() {
		this.plateau = new Case[10][10];
	}
	
	public void initPlateau() {
		for(int i = 0; i < this.plateau.length; i++) {
			for(int j = 0; j < this.plateau[i].length; j++) {
				this.plateau[i][j] = new Case();
			}
		}
	}
	
	public void affichePlateau() {
		System.out.print("╔═╦═╦═╦═╦═╦═╦═╦═╦═╦═╗\n║");
		for(int i = 0; i < this.plateau.length; i++) {
			for(int j = 0; j < this.plateau[i].length; j++) {
				System.out.print(this.plateau[i][j].getIcon()+"║");
			}
			System.out.print("\n\n");
		}
	}
	
	public void setType() {
		for(int i = 0; i < this.plateau.length; i++) {
			for(int j = 0; j < this.plateau[i].length; j++) {
				this.plateau[i][j].setType(TypeCase.LOOT);;
			}
		}
	}

}
