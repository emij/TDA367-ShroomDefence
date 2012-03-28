package se.chalmers.tda367.std.utilities;

/**
 * Represents a position/coordinate on the game board.
 * @author Emil Johansson
 * @modified Emil Edholm
 * @date Mar 22, 2012
 */
public final class Position {
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
	
	@Override
	public String toString(){
		return "(" + x + ", " + y + ")"; 
	}
	
	@Override 
	public int hashCode(){
		int result = 17;
		result = 31 * result + x;
		result = 31 * result + y;
		
		return result;
	}
	
	@Override
	public boolean equals(Object rhs){
		if(this == rhs)
			return true;
		if(!(rhs instanceof Position))
			return false;
		
		// Safe to cast.
		Position p = (Position)rhs;
		return x == p.x && y == p.y;
	}

}
