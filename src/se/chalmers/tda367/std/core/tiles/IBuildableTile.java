package se.chalmers.tda367.std.core.tiles;

import com.google.inject.ImplementedBy;

/**
 * Interface to represent a buildable tile. i.e. a tile that a tower can be built on.
 * @author Emil Johansson
 * @date Mar 26, 2012
 * @modified Emil Edholm (12 mar, 2012)
 */
@ImplementedBy(BuildableTile.class)
public interface IBuildableTile extends IBoardTile {
}
