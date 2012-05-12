package se.chalmers.tda367.std.core.tiles;

import com.google.inject.ImplementedBy;

/**
 * Interface to represent a tile that is "walkable", i.e. a path for enemies
 * @author Emil Johansson
 * @date Mar 26, 2012
 * @modified Emil Edholm (12 mar, 2012)
 */
@ImplementedBy(PathTile.class)
public interface IWalkableTile extends IBoardTile {

}
