package se.chalmers.tda367.std.utilities;

/**
 * Represents a position/coordinate on the game board. 
 * Position coordniates ranging from {@code Integer.MIN_VALUE} to {@code Integer.MAX_VALUE}
 * @author Emil Johansson
 * @modified Emil Edholm, Johan Andersson
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void incrementX() {
		this.x += 1;
	}
	
	public void incrementY() {
		this.y += 1;
	}
	
	public void decrementX() {
		this.x -= 1;
	}
	
	public void decrementY() {
		this.y -= 1;
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
	
	public Position move(int dx, int dy){
		return new Position(getX()+dx, getY()+dy);
	}

}
