package se.chalmers.tda367.std.utilities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Represents a position/coordinate on the game board. 
 * Position coordinates ranging from {@code Float.MIN_VALUE} to {@code Float.MAX_VALUE}
 * @author Emil Johansson
 * @modified Emil Edholm (Apr 28, 2012), Johan Andersson (Mar 28, 2012)
 * @modified Johan Gustafsson(2012-05-22)
 * @date Mar 22, 2012
 */
public class Position implements Serializable {
	private static final long serialVersionUID = -1002505327189491904L;
	private transient float x, y;
	
	/**
	 * Creates a new {@code Position} from given x and y values.
	 * @param x the value that is going to be used as x coordinate.
	 * @param y the value that is going to be used as y coordinate.
	 */
	public Position(float x, float y){
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * Creates a new copy {@code Position} of the given {@code Position}
	 * @param copyFrom this is the position which will be copied.
	 */
	public Position(Position copyFrom){
		this.x = copyFrom.x;
		this.y = copyFrom.y;
	}

	/**
	 * A static factory method for creating positions.
	 * @param x the x coordinate/position
	 * @param y the y coordinate/position
	 * @return a position with the specified values.
	 */
	public static Position valueOf(float x, float y){
		return new Position(x, y);
	}
	
	/**
	 * A static factory method for copying positions.
	 * @param p the position to get values from.
	 * @return a copied position of the given position.
	 */
	public static Position valueOf(Position p){
		return new Position(p.getX(), p.getY());
	}
	
	/**
	 * Get the x-value of the position.
	 * @return the position x-value.
	 */
	public final float getX() {
		return x;
	}

	/**
	 * Get the y-value of the position.
	 * @return the y value of the position.
	 */
	public final float getY() {
		return y;
	}
	
	/**
	 * Sets the x coordinate of the Position.
	 * @param x - the coordinate to set the position to.
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * Sets the y coordinate of the Position.
	 * @param y - the coordinate to set the position to.
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	@Override
	public String toString(){
		return "(" + x + ", " + y + ")"; 
	}
	
	@Override 
	public int hashCode(){
		int result = 17;
		result = 31 * result + Float.floatToIntBits(x);
		result = 31 * result + Float.floatToIntBits(y);
		
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
		return Float.floatToIntBits(x) == Float.floatToIntBits(p.x) && 
				Float.floatToIntBits(y) == Float.floatToIntBits(p.y);
	}
	
	/**
	 * Moves the x and y coordinates of the position with given values.
	 * @param dx the amount to change the x value with.
	 * @param dy the amount to change the y value with.
	 */
	public void move(float dx, float dy){
		x = x + dx;
		y = y + dy;
	}
	
	/**
	 * Calculate the distance between 2 positions using the Pythagorean theorem.
	 * @param p1 the first position
	 * @param p2 the second position
	 * @return the distance between the two points, expressed as a double.
	 */
	public static double calculateDistance(Position p1, Position p2) {
		double dx = Math.abs(p1.x - p2.x);
		double dy = Math.abs(p1.y - p2.y);
		
		// The Pythagorean theorem gives us
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}
	
	/**
	 * Serialize this {@code Position}.
	 * @serialData the x value is written first, then the y value.
	 */
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeFloat(x);
		s.writeFloat(y);
	}
	
	
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		this.x = s.readFloat();
		this.y = s.readFloat();
	}
}
