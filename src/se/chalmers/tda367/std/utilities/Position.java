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
 * @date Mar 22, 2012
 */
public class Position implements Serializable {
	private static final long serialVersionUID = -1002505327189491904L;
	private transient float x, y;
	
	public Position(float x, float y){
		this.x = x;
		this.y = y;
		
	}
	
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
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
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
		return x == p.x && y == p.y;
	}
	
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
