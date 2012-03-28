package se.chalmers.tda367.std.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.tda367.std.core.Score;

/**
 * Unit test for {@link se.chalmers.tda367.std.core.Score}.
 * @author Emil Edholm
 * @date   25 mar 2012
 */
public class TestScore {

	/**
	 * Test method for {@link se.chalmers.tda367.std.core.Score#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		Score s1 = new Score("Apa", 10);
		Score s2 = new Score("apa", 10);
		Score s3 = new Score("Apa", 10);
		
		assertTrue(s1.hashCode() == s3.hashCode());
		assertTrue(s1.hashCode() != s2.hashCode());
	}

	/**
	 * Test method for {@link se.chalmers.tda367.std.core.Score#compareTo(se.chalmers.tda367.std.core.Score)}.
	 */
	@Test
	public void testCompareTo() {
		Score s1 = new Score("Apa", 10);
		Score s2 = new Score("apa", 10);
		Score s3 = new Score("Bepa", 20);
		
		assertTrue(s2.compareTo(s1) > 0);
		assertTrue(s1.compareTo(s3) < 0);
		assertTrue(s1.compareTo(s1) == 0);
	}

	/**
	 * Test method for {@link se.chalmers.tda367.std.core.Score#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Score s1 = new Score("Apa", 10);
		Score s2 = new Score("apa", 10);
		Score s3 = new Score("Bepa", 20);
		Score s4 = new Score("Bepa", 20);
		
		assertFalse(s1.equals(s2));
		assertFalse(s2.equals(s3));
		assertTrue(s3.equals(s4));
		assertFalse(s3.equals(this));
		assertTrue(s3.equals(s3));
	}

}
