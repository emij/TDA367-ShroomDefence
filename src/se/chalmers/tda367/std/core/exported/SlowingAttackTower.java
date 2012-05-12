package se.chalmers.tda367.std.core.exported;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.std.core.effects.IEffect;
import se.chalmers.tda367.std.core.effects.SlowEffect;
import se.chalmers.tda367.std.core.tiles.towers.AbstractAttackTower;
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
							 attackSpeed     = 80;
	private static List<IEffect> effects = new ArrayList<IEffect>();
	static {
		effects.add(new SlowEffect(1));
	}
	
	//TODO: change sprite for the slowing tower.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/slow_tower_tile.png");
	
	private int upgradeLevel = 1;
	private int dmgModifier = 1;
	
	public SlowingAttackTower() {
		super(baseCost, baseDamage, effectiveRadius, aoeRadius, attackSpeed,
				effects, sprite);
	}

	@Override
	public void upgrade() {
		super.upgrade();
		dmgModifier++;
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
		return "S";
	}
}