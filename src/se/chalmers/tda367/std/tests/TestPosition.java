package se.chalmers.tda367.std.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.tda367.std.utilities.Position;

/**
 * Unit test for the position. 
 * Only tests the non-trivial methods.
 * @author Emil Edholm
 * @date   Apr 24, 2012
 */
public class TestPosition {

	@Test
	public void testCalculateDistance() {
		Position p1 = Position.valueOf(0, 0);
		Position p2 = Position.valueOf(-10, 0);
		Position p3 = Position.valueOf(10, 0);
		// The distance between the above points is obviously 10.
		
		assertTrue(Double.compare(Position.calculateDistance(p1, p2), 10D) == 0);
		assertTrue(Double.compare(Position.calculateDistance(p1, p3), 10D) == 0);
		assertTrue(Double.compare(Position.calculateDistance(p2, p2), 0D) == 0);
	}

}
