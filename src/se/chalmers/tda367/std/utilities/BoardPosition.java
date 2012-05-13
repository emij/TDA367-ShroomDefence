package se.chalmers.tda367.std.utilities;


/**
 * Class used for representing positions on the game board.
 * @author Emil Edholm
 * @date   Apr 28, 2012
 */
public class BoardPosition {
	private int x, y;
	private BoardPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static BoardPosition valueOf(int x, int y) {
		return new BoardPosition(x, y);
	}
	
	public static BoardPosition valueOf(BoardPosition bp) {
		return valueOf(bp.getX(), bp.getY());
	}
	
	public int getX() {
		return x;
	}
	
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