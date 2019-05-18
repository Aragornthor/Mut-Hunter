package classes;

import javafx.scene.image.Image;

public enum TypeTerrain {

	PLAINE(0,1,new Image("file:ressources/images/plaines.png")), 
	FORET(0,2,new Image("file:ressources/images/foret.png")), 
	MONTAGNE(0,3,new Image("file:ressources/images/montagnes.png")), 
	EAU(0,2,new Image("file:ressources/images/eau.png")), 
	DESERT(0,1,new Image("file:ressources/images/plaines.png")), 
	VILLE(0,2,new Image("file:ressources/images/plaines.png")), 
	PLAINE_ENNEIGEE(0,1,new Image("file:ressources/images/plaines.png")),
	MONTAGNE_ENNEIGEE(0,3,new Image("file:ressources/images/plaines.png")), 
	LAC_GELE(0,2,new Image("file:ressources/images/plaines.png")), 
	PIC_ARID(0,3,new Image("file:ressources/images/plaines.png")),
	RIVIERE_ASSECHE(0,1,new Image("file:ressources/images/plaines.png")), 
	CENTRALE(0,2,new Image("file:ressources/images/plaines.png")), 
	MARAIS(0,2,new Image("file:ressources/images/plaines.png"));
	
	Image image;
	private int vision;
	private int deplacement;
	
	private TypeTerrain(int vision, int deplacement, Image image) {
		this.vision = vision;
		this.deplacement = deplacement;
		this.image = image;
	}
	
	public int getVision() {
		return this.vision;
	}
	
	public int getDeplacement() {
		return this.deplacement;
	}
	
	public Image getImage() {
		return this.image;
	}
	
}
