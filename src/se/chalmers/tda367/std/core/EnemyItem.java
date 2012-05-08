package se.chalmers.tda367.std.core;

import java.util.List;

import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.utilities.Position;

/**
 * Represents an enemy, the enemy's position on the gameboard and it's distance to the end position.
 * @author Johan Gustafsson
 * @date April 15, 2012
 */
public class EnemyItem {
	private IEnemy enemy;
	private Position enemyPos;
	private double distanceTraveled;
	private List<Position> waypoints;
	private Properties p = Properties.INSTANCE;
	
	public EnemyItem(IEnemy enemy, Position enemyPos, List<Position> waypoints) {
		this.enemy = enemy;
		this.enemyPos = new Position(enemyPos.getX()*p.getTileScale(), enemyPos.getY()*p.getTileScale());
		this.distanceTraveled = 0;
		this.waypoints = waypoints;
	}
	
	/**
	 * @return the enemy reference.
	 */
	public IEnemy getEnemy() {
		return enemy;
	}
	
	/**
	 * @return the Position the enemy have on the gameboard.
	 */
	public Position getEnemyPos() {
		return enemyPos;
	}
	
	/**
	 * 
	 * @return the waypoints the enemy has.
	 */
	public List<Position> getWaypoints() {
		return waypoints;
	}
	
	/**
	 * @return the shortest distance between the enemy and the end position.
	 */
	public double getDistanceTraveled() {
		return distanceTraveled;
	}
	
	/**
	 * Move the enemy forward.
	 * @param delta - the amount of time (in milliseconds) since the last movement.	
	 */
	public void moveEnemy(int delta) {
		if(waypoints == null || waypoints.isEmpty()) {
			return;
		}
		
		Position waypoint = waypoints.get(0);
		if(!minorDifference(waypoint, enemyPos)) {
			float speedDelta = enemy.getSpeed() * delta;
			float x = enemyPos.getX();
			float y = enemyPos.getY();
			float wayX = waypoint.getX();
			float wayY = waypoint.getY();
			
			if(!minorDifference(wayX, x)) {
				if(wayX > x) {
					enemyPos.move(speedDelta, 0);
				}
				else {
					enemyPos.move(-speedDelta, 0);
				}
			}
			else if(!minorDifference(wayY, y)) {
				if(wayY > y) {
					enemyPos.move(0, speedDelta);
				}
				else {
					enemyPos.move(0, -speedDelta);
				}
				
			}
			if (minorDifference(waypoint, enemyPos)) {
				waypoints.remove(waypoint);
			}
		}
	}
	
	/** Decides if the difference between two values are negligible */
	private boolean minorDifference(float f1, float f2) {
		float diff = f1 - f2;
		return Math.abs(diff) < 2F;
	}
	
	private boolean minorDifference(Position p1, Position p2) {
		return minorDifference(p1.getX(), p2.getX()) && minorDifference(p1.getY(), p2.getY());
	}
}
