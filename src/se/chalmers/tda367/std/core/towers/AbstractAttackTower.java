package se.chalmers.tda367.std.core.towers;

import java.beans.PropertyChangeSupport;

import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * A skeleton implementation of the {@code IAttackTower}
 * @author Emil Edholm
 * @date Mar 25, 2012
 */
public abstract class AbstractAttackTower implements IAttackTower{
	private int baseCost, baseDamage, effectiveRadius, aoeRadius, attackSpeed;
	private final Sprite sprite;
	
	private final PropertyChangeSupport targetList;
	
	public AbstractAttackTower(int baseCost, int baseDamage, 
							   int effectiveRadius, int aoeRadius, int attackSpeed, Sprite sprite){
		this.baseCost        = baseCost;
		this.baseDamage      = baseDamage;
		this.effectiveRadius = effectiveRadius;
		this.aoeRadius       = aoeRadius;
		this.attackSpeed     = attackSpeed;
		this.sprite          = sprite;
		
		targetList = new PropertyChangeSupport(this);
	}
	
	@Override
	public abstract void upgrade();

	@Override
	public abstract void fire();
	
	@Override
	public int refund() {
		return (int)(0.75 * baseCost);
	}
	
	@Override
	public int getBaseCost(){
		return baseCost;
	}

	@Override
	public int getRadius() {
		return effectiveRadius;
	}

	@Override
	public int getDmg() {
		return baseDamage;
	}

	@Override
	public int getAttackSpeed() {
		return attackSpeed;
	}

	@Override
	public int getDmgRadius() {
		return aoeRadius;
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}
	

	@Override
	public abstract int getUpgradeCost();
	

	@Override
	public void addToTargetList(IEnemy enemy) {
		targetList.addPropertyChangeListener(enemy);
	}

	@Override
	public void removeFromTargetList(IEnemy enemy) {
		targetList.removePropertyChangeListener(enemy);
	}
	
	/**
	 * Notifies the enemies in the target list of the damage done to them.
	 * Sends the base damage as the {@code oldValue}.
	 * @param dmg the damage to send to the target list.
	 */
	protected void notifyTargetList(int dmg){
		targetList.firePropertyChange("damage", getDmg(), dmg);
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + " {" +
							               "\n\tBase cost: "       + baseCost        + 
							               "\n\tBase damage: "     + baseDamage      + 
							               "\n\tEffectiveRadius: " + effectiveRadius + 
							               "\n\tDmg Radius: "      + aoeRadius       +
							               "\n\tAttackSpeed: "     + attackSpeed     +
							               "\n\tSprite: "          + sprite          +
							               "\n}\n";
		
		// This should probably not be "closed" and it should be up to each
		// concrete implementation to do that and add their specific values.
	}

}
