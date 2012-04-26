package se.chalmers.tda367.std.core.anno;

/**
 * Optional annotation useful when describing different types of tiles.
 * @author Emil Edholm
 * @date   Apr 22, 2012
 */
public @interface Tile {
	String name();
	Class<?> type();
}
