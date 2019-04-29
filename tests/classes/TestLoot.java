package classes;

import static org.junit.Assert.*;

import org.junit.Test;

import competences.Competences;

public class TestLoot {

	public Personnage p = new Chasseur(new Position(0,0));
	
	@Test
	public void testChangeCompetence() {
		Competences[] comp = p.getCompetences();
		assertTrue(comp[1] == null);
		System.out.println("Avant");
		System.out.println(p);
		Loot.changeCompetence(p);
		System.out.println("Apres");
		System.out.println(p);
		assertTrue(comp[1] != null);
		int id1 = comp[0].getId();
		int id2 = comp[1].getId();
		Loot.changeCompetence(p, 2);
		System.out.println("Enfin");
		System.out.println(p);
		assertTrue(comp[1].getId() != id1 && comp[1].getId() != id2);
	}

}
