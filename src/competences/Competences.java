package competences;

/**
 * 
 * L'interface Competences donne toutes les methodes propres aux competences
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public interface Competences {
	public String getElement();
	public int getEffet();
	public int getDuree();
	public int getId();
	public String toString();
	public boolean equals(Competences c);
}
