package se.chalmers.tda367.std.core.tiles.towers;

import java.util.Iterator;
import java.util.List;

import se.chalmers.tda367.std.core.Shot;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.effects.IEffect;
import se.chalmers.tda367.std.core.events.TowerShootingEvent;
import se.chalmers.tda367.std.utilities.EventBus;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * A skeleton implementation of the {@code IAttackTower}
 * @author Emil Edholm
 * @modified Johan Andersson.
 * @modified Johan Gustafsson
 * @modified Emil Johansson 12-05-2012
 * @modified Emil Edholm (May 16, 2012)
 * @date Mar 25, 2012
 */
public abstract class AbstractAttackTower implements IAttackTower{
	private final int baseCost, baseDamage, effectiveRadius, aoeRadius, attackSpeed, targetCount;
	private int currentLevel;
	private final Sprite sprite;
	private IEffect effect;
	
	private int timeSinceLastAttack = 0;

	public AbstractAttackTower(int baseCost, int baseDamage, 
			int effectiveRadius, int aoeRadius, int attackSpeed, int targetCount, IEffect effect, Sprite sprite){
		this.baseCost        = baseCost;
		this.baseDamage      = baseDamage;
		this.effectiveRadius = effectiveRadius;
		this.aoeRadius       = aoeRadius;
		this.attackSpeed     = attackSpeed;
		this.targetCount     = targetCount;
		this.sprite          = sprite;
		this.effect          = effect.clone();
		this.currentLevel	 = 1;
	}

	@Override
	public IEffect getEffect() {
		return effect;
	}
	
	/**
	 * Removes any enemies that has {@code excludeEffect} applied before shooting.
	 * @param excludeEffect - the effect type to exclude.
	 */
	protected List<IEnemy> excludeEffect(List<IEnemy> enemies, Class<? extends IEffect> excludeEffect) {
		// Remove the enemies that already has the effect applied.
		Iterator<IEnemy> it = enemies.iterator();
		while(it.hasNext()){
			IEnemy enemy = it.next();
			if(enemy.hasEffect(excludeEffect)) {
				it.remove();
			}
		}
		
		return enemies;
	}
	
	@Override
	public void shoot(List<IEnemy> enemies, Position pos) {
		int shots = 0;
		for(IEnemy enemy : enemies) {
			enemy.receiveShot(new Shot() {
				@Override
				public int getDamage() { return getDmg(); }
	
				@Override
				public IEffect getEffect() { return effect.clone(); }
			});
	
	
			EventBus.INSTANCE.post(new TowerShootingEvent(pos, enemy.getPosition()));
			
			if(++shots >= targetCount) // Never shot more enemies than our target count.
				return;
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
		return baseDamage*currentLevel;
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
