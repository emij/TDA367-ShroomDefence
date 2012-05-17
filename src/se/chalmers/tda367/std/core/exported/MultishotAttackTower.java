package se.chalmers.tda367.std.core.exported;

import se.chalmers.tda367.std.core.anno.Tower;
import se.chalmers.tda367.std.core.effects.NoEffect;
import se.chalmers.tda367.std.core.tiles.towers.AbstractAttackTower;
import se.chalmers.tda367.std.utilities.Sprite;
import se.chalmers.tda367.std.utilities.SpriteCreator;

/**
 * Represents a Armor decreasing attack tower for use in a textual game board.
 * <p>
 * Base cost        = <b>250</b><br />
 * Base damage      = <b>15</b><br />
 * Effective radius = <b>3</b><br />
 * Damage radius    = <b>2</b><br />
 * Attack speed     = <b>400</b><br />
 * </p>
 * @author Johan Andersson
 * @date   13 may 2012
 */
@Tower(name = "Multishot", description = "Tower that shoot multiple targets at once", towerStrength = 3)
public final class MultishotAttackTower extends AbstractAttackTower {

	private static final int baseCost        = 250, 
							 baseDamage      = 15, 
							 effectiveRadius = 3, 
							 aoeRadius       = 2, 
							 attackSpeed     = 400,
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