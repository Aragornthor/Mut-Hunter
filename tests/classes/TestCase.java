/**
 * 
 */
package classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Bankaert Benoit
 *
 */
public class TestCase {

	public Case c1,c2,c3,c4;
	
	@Before
	public void initialisation() {
		c1 = new Case();
		c2 = new Case();
		c3 = new Case();
		c4 = new Case();
	}
	
	public boolean estNormale(Case c) {
		if(!c.getEstChasseur() && !c.getEstMonstre() && !c.getEstPortail() && !c.getLoot()) return true;
		return false;
	}
	@Test
	public void testChangeLoot() {
		assertTrue(c1.getLoot() == false);
		assertTrue(estNormale(c1));
		c1.changeLoot();
		assertTrue(c1.getLoot());
		assertTrue(c1.getLoot());
	}
	
	@Test
	public void testDecouvrirCase() {
		assertTrue(c2.decouvrirCase());
		assertFalse(c2.decouvrirCase());
	}
	
	@Test
	public void testTempsDecouvert() {
		assertEquals(c3.getTempsDecouvert(),0);
		c3.setTempsDecouvert();
		assertEquals(c3.getTempsDecouvert(),1);
	}

}
