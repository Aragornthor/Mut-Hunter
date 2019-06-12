package UI;

import classes.Chasseur;
import classes.Monstre;
import classes.Plateau;
import javafx.scene.canvas.GraphicsContext;

public class DisplayPlateau {
	
	private Plateau jeu;
	private Chasseur chasseur;
	private Monstre monstre;

	public DisplayPlateau(Plateau p, Chasseur c, Monstre m) {
		this.jeu = p;
		this.chasseur = c;
		this.monstre = m;
	}
	
	/**
	 * Permet l'affichage du plateau en entier
	 * @param p Plateau de jeu
	 */
	public void affichagePlateau(GraphicsContext p) {
		p.clearRect(0, 0, p.getCanvas().getWidth(), p.getCanvas().getHeight());
		for(int i=0; i<jeu.getLargeur(); i++) {
			for(int j=0; j<jeu.getHauteur(); j++) {
				
				p.drawImage(jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible(),
						((9-j))*(jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2),
						  j*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6));
				
				if(jeu.getPlateau()[i][j].getEstChasseur()) {
					p.drawImage(chasseur.getImage(),
							((9-j))*(chasseur.getImage().getWidth()/2)+(i*chasseur.getImage().getWidth()/2),
							  j*chasseur.getImage().getHeight()/6+(i*chasseur.getImage().getHeight()/6));
				}
				if(jeu.getPlateau()[i][j].getEstMonstre()) {
					p.drawImage(monstre.getImage(),
							((9-j))*(monstre.getImage().getWidth()/2)+(i*monstre.getImage().getWidth()/2),
							  j*monstre.getImage().getHeight()/6+(i*monstre.getImage().getHeight()/6));
				}
				if(jeu.getPlateau()[i][j].getEstPortail()) {
					p.drawImage(jeu.getPlateau()[i][j].getTypeCase().getImage(),
							((9-j))*(jeu.getPlateau()[i][j].getTypeCase().getImage().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeCase().getImage().getWidth()/2),
							  j*jeu.getPlateau()[i][j].getTypeCase().getImage().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeCase().getImage().getHeight()/6));
				}
			}
		}
	}
	
	/**
	 * Cacher les caches hors du champ de vision du Chasseur
	 * @param p Le plateau
	 */
	public void affichagePlateauVisionChasseur(GraphicsContext p) {
		p.clearRect(0, 0, p.getCanvas().getWidth(), p.getCanvas().getHeight());
		for(int i=0; i<jeu.getLargeur(); i++) {
			for(int j=0; j<jeu.getHauteur(); j++) {
				if(i>chasseur.getPosition().getX()-jeu.getCase(chasseur.getPosition()).getTypeTerrain().getVision()
						&& i<chasseur.getPosition().getX()+jeu.getCase(chasseur.getPosition()).getTypeTerrain().getVision()
						&& j>chasseur.getPosition().getY()-jeu.getCase(chasseur.getPosition()).getTypeTerrain().getVision()
						&& j<chasseur.getPosition().getY()+jeu.getCase(chasseur.getPosition()).getTypeTerrain().getVision()) {
					p.drawImage(jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible(),
							((9-j))*(jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2),
							  j*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6));
					
					if(jeu.getPlateau()[i][j].getEstChasseur()) {
						p.drawImage(chasseur.getImage(),
								((9-j))*(chasseur.getImage().getWidth()/2)+(i*chasseur.getImage().getWidth()/2),
								  j*chasseur.getImage().getHeight()/6+(i*chasseur.getImage().getHeight()/6));
					}
					if(jeu.getPlateau()[i][j].getEstMonstre()) {
						p.drawImage(monstre.getImage(),
								((9-j))*(monstre.getImage().getWidth()/2)+(i*monstre.getImage().getWidth()/2),
								  j*monstre.getImage().getHeight()/6+(i*monstre.getImage().getHeight()/6));
					}
				} else {
					p.drawImage(jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible(),
							((9-j))*(jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getWidth()/2),
							  j*jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getHeight()/6));
					if(jeu.getPlateau()[i][j].getEstPortail()) {
						p.drawImage(jeu.getPlateau()[i][j].getTypeCase().getImage(),
								((9-j))*(jeu.getPlateau()[i][j].getTypeCase().getImage().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeCase().getImage().getWidth()/2),
								  j*jeu.getPlateau()[i][j].getTypeCase().getImage().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeCase().getImage().getHeight()/6));
					}
					if(jeu.getPlateau()[i][j].getLoot()) {
						p.drawImage(jeu.getPlateau()[i][j].getTypeCase().getImage(),
								((9-j))*(jeu.getPlateau()[i][j].getTypeCase().getImage().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeCase().getImage().getWidth()/2),
								  j*jeu.getPlateau()[i][j].getTypeCase().getImage().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeCase().getImage().getHeight()/6));
					}
				}
			}
		}
	}
	
	/**
	 * Cacher les caches hors du champ de vision du Monstre
	 * @param p Le plateau
	 */
	public void affichagePlateauVisionMonstre(GraphicsContext p) {
		p.clearRect(0, 0, p.getCanvas().getWidth(), p.getCanvas().getHeight());
		for(int i=0; i<jeu.getLargeur(); i++) {
			for(int j=0; j<jeu.getHauteur(); j++) {
				if(i>monstre.getPosition().getX()-jeu.getCase(monstre.getPosition()).getTypeTerrain().getVision()
						&& i<monstre.getPosition().getX()+jeu.getCase(monstre.getPosition()).getTypeTerrain().getVision()
						&& j>monstre.getPosition().getY()-jeu.getCase(monstre.getPosition()).getTypeTerrain().getVision()
						&& j<monstre.getPosition().getY()+jeu.getCase(monstre.getPosition()).getTypeTerrain().getVision()) {
					p.drawImage(jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible(),
							((9-j))*(jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2),
							  j*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6));
					
					if(jeu.getPlateau()[i][j].getEstChasseur()) {
						p.drawImage(chasseur.getImage(),
								((9-j))*(chasseur.getImage().getWidth()/2)+(i*chasseur.getImage().getWidth()/2),
								  j*chasseur.getImage().getHeight()/6+(i*chasseur.getImage().getHeight()/6));
					}
					if(jeu.getPlateau()[i][j].getEstMonstre()) {
						p.drawImage(monstre.getImage(),
								((9-j))*(monstre.getImage().getWidth()/2)+(i*monstre.getImage().getWidth()/2),
								  j*monstre.getImage().getHeight()/6+(i*monstre.getImage().getHeight()/6));
					}
				} else {
					p.drawImage(jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible(),
							((9-j))*(jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getWidth()/2),
							  j*jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getHeight()/6));
					if(jeu.getPlateau()[i][j].getEstPortail()) {
						p.drawImage(jeu.getPlateau()[i][j].getTypeCase().getImage(),
								((9-j))*(jeu.getPlateau()[i][j].getTypeCase().getImage().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeCase().getImage().getWidth()/2),
								  j*jeu.getPlateau()[i][j].getTypeCase().getImage().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeCase().getImage().getHeight()/6));
					}
					if(jeu.getPlateau()[i][j].getLoot()) {
						p.drawImage(jeu.getPlateau()[i][j].getTypeCase().getImage(),
								((9-j))*(jeu.getPlateau()[i][j].getTypeCase().getImage().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeCase().getImage().getWidth()/2),
								  j*jeu.getPlateau()[i][j].getTypeCase().getImage().getHeight()/6+(i*jeu.getPlateau()[i][j].getTypeCase().getImage().getHeight()/6));
					}
				}
			}
		}
	}
}
