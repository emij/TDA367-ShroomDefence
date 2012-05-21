package se.chalmers.tda367.std.core;

import com.google.common.eventbus.Subscribe;

import se.chalmers.tda367.std.core.events.EnemyDeadEvent;
import se.chalmers.tda367.std.utilities.EventBus;
import se.chalmers.tda367.std.utilities.Position;

/**
 * Represents a player. Contains name, score etc.
 * @author Emil Johansson
 * @modified Johan Gustafsson
 * @modified Emil Edholm (May 21, 2012)
 * @date Mar 22, 2012
 */
public class Player implements IPlayer {
	
	public static final String DEFAULT_NAME   = "Random Player";
	public static final int    STARTING_MONEY = 500;
	
	private String name;
	private int currentScore;
	private int money;
	private IPlayerCharacter character;
	
	/**
	 * Create a new player with the name {@code DEFAULT_NAME}.
	 */
	public Player(){
		this(DEFAULT_NAME);
	}
	public Player(String name){
		this.name = name;
		this.character = new PlayerCharacter(new Position(100, 250));
		this.addMoney(STARTING_MONEY);
		this.currentScore = 0;
		
		EventBus.INSTANCE.register(this);
	}

	@Override
	public int getCurrentScore() {
		return currentScore;
	}
	
	@Override
	public void addScore(int score) {
		this.currentScore += score;
	}
	
	@Override
	public int getMoney() {
		return money;
	}
	
	@Override
	public void removeMoney(int money) {
		this.money -= money;
	}
	
	@Override
	public void addMoney(int money) {
		this.money += money;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public IPlayerCharacter getCharacter() {
		return character;
	}
        /**
	 * Event handler for when an enemy dies and it should be looted and added to score.
	 * @param e - the event that contains the dead enemy.
	 */
	@Subscribe
	public void lootDeadEnemy(EnemyDeadEvent e) {
		int lootedMoney = e.getDeadEnemy().getLootValue();
		addMoney(lootedMoney);
		addScore(lootedMoney);
	}
}
