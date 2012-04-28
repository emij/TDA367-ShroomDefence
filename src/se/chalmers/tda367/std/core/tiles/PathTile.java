package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.SpriteCreator;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Class for representing a walkable tile.
 * @author Emil Johansson
 * @date Mar 26, 2012
 */
public class PathTile extends AbstractTile implements IWalkableTile, Comparable<PathTile>{

	//TODO modified to test pathfinding
	Position pos;
	int boardValue;
	
	//TODO: change sprite for the Path tile.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/path_tile.jpg");

	/**
	 * Create a new PathTile.
	 */
	public PathTile(){
		super(sprite);
	}
	public PathTile(int tileValue) {
		this();
		this.boardValue = tileValue;
	}
	public PathTile(int tileValue, Position pos) {
		this(tileValue);
		this.pos = pos;
	}
	
	@Override
	public String toString(){
		return "_";
	}
	public int getBoardValue(){
		return this.boardValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PathTile tile) {
		if (this.getBoardValue() < tile.getBoardValue()){
			return -1;
		} else if (this.getBoardValue() > tile.getBoardValue()){
			return 1;
		} else {
			return 0;
		}
	}
	/**
	 * @return the pos
	 */
	public Position getPos() {
		return pos;
	}
	

}
