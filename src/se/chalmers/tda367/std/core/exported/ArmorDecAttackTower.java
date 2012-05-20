package se.chalmers.tda367.std.core.exported;

import se.chalmers.tda367.std.core.effects.ReduceArmorEffect;
import se.chalmers.tda367.std.core.tiles.towers.AbstractAttackTower;
import se.chalmers.tda367.std.utilities.Sprite;
import se.chalmers.tda367.std.core.anno.Tower;
import se.chalmers.tda367.std.utilities.SpriteCreator;

/**
 * Represents a Armor decreasing attack tower for use in a textual game board.
 * <p>
 * Base cost        = <b>200</b><br />
 * Base damage      = <b>5</b><br />
 * Effective radius = <b>2</b><br />
 * Damage radius    = <b>0</b><br />
 * Attack speed     = <b>80</b><br />
 * </p>
 * @author Emil Johansson
 * @date   12 may 2012
 */
@Tower(name = "ArmorDec", description = "AttackTower that decrease enemy armor", towerStrength = 2)
public final class ArmorDecAttackTower extends AbstractAttackTower {

	private static final int baseCost        = 200, 
							 baseDamage      = 5, 
							 effectiveRadius = 2, 
							 aoeRadius       = 0, 
							 attackDelay     = 80;
	
	//TODO: change sprite for the armordec tower.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/armor_tower_tile.png");
	
	public ArmorDecAttackTower() {
		super(baseCost, baseDamage, effectiveRadius, aoeRadius, attackDelay,
				1, new ReduceArmorEffect(1), sprite);
	}
	
	/**
	 * The textual representation of the tower on a text based game board.
	 */
	@Override
	public String toString(){
		return "A";
	}
}