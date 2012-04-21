package se.chalmers.tda367.std.core.tiles.enemies;

import java.beans.PropertyChangeEvent;
import java.util.List;

import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * A skeleton implementation of the IEnemy.
 * @author Emil Edholm
 * @modified Emil Johansson
 * @date Mar 22, 2012
 */
public abstract class AbstractEnemy implements IEnemy{

	private int currentHealth;
	private final Sprite sprite;
	private final int lootValue;
	private int speed;
	private List<Position> waypoints;
	private Position position;
	
	public AbstractEnemy(int startHealth, int speed, int lootValue, Sprite sprite, List<Position> waypoints, Position startPos){
		this.currentHealth = startHealth;
		this.speed         = speed;
		this.lootValue     = lootValue;
		this.sprite        = sprite;
		this.waypoints     = waypoints;
		this.position      = startPos;
	}
	
	@Override
	public int getHealth() {
		return currentHealth;
	}
	@Override
	public int getLootValue(){
		return lootValue;
	}
	@Override
	public int getSpeed(){
		return speed;
	}
	@Override
	public int increaseSpeed(int inc){
		speed = speed + inc;
		return speed;
	}
	@Override
	public int decreaseSpeed(int inc){
		speed = speed - inc;
		return speed;
	}

	@Override
	public void decreaseHealth(int dmg) {
		if(dmg > currentHealth) {
			currentHealth = 0;
			return;
		}
		
		currentHealth -= dmg;
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("damage")){
			decreaseHealth((Integer)evt.getNewValue());
		}
	}
	
	@Override
	public String toString(){
		return this.getClass().getName() + " { " +
									       "\n\tCurrent health: " + currentHealth +
										   "\n\tSprite: "         + sprite        +
										   "\n}\n";
		
		// This should probably not be "closed" and it should be up to each
		// concrete implementation to do that and add their specific values.
		
	}
	
	public void moveEnemy() {
		if(waypoints == null || waypoints.size() == 0) {
			return;
		}
		else if(!waypoints.get(0).equals(position)) {
			for(int i = 0; i < speed; i++) {
				if(waypoints.get(0).getX() != position.getX()) {
					if(waypoints.get(0).getX() > position.getX()) {
						position.incrementX();
					}
					else {
						position.decrementX();
					}
					if (waypoints.get(0).equals(position)) {
						waypoints.remove(0);
					}
				}
				else if(waypoints.get(0).getY() != position.getY()) {
					if(waypoints.get(0).getY() > position.getY()) {
						position.incrementY();
					}
					else {
						position.decrementY();
					}
					if (waypoints.get(0).equals(position)) {
						waypoints.remove(0);
					}
				}
			}
		}
		System.out.println("X= " + position.getX() + "   Y= " + position.getY());
	}

}
