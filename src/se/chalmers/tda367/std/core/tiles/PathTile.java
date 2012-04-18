package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Class for representing a walkable tile.
 * @author Emil Johansson
 * @date Mar 26, 2012
 */
public class PathTile extends AbstractTile implements IWalkableTile, Comparable<PathTile>{

	//TODO modified to test pathfinding
	int tileValue;
	/**
	 * @param sprite
	 * @param tileValue
	 */
	public PathTile(Sprite sprite, int tileValue) {
		super(sprite);
		this.tileValue = tileValue;
	}
	
	@Override
	public String toString(){
		return "_";
	}
	public int getTileValue(){
		return this.tileValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PathTile tile) {
		if (this.getTileValue() < tile.getTileValue()){
			return -1;
		} else if (this.getTileValue() > tile.getTileValue()){
			return 1;
		} else {
			return 0;
		}
	}
	

}
