package se.chalmers.tda367.std.core.exported;

import java.util.List;

import se.chalmers.tda367.std.core.effects.PoisonEffect;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.tiles.towers.AbstractAttackTower;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;
import se.chalmers.tda367.std.core.anno.Tower;
import se.chalmers.tda367.std.utilities.SpriteCreator;

/**
 * Represents a Slowing attack tower for use in a textual game board.
 * <p>
 * Base cost        = <b>200</b><br />
 * Base damage      = <b>5</b><br />
 * Effective radius = <b>2</b><br />
 * Damage radius    = <b>0</b><br />
 * Attack speed     = <b>60</b><br />
 * </p>
 * @author Emil Johansson
 * @date   12 may 2012
 */
@Tower(name = "Poison", description = "AttackTower with poison effect", towerStrength = 2)
public final class PoisonAttackTower extends AbstractAttackTower {

	private static final int baseCost        = 200, 
							 baseDamage      = 5, 
							 effectiveRadius = 2, 
							 aoeRadius       = 0, 
							 attackSpeed     = 250;
	
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/poison_tower_tile.png");
	
	public PoisonAttackTower() {
		super(baseCost, baseDamage, effectiveRadius, aoeRadius, attackSpeed,
				1, new PoisonEffect(1), sprite);
	}
	


	@Override
	public void shoot(List<IEnemy> enemies, Position pos) {
		super.shoot(excludeEffect(enemies, PoisonEffect.class), pos);
	}


	/**
	 * The textual representation of the tower on a text based game board.
	 */
	@Override
	public String toString(){
		return "P";
	}
}