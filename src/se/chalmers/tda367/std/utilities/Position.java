package se.chalmers.tda367.std.utilities;

/**
 * Represents a position/coordinate on the game board.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public class Position {
	private int x, y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Position(Position copyFrom){
		this.x = copyFrom.x;
		this.y = copyFrom.y;
	}

	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}
	
	// hashcode, equals and toString goes here.
	
	
}
