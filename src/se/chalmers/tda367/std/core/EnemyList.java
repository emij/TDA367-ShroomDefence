package se.chalmers.tda367.std.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.eventbus.Subscribe;

import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.events.EnemyDeadEvent;
import se.chalmers.tda367.std.core.events.EnemyEnteredBaseEvent;
import se.chalmers.tda367.std.utilities.EventBus;
import se.chalmers.tda367.std.utilities.Position;

/**
 * Represents the enemies on the game board.
 * @author Emil Edholm
 * @date   May 13, 2012
 */
public final class EnemyList implements Iterable<IEnemy> {
	private final List<IEnemy> enemies;
	private final Position enemyStartPosition;
	private final List<Position> baseWaypoints;
	
	/**
	 * Create a new enemy list with the specified enemy start position and a list of waypoints from the map.
	 * @param enemyStartPos - the position where enemies should be inserted on the game board.
	 * @param baseWaypoints - all waypoints on the map/game board.
	 */
	public EnemyList(Position enemyStartPos, List<Position> baseWaypoints) {
		EventBus.INSTANCE.register(this);
		
		enemies = new CopyOnWriteArrayList<IEnemy>();
		this.enemyStartPosition = enemyStartPos;
		this.baseWaypoints = baseWaypoints;
	}

	@Override
	public Iterator<IEnemy> iterator() {
		return enemies.iterator();
	}
	
	/** Remove an enemy from the list */
	public void remove(IEnemy enemy) {
		enemies.remove(enemy);
	}
	
	/** Add an enemy to the list */
	public void add(IEnemy enemy) {
		enemy.placeOnBoard(enemyStartPosition, new ArrayList<Position>(baseWaypoints));
		enemies.add(enemy);
	}
	
	/** Whether or not the list is empty */
	public boolean isEmpty() {
		return enemies.isEmpty();
	}
	
	/**
	 * Event handler that removes a dead enemy.
	 */
	@Subscribe
	public void removeDeadEnemy(EnemyDeadEvent e) {
		enemies.remove(e.getDeadEnemy());
	}
	
	/** Event handler for when an enemy entered the player base */
	@Subscribe
	public void enemyEnteredBase(EnemyEnteredBaseEvent e) {
		remove(e.getOffendingEnemy());
	}
}
