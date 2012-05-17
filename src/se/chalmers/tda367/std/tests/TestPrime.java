package se.chalmers.tda367.std.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.tda367.std.utilities.Prime;

/**
 * Unit Test for the Prime class.
 * @author Emil Edholm
 * @date   May 17, 2012
 */
public class TestPrime {

	/**
	 * Test method for {@link se.chalmers.tda367.std.utilities.Prime#isPrime(int)}.
	 */
	@Test
	public void testIsPrime() {
		assertFalse(Prime.isPrime(1));
		assertTrue(Prime.isPrime(2));
		assertTrue(Prime.isPrime(3));
		assertFalse(Prime.isPrime(4));
		assertTrue(Prime.isPrime(5));
		assertFalse(Prime.isPrime(6));
		assertFalse(Prime.isPrime(9));
		assertTrue(Prime.isPrime(23));
		assertTrue(Prime.isPrime(37));
		assertFalse(Prime.isPrime(38));
	}

	/**
	 * Test method for {@link se.chalmers.tda367.std.utilities.Prime#nextPrime(int)}.
	 */
	@Test
	public void testNextPrime() {
		int prime = Prime.nextPrime(1);
		assertTrue(prime == 2);
		prime = Prime.nextPrime(prime);
		assertTrue(prime == 3);
		prime = Prime.nextPrime(prime);
		assertTrue(prime == 5);
		
		prime = Prime.nextPrime(83);
		assertTrue(prime == 89);
	}

}
