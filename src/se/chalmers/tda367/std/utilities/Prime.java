package se.chalmers.tda367.std.utilities;

/**
 * A class used for dealing with prime numbers.
 * @author Emil Edholm
 * @date   May 17, 2012
 */
public final class Prime {
	private Prime(){} // Prevents instantiation
	
	/**
	 * Tests if a given number is a prime number.
	 * @param x - the prime number candidate.
	 * @return true if {@code x} is a prime number, else false.
	 */
	public static boolean isPrime(int x) {
		if(x == 2) return true;
		if(x % 2 == 0 || x < 2) return false;
		
		int top = (int)Math.sqrt(x);
		for(int i = 3; i <= top; i += 2) {
			if(x % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns the next prime number after a given number.
	 * Note that if {@code n} is a prime number, it will still return the next prime.
	 * @param n - the minimum value to check from.
	 * @return a prime number bigger than {@code n}
	 */
	public static int nextPrime(int n) {
		while(!isPrime(++n));
		return n;
	}
}
