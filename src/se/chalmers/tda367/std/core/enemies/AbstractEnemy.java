package se.chalmers.tda367.std.core.enemies;

import java.beans.PropertyChangeEvent;

import se.chalmers.tda367.std.utilities.Sprite;

/**
 * A skeleton implementation of the IEnemy.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public abstract class AbstractEnemy implements IEnemy{

	private int currentHealth;
	private final Sprite sprite;
	
	public AbstractEnemy(int startHealth, Sprite sprite){
		this.currentHealth = startHealth;
		this.sprite        = sprite;
	}
	
	@Override
	public int getHealth() {
		return currentHealth;
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
