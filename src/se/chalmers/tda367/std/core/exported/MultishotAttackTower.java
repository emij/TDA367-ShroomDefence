package se.chalmers.tda367.std.core.exported;

import se.chalmers.tda367.std.core.anno.Tower;
import se.chalmers.tda367.std.core.effects.NoEffect;
import se.chalmers.tda367.std.core.tiles.towers.AbstractAttackTower;
import se.chalmers.tda367.std.utilities.Sprite;
import se.chalmers.tda367.std.utilities.SpriteCreator;

/**
 * Represents a Armor decreasing attack tower for use in a textual game board.
 * <p>
 * Base cost        = <b>300</b><br />
 * Base damage      = <b>20</b><br />
 * Effective radius = <b>3</b><br />
 * Damage radius    = <b>0</b><br />
 * Attack speed     = <b>100</b><br />
 * </p>
 * @author Johan Andersson
 * @date   13 may 2012
 */
@Tower(name = "Multishot", description = "Tower that shoot multiple targets at once", towerStrength = 3)
public final class MultishotAttackTower extends AbstractAttackTower {

	private static final int baseCost        = 300, 
							 baseDamage      = 40, 
							 effectiveRadius = 3, 
							 aoeRadius       = 2, 
							 attackSpeed     = 500,
							 nbrOfTargets	 = 3;
	
	//TODO: change sprite for the Multishot tower.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/armor_tower_tile.png");
	
	public MultishotAttackTower() {
		super(baseCost, baseDamage, effectiveRadius, aoeRadius, attackSpeed,
				nbrOfTargets, NoEffect.getInstance(), sprite);
	}
	
	/**
	 * The textual representation of the tower on a text based game board.
	 */
	@Override
	public String toString(){
		return "S";
	}
}