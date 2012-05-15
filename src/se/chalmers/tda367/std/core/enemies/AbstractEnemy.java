package se.chalmers.tda367.std.core.enemies;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.std.core.effects.IEffect;
import se.chalmers.tda367.std.core.events.EnemyDeadEvent;
import se.chalmers.tda367.std.core.events.EnemyEnteredBaseEvent;
import se.chalmers.tda367.std.utilities.EventBus;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * A skeleton implementation of the IEnemy.
 * @author Emil Edholm
 * @modified Emil Johansson, Johan Andersson
 * @date Mar 22, 2012
 */
public abstract class AbstractEnemy implements IEnemy {

	private final Position position;
	private float distanceTraveled;
	private List<Position> waypointsLeft;
	
	private final int baseHealth;
	private final int baseArmor;
	private final int lootValue;
	private final float baseSpeed;

	private int currentHealth;

	private final Sprite sprite;
	private List<IEffect> effects = new ArrayList<IEffect>();

	public AbstractEnemy(int startHealth, float speed, int armor, int lootValue, Sprite sprite){
		this.position         = Position.valueOf(0, 0);
		this.distanceTraveled = 0;
		
		this.baseHealth    = startHealth;
		this.currentHealth = this.baseHealth;
		this.baseSpeed     = speed;
		this.baseArmor 	   = armor;
		this.lootValue     = lootValue;
		this.sprite        = sprite;
	}

	@Override
	public void addEffect(IEffect effect) {
		if(!renewEffect(effect)) {
			effects.add(effect);
		}
	}

	@Override
	public void removeEffect(IEffect effect) {
		effects.remove(effect);
	}

	@Override
	public List<IEffect> getEffects() {
		return effects;
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
	public float getSpeed(){
		double speed = baseSpeed;
		for (IEffect effect : effects) {
			speed = speed * effect.getSpeedModifier();
		}
		return (float)speed;
	}
	
	@Override
	public void decreaseHealth(int dmg) {
		int reducedDmg = dmg - this.getArmor();
		dmg = (reducedDmg > 0) ? reducedDmg : 0;
		
		if(dmg > currentHealth) {
			currentHealth = 0;
			EventBus.INSTANCE.post(new EnemyDeadEvent(this));
			return;
		}

		currentHealth -= dmg;
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
	@Override
	public int getBaseHealth() {
		return baseHealth;
	}

	@Override
	public float getBaseSpeed() {
		return baseSpeed;
	}

	@Override
	public int getArmor() {
		double armor = baseArmor;
		for(IEffect effect: effects){
			armor = armor * effect.getArmorModifier();
		}
		return (int)armor;
	}

	@Override
	public int getBaseArmor() {
		return baseArmor;
	}
	
	@Override
	public void placeOnBoard(Position start, List<Position> waypoints) {
		position.setX(start.getX());
		position.setY(start.getY());
		
		this.waypointsLeft = waypoints;
	}
	
	@Override
	public void moveTowardsWaypoint(int delta) {
		if(waypointsLeft == null || waypointsLeft.isEmpty()) {
			EventBus.INSTANCE.post(new EnemyEnteredBaseEvent(this));
			return;
		}
		
		Position waypoint = waypointsLeft.get(0);
		if(!minorDifference(waypoint, position)) {
			float speedDelta = getSpeed() * delta;
			distanceTraveled += speedDelta;
			float x = position.getX();
			float y = position.getY();
			float wayX = waypoint.getX();
			float wayY = waypoint.getY();
			
			if(!minorDifference(wayX, x)) {
				if(wayX > x) {
					position.move(speedDelta, 0);
				}
				else {
					position.move(-speedDelta, 0);
				}
			}
			else if(!minorDifference(wayY, y)) {
				if(wayY > y) {
					position.move(0, speedDelta);
				}
				else {
					position.move(0, -speedDelta);
				}
				
			}
			if (minorDifference(waypoint, position)) {
				waypointsLeft.remove(0);
			}
		}
	}
	
	@Override
	public Position getPosition(){
		return Position.valueOf(position.getX(), position.getY());
	}
	
	@Override
	public float getDistanceTraveled() {
		return distanceTraveled;
	}
	
	/** Decides if the difference between two values are negligible */
	private boolean minorDifference(float f1, float f2) {
		float diff = f1 - f2;
		return Math.abs(diff) < 5F;
	}
	
	private boolean minorDifference(Position p1, Position p2) {
		return minorDifference(p1.getX(), p2.getX()) && minorDifference(p1.getY(), p2.getY());
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
	public int compareTo(IEnemy o) {
		return Float.compare(distanceTraveled, o.getDistanceTraveled());
	}

	/** 
	 * This checks if the effect is already on the enemy and if so it will reset the duration.
	 * @param effect to check for on the enemy.
	 * @return true if effect has been found and duration reset. False if no similar effect is found.
	 */
	private boolean renewEffect(IEffect effect) {
		for(IEffect e : getEffects()) {
			if(e.getHealthModifier() == effect.getHealthModifier() && e.getArmorModifier() == effect.getArmorModifier()
					&& e.getSpeedModifier() == effect.getSpeedModifier()) {
				e.setDuration(effect.getDuration());
				return true;
			}
		}
		return false;
	}
}
