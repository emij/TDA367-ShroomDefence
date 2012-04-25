package se.chalmers.tda367.std.utilities;

/**
 * A generic filter interface useful when filtering various things.
 * @author Emil Edholm
 * @date   Apr 25, 2012
 */
public interface Filter<T> {
		/**
		 * The filter method.
		 * @param object the object to check.
		 * @return true if the position is accepted, else false.
		 */
		public boolean accept(T object);
}
