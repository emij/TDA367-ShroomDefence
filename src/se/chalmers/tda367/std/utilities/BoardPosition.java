package se.chalmers.tda367.std.utilities;


/**
 * Class used for representing positions using floating point numbers.
 * @author Emil Edholm
 * @date   Apr 28, 2012
 */
public class BoardPosition {
	private int x, y;
	private BoardPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/** Create a new {@code BoardPosition} from the specified x,y values */
	public static BoardPosition valueOf(int x, int y) {
		return new BoardPosition(x, y);
	}
	
	/** Create a new {@code BoardPosition} with the same values as the one specified */
	public static BoardPosition valueOf(BoardPosition bp) {
		return valueOf(bp.getX(), bp.getY());
	}
	
	/** Returns the x coordinate */
	public int getX() {
		return x;
	}
	
	/** Returns the y coordinate */
	public int getY() {
		return y;
	}
	
	/**
	 * Convert to a floating point based Position.
	 * @return a {@code Position} with the same coordinates as {@code this}
	 */
	public Position toPosition() {
		return Position.valueOf(x, y);
	}
}