package classes;

import javafx.scene.image.Image;

/**
 * 
 * @author Xavier Lezzoche
 *
 */
public enum TypeCase {

	VIDE(new Image("file:ressources/images/vide.png")), PORTAIL(new Image("file:ressources/images/portail.png")), LOOT(new Image("file:ressources/images/loot.png")), PIEGE(null);
	
	private Image image;
	
	private TypeCase(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return this.image;
	}
	
}
