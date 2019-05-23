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
	
	public void affichagePlateau(GraphicsContext p) {
		p.clearRect(0, 0, p.getCanvas().getWidth(), p.getCanvas().getHeight());
		for(int i=0; i<this.jeu.getLargeur(); i++) {
			for(int j=0; j<this.jeu.getHauteur(); j++) {
				
				p.drawImage(this.jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible(),
						((9-j))*(this.jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2)+(i*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2),
						  j*jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6+(i*this.jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6));
				
				if(this.jeu.getPlateau()[i][j].getEstChasseur()) {
					p.drawImage(this.chasseur.getImage(),
							((9-j))*(this.chasseur.getImage().getWidth()/2)+(i*this.chasseur.getImage().getWidth()/2),
							  j*this.chasseur.getImage().getHeight()/6+(i*this.chasseur.getImage().getHeight()/6));
				}
				if(this.jeu.getPlateau()[i][j].getEstMonstre()) {
					p.drawImage(this.monstre.getImage(),
							((9-j))*(this.monstre.getImage().getWidth()/2)+(i*this.monstre.getImage().getWidth()/2),
							  j*this.monstre.getImage().getHeight()/6+(i*this.monstre.getImage().getHeight()/6));
				}
			}
		}
	}
	
	public void affichagePlateauVisionChasseur(GraphicsContext p) {
		p.clearRect(0, 0, p.getCanvas().getWidth(), p.getCanvas().getHeight());
		for(int i=0; i<this.jeu.getLargeur(); i++) {
			for(int j=0; j<this.jeu.getHauteur(); j++) {
				if(i>this.chasseur.getPosition().getX()-this.jeu.getCase(this.chasseur.getPosition()).getTypeTerrain().getVision()
						&& i<this.chasseur.getPosition().getX()+this.jeu.getCase(this.chasseur.getPosition()).getTypeTerrain().getVision()
						&& j>this.chasseur.getPosition().getY()-this.jeu.getCase(this.chasseur.getPosition()).getTypeTerrain().getVision()
						&& j<this.chasseur.getPosition().getY()+this.jeu.getCase(this.chasseur.getPosition()).getTypeTerrain().getVision()) {
					p.drawImage(this.jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible(),
							((9-j))*(this.jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2)+(i*this.jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2),
							  j*this.jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6+(i*this.jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6));
					
					if(this.jeu.getPlateau()[i][j].getEstChasseur()) {
						p.drawImage(this.chasseur.getImage(),
								((9-j))*(this.chasseur.getImage().getWidth()/2)+(i*this.chasseur.getImage().getWidth()/2),
								  j*this.chasseur.getImage().getHeight()/6+(i*this.chasseur.getImage().getHeight()/6));
					}
					if(this.jeu.getPlateau()[i][j].getEstMonstre()) {
						p.drawImage(this.monstre.getImage(),
								((9-j))*(this.monstre.getImage().getWidth()/2)+(i*this.monstre.getImage().getWidth()/2),
								  j*this.monstre.getImage().getHeight()/6+(i*this.monstre.getImage().getHeight()/6));
					}
				} else {
					p.drawImage(this.jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible(),
							((9-j))*(this.jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getWidth()/2)+(i*this.jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getWidth()/2),
							  j*this.jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getHeight()/6+(i*this.jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getHeight()/6));
				}
			}
		}
	}
	
	public void affichagePlateauVisionMonstre(GraphicsContext p) {
		p.clearRect(0, 0, p.getCanvas().getWidth(), p.getCanvas().getHeight());
		for(int i=0; i<this.jeu.getLargeur(); i++) {
			for(int j=0; j<this.jeu.getHauteur(); j++) {
				if(i>this.monstre.getPosition().getX()-this.jeu.getCase(this.monstre.getPosition()).getTypeTerrain().getVision()
						&& i<this.monstre.getPosition().getX()+this.jeu.getCase(this.monstre.getPosition()).getTypeTerrain().getVision()
						&& j>this.monstre.getPosition().getY()-this.jeu.getCase(this.monstre.getPosition()).getTypeTerrain().getVision()
						&& j<this.monstre.getPosition().getY()+this.jeu.getCase(this.monstre.getPosition()).getTypeTerrain().getVision()) {
					p.drawImage(this.jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible(),
							((9-j))*(this.jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2)+(i*this.jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getWidth()/2),
							  j*this.jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6+(i*this.jeu.getPlateau()[i][j].getTypeTerrain().getImageVisible().getHeight()/6));
					
					if(this.jeu.getPlateau()[i][j].getEstChasseur()) {
						p.drawImage(this.chasseur.getImage(),
								((9-j))*(this.chasseur.getImage().getWidth()/2)+(i*this.chasseur.getImage().getWidth()/2),
								  j*this.chasseur.getImage().getHeight()/6+(i*this.chasseur.getImage().getHeight()/6));
					}
					if(this.jeu.getPlateau()[i][j].getEstMonstre()) {
						p.drawImage(this.monstre.getImage(),
								((9-j))*(this.monstre.getImage().getWidth()/2)+(i*this.monstre.getImage().getWidth()/2),
								  j*this.monstre.getImage().getHeight()/6+(i*this.monstre.getImage().getHeight()/6));
					}
				} else {
					p.drawImage(this.jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible(),
							((9-j))*(this.jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getWidth()/2)+(i*this.jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getWidth()/2),
							  j*this.jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getHeight()/6+(i*this.jeu.getPlateau()[i][j].getTypeTerrain().getImageNonVisible().getHeight()/6));
				}
			}
		}
	}
}
