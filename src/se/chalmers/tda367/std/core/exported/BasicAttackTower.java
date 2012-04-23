package se.chalmers.tda367.std.core.exported;

import se.chalmers.tda367.std.core.anno.Tower;
import se.chalmers.tda367.std.core.tiles.towers.AbstractAttackTower;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a basic attack tower for use in a textual game board.
 * <p>
 * Base cost        = <b>100</b><br />
 * Base damage      = <b>10</b><br />
 * Effective radius = <b>2</b><br />
 * Damage radius    = <b>0</b><br />
 * Attack speed     = <b>100</b><br />
 * </p>
 * @author Emil Edholm
 * @date   25 mar 2012
 */
@Tower(name = "Basic attack tower", description = "The most common basic attack tower.", towerStrength = 1)
public final class BasicAttackTower extends AbstractAttackTower {

	private static final int baseCost        = 100, 
							 baseDamage      = 10, 
							 effectiveRadius = 2, 
							 aoeRadius       = 0, 
							 attackSpeed     = 100;
	private static final Sprite sprite = new Sprite();
	
	private int upgradeLevel = 1;
	private int dmgModifier = 1;
	
	public BasicAttackTower() {
		super(baseCost, baseDamage, effectiveRadius, aoeRadius, attackSpeed,
				sprite);
	}

	@Override
	public void upgrade() {
		dmgModifier++;
	}

	@Override
	public void fire() {
		notifyTargetList(baseDamage * dmgModifier);
	}

	@Override
	public int getUpgradeCost() {
		return 10 * upgradeLevel;
	}

	/**
	 * The textual representation of the tower on a text based game board.
	 */
	@Override
	public String toString(){
		return "T";
	}
}
