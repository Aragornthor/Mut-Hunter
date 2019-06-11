package classes;

import javafx.scene.image.Image;

/**
 * 
 * @author Xavier Lezzoche
 *
 */
public enum TypeTerrain {

	PLAINE(2,1,new Image("file:ressources/images/plaines.png"),new Image("file:ressources/images/plainesNonVisible.png")), 
	FORET(1,2,new Image("file:ressources/images/foret.png"), new Image("file:ressources/images/foretNonVisible.png")), 
	MONTAGNE(3,3,new Image("file:ressources/images/montagnes.png"), new Image("file:ressources/images/montagnesNonVisible.png")), 
	EAU(2,2,new Image("file:ressources/images/eau.png"), new Image("file:ressources/images/eauNonVisible.png")), 
	DESERT(1,1,new Image("file:ressources/images/desert.png"), new Image("file:ressources/images/desert.png")), 
	VILLE(1,2,new Image("file:ressources/images/ville2.png"), new Image("file:ressources/images/ville2.png")), 
	PLAINE_ENNEIGEE(1,1,new Image("file:ressources/images/plainesEnneigee.png"), new Image("file:ressources/images/plainesEnneigee.png")),
	MONTAGNE_ENNEIGEE(1,3,new Image("file:ressources/images/montagnesEnneigees.png"), new Image("file:ressources/images/montagnesEnneigees.png")),
	FORET_ENNEIGEE(1,3,new Image("file:ressources/images/foretEnneigee.png"), new Image("file:ressources/images/foretEnneigee.png")),
	LAC_GELE(1,2,new Image("file:ressources/images/eauGelee.png"), new Image("file:ressources/images/eauGelee.png")), 
	PIC_ARID(1,3,new Image("file:ressources/images/picAride.png"), new Image("file:ressources/images/picAride.png")),
	RIVIERE_ASSECHE(1,1,new Image("file:ressources/images/plaines.png"), new Image("file:ressources/images/plaines.png")), 
	CENTRALE(1,2,new Image("file:ressources/images/plaines.png"), new Image("file:ressources/images/plaines.png")), 
	MARAIS(1,2,new Image("file:ressources/images/plaines.png"), new Image("file:ressources/images/plaines.png"));
	
	Image imageVisible;
	Image imageNonVisible;
	private int vision;
	private int deplacement;
	
	/**
	 * Instancie l'énumération TypeTerrain
	 * @param vision Distance de vision depuis la case courante
	 * @param deplacement Coût en déplacement à travers la case courante
	 * @param imageVisible Image de référence lors d'une présence dans la distance de vision
	 * @param imageNonVisible Image de référence lors d'une présence hors de la distance de vision
	 */
	private TypeTerrain(int vision, int deplacement, Image imageVisible, Image imageNonVisible) {
		this.vision = vision;
		this.deplacement = deplacement;
		this.imageVisible = imageVisible;
		this.imageNonVisible = imageNonVisible;
	}
	
	/**
	 * 
	 * @return Renvoie la distance de vision depuis la case courante
	 */
	public int getVision() {
		return this.vision;
	}
	
	/**
	 * 
	 * @return Renvoie le coût de déplacement de la case courante
	 */
	public int getDeplacement() {
		return this.deplacement;
	}
	
	/**
	 * 
	 * @return Renvoie l'image de référence lors d'une présence dans la distance de vision
	 */
	public Image getImageVisible() {
		return this.imageVisible;
	}
	
	/**
	 * 
	 * @return Image de référence lors d'une présence hors de la distance de vision
	 */
	public Image getImageNonVisible() {
		return this.imageNonVisible;
	}
	
}
