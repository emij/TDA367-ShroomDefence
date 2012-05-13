package se.chalmers.tda367.std.factories;

/**
 * A general interface for defining different factories.
 * @author Emil Edholm
 * @date Mar 22, 2012
 */
public interface IFactory<T, E> {
	public T create(E param);
}
