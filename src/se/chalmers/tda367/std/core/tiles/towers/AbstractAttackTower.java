package se.chalmers.tda367.std.core.tiles.towers;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.std.core.effects.IEffect;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * A skeleton implementation of the {@code IAttackTower}
 * @author Emil Edholm
 * @modified Johan Andersson.
 * @modified Johan Gustafsson
 * @date Mar 25, 2012
 */
public abstract class AbstractAttackTower implements IAttackTower{
	private int baseCost, baseDamage, effectiveRadius, aoeRadius, attackSpeed;
	private final Sprite sprite;
	private List<IEffect> effects;
	private String towerName;
	
	private int timeSinceLastAttack = 0;

	public AbstractAttackTower(int baseCost, int baseDamage, 
			int effectiveRadius, int aoeRadius, int attackSpeed, List<IEffect> effects, Sprite sprite, String name){
		this.baseCost        = baseCost;
		this.baseDamage      = baseDamage;
		this.effectiveRadius = effectiveRadius;
		this.aoeRadius       = aoeRadius;
		this.attackSpeed     = attackSpeed;
		this.sprite          = sprite;
		this.towerName		 = name;
		if(effects != null){
			this.effects		 = new ArrayList<IEffect>(effects);
		}
	}

	@Override
	public List<IEffect> getEffects() {
		return effects;
	}


	@Override
	public abstract void upgrade();

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
	public boolean isAttackReady(final int delta) {
		timeSinceLastAttack += delta;
		if(timeSinceLastAttack >= getAttackSpeed()){
			timeSinceLastAttack = 0;
			return true;
		}
		return false;
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
	
	@Override
	public String getName() {
		return towerName;
	}
}
