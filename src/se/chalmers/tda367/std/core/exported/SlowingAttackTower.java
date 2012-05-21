package se.chalmers.tda367.std.core.exported;

import java.util.List;

import se.chalmers.tda367.std.core.Attackable;
import se.chalmers.tda367.std.core.effects.SlowEffect;
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
 * Attack speed     = <b>80</b><br />
 * </p>
 * @author Emil Johansson
 * @date   12 may 2012
 */
@Tower(name = "Slowing", description = "AttackTower with slow effect", towerStrength = 2)
public final class SlowingAttackTower extends AbstractAttackTower {

	private static final int baseCost        = 200, 
							 baseDamage      = 5, 
							 effectiveRadius = 2, 
							 aoeRadius       = 0, 
							 attackDelay     = 300;
	
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/slow_tower_tile.png");
	
	public SlowingAttackTower() {
		super(baseCost, baseDamage, effectiveRadius, aoeRadius, attackDelay,
				1, new SlowEffect(1), sprite);
	}
	/**
	 * The textual representation of the tower on a text based game board.
	 */
	@Override
	public String toString(){
		return "S";
	}

	@Override
	public void shoot(List<Attackable> enemies, Position pos) {
		super.shoot(excludeEffect(enemies, SlowEffect.class), pos);
	}
}