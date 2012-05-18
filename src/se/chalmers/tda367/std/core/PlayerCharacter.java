package se.chalmers.tda367.std.core;

import java.util.List;

import se.chalmers.tda367.std.core.effects.IEffect;
import se.chalmers.tda367.std.core.effects.NoEffect;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.events.TowerShootingEvent;
import se.chalmers.tda367.std.utilities.EventBus;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;
import se.chalmers.tda367.std.utilities.SpriteCreator;

/**
 * Concrete implementation of the {@code PlayerCharacter} class, this is where all the character data is stored.
 * @author Johan Gustafsson
 * @date   May 14, 2012
 */
public class PlayerCharacter implements IPlayerCharacter {
	private Position pos;
	private int attackDmg, attackSpd, timeSinceLastAttack;
	private float movementSpd;
	
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/enemy.png");

	public PlayerCharacter(Position pos) {
		this.pos = pos;
		attackDmg = 10;
		timeSinceLastAttack = 0;
		attackSpd = 150;
		movementSpd = 0.2F;
	}
	
	@Override
	public void moveTo(Position pos) {
		this.pos.setX(pos.getX());
		this.pos.setY(pos.getY());
	}
	
	@Override
	public Position getPos() {
		return new Position(pos);
	}
	
	@Override
	public float getSpeed() {
		return movementSpd;
	}
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
	@Override
	public boolean isAttackReady(final int delta) {
		timeSinceLastAttack += delta;
		if(timeSinceLastAttack >= attackSpd){
			timeSinceLastAttack = 0;
			return true;
		}
		return false;
	}
	
	@Override
	public void shoot(List<IEnemy> enemies, Position pos) {
		if(!enemies.isEmpty()){
			IEnemy enemy = enemies.get(0);
			
			enemy.receiveShot(new Shot() {
				@Override
				public int getDamage() { return attackDmg; }

				@Override
				public IEffect getEffect() { return NoEffect.getInstance(); }
				
			});
		
			EventBus.INSTANCE.post(new TowerShootingEvent(pos, enemy.getPosition()));
		}
	}
}
