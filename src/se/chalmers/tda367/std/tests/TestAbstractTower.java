package se.chalmers.tda367.std.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import se.chalmers.tda367.std.core.*;
import se.chalmers.tda367.std.core.effects.IEffect;
import se.chalmers.tda367.std.core.effects.NoEffect;
import se.chalmers.tda367.std.core.effects.SlowEffect;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.exported.BasicEnemy;
import se.chalmers.tda367.std.core.tiles.towers.AbstractAttackTower;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;
import se.chalmers.tda367.std.utilities.SpriteCreator;

/**
 * Unit test for {@link se.chalmers.tda367.std.core.tiles.towers.AbstractAttackTower}.
 * @author Emil Johansson
 * @date   22 may 2012
 */

public class TestAbstractTower {
	private AbstractTowerTest abs;

	@Before
	public void before(){
		SpriteCreator.setNativeSpriteClass(NativeDummySprite.class);
		abs = new AbstractTowerTest();
	}

	@Test
	public void testEffect(){
		assertTrue(!(abs.getEffect() == NoEffect.getInstance()));
		//Should be false because in constructor the Effect is cloned
	}				
	@Test
	public void testShoot(){
		List<Attackable> enemies = new ArrayList<Attackable>();
		IEnemy tmp = DynamicLoader.createInstance(BasicEnemy.class); 
		enemies.add(tmp);
		int tmpEnHp = tmp.getHealth();
		System.out.println(tmpEnHp);
		abs.shoot(enemies, new Position(2, 2));
		System.out.println(tmp.getHealth());
		assertTrue(tmpEnHp-abs.getDmg()+tmp.getArmor() == tmp.getHealth());	
	}
	@Test
	public void testExcludeEffect(){
		IEnemy tmp = DynamicLoader.createInstance(BasicEnemy.class);
		Shot shot = new TestShot();
		tmp.receiveShot(shot);
		List<Attackable> enemies = new ArrayList<Attackable>();
		enemies.add(tmp);
		assertTrue(enemies.size() == 1);
		enemies = abs.excludeEffect(enemies, SlowEffect.class);
		assertTrue(enemies.isEmpty());
	}
	@Test
	public void testUpgrade(){
		assertTrue(abs.getCurrentLevel() == 1);
		abs.upgrade();
		assertTrue(abs.getCurrentLevel() == 2);
	}
	@Test
	public void testRefund(){
		int tmp = (int)0.75*abs.getBaseCost();
		assertTrue(tmp == abs.refund());
	}
	@Test
	public void testIsAttackReady(){
		assertTrue(abs.isAttackReady(110));
		assertTrue(!abs.isAttackReady(0));
	}
	private static class TestShot implements Shot {
		private IEffect effect = new SlowEffect(1);
		public TestShot(){

		}
		@Override
		public int getDamage() {
			return 0;
		}

		@Override
		public IEffect getEffect() {
			return effect;
		}

	}
	private static class AbstractTowerTest extends AbstractAttackTower {

		private final static Sprite sprite = SpriteCreator.create("/images/gameplay/basic_tower_tile.png");

		public AbstractTowerTest() {
			super(1, 6, 3, 4, 5,
					6, NoEffect.getInstance(), sprite);
		}
		@Override
		public List<Attackable> excludeEffect(List<Attackable> enemies, Class<? extends IEffect> excludeEffect) {
			return super.excludeEffect(enemies, excludeEffect);
		}
	}
}