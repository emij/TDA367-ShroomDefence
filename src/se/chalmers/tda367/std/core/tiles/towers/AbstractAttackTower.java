package se.chalmers.tda367.std.core.tiles.towers;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.std.core.EnemyItem;
import se.chalmers.tda367.std.core.effects.IEffect;
import se.chalmers.tda367.std.events.TowerShootingEvent;
import se.chalmers.tda367.std.utilities.EventBus;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * A skeleton implementation of the {@code IAttackTower}
 * @author Emil Edholm
 * @modified Johan Andersson.
 * @modified Johan Gustafsson
 * @modified Emil Johansson 12-05-2012
 * @date Mar 25, 2012
 */
public abstract class AbstractAttackTower implements IAttackTower{
	private int baseCost, baseDamage, effectiveRadius, aoeRadius, attackSpeed, currentLevel;
	private final Sprite sprite;
	private List<IEffect> effects;
	
	private int timeSinceLastAttack = 0;

	public AbstractAttackTower(int baseCost, int baseDamage, 
			int effectiveRadius, int aoeRadius, int attackSpeed, List<IEffect> effects, Sprite sprite){
		this.baseCost        = baseCost;
		this.baseDamage      = baseDamage;
		this.effectiveRadius = effectiveRadius;
		this.aoeRadius       = aoeRadius;
		this.attackSpeed     = attackSpeed;
		this.sprite          = sprite;
		this.effects = effects != null ? new ArrayList<IEffect>(effects): new ArrayList<IEffect>();
		this.currentLevel	 = 1;
	}

	@Override
	public List<IEffect> getEffects() {
		return effects;
	}
	@Override
	public void shoot(List<EnemyItem> enemies, Position pos) {
		if(!enemies.isEmpty()){

			enemies.get(0).getEnemy().decreaseHealth(this.getDmg()*currentLevel);
		
			for(IEffect ie:this.getEffects()){
				enemies.get(0).getEnemy().addEffect(ie);	//TODO refactor
			}


			EventBus.INSTANCE.post(new TowerShootingEvent(pos, enemies.get(0).getEnemyPos()));

		}
	}
	@Override
	public void upgrade() {
		currentLevel++;
	} 

	@Override
	public int refund() {
		return (int)(0.75 * (baseCost + (0.5*currentLevel*baseCost))); //TODO change calculation
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
	public int getUpgradeCost() {
		return (int)((currentLevel*0.5)*baseCost); 
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
	
	@Override
	public int getCurrentLevel() {
		return currentLevel;
	}
}
