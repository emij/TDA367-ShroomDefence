package se.chalmers.tda367.std.core.tiles.enemies;

import java.beans.PropertyChangeEvent;

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
	private int boardValue;
	
	public AbstractEnemy(int startHealth, int speed, int lootValue, Sprite sprite){
		this.currentHealth = startHealth;
		this.speed         = speed;
		this.lootValue     = lootValue;
		this.sprite        = sprite;
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
	@Override
	public int getBoardValue(){
		return this.boardValue;
	}
	@Override
	public void setBoardValue(int boardValue){
		this.boardValue = boardValue;
	}
	@Override
	public int compareTo(IEnemy enemy){
		if (this.getBoardValue() < enemy.getBoardValue()){
			return -1;
		} else if (this.getBoardValue() > enemy.getBoardValue()){
			return 1;
		} else {
			return 0;
		}
	}
}
