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
	
	public AbstractEnemy(int startHealth, int lootValue, Sprite sprite){
		this.currentHealth = startHealth;
		this.sprite        = sprite;
		this.lootValue     = lootValue;
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

}
