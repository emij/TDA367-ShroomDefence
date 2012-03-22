package se.chalmers.tda367.std.factories;

/**
 * A general interface for defining different factories.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public interface IFactory<T> {
	public T create(Object param);
}
