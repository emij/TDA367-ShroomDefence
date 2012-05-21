package se.chalmers.tda367.std.gui;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import com.google.common.eventbus.Subscribe;

import se.chalmers.tda367.std.core.EnemyList;
import se.chalmers.tda367.std.core.IGame;
import se.chalmers.tda367.std.core.IGameBoard;
import se.chalmers.tda367.std.core.IPlayerCharacter;
import se.chalmers.tda367.std.core.Properties;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.events.EnemyEnteredBaseEvent;
import se.chalmers.tda367.std.core.events.TowerShootingEvent;
import se.chalmers.tda367.std.core.tiles.IBoardTile;
import se.chalmers.tda367.std.core.tiles.towers.ITower;
import se.chalmers.tda367.std.utilities.BoardPosition;
import se.chalmers.tda367.std.utilities.EventBus;
import se.chalmers.tda367.std.utilities.NativeSprite;
import se.chalmers.tda367.std.utilities.Position;

/**
 * In-game graphics will be drawn by this class.
 * Will be used by {@code GameplayState} as main graphic-renderer.
 * @author Johan Gustafsson
 * @date 2012-05-15
 */
public class GameplayRenderer {
	private IGame gameControl;
	private Input input;
	private Animation explosionAnimation;
	private List<AttackAnimation> attacksList;
	
	private Properties prop = Properties.INSTANCE;
	private int tileScale;
	
	public GameplayRenderer(IGame gameControl, Input input) throws SlickException {
		this.gameControl = gameControl;
		this.input = input;
		init();
	}
	
	
	private void init() throws SlickException {
		tileScale = prop.getTileScale();
		attacksList = new CopyOnWriteArrayList<AttackAnimation>();
		initAnimations();
		
		EventBus.INSTANCE.register(this);
	}
	
	
	/**Creates {@code Animation} which will be used by game renderer*/
	private void initAnimations() throws SlickException {
		Image exp_1 = new Image(getResourcePath("/animations/explosion_1.png"));
		Image exp_2 = new Image(getResourcePath("/animations/explosion_2.png"));
		Image[] explosion = new Image[2];
		explosion[0] = exp_1;
		explosion[1] = exp_2;
		explosionAnimation = new Animation(explosion, 500);
	}
	
	/**Helper method to get correct path to files.*/
	private String getResourcePath(String path){
		return getClass().getResource(path).getPath();
	}
	
	public void renderGame(Graphics g) {
		g.setColor(Color.black);
		renderTiles();
        renderEnemies(g);
        renderPlayerCharacter(g);
        
        if(attacksList != null && !attacksList.isEmpty()) {
        	renderAttacks(g);
        }
	}
	
	
	private void renderTiles() {
		IGameBoard board = gameControl.getGameBoard();
		
        for(int y = 0; y < board.getHeight(); y++){
        	for(int x = 0; x < board.getWidth(); x++){
        		IBoardTile tile = board.getTileAt(x, y);
        		int nX = x * tileScale;
        		int nY = y * tileScale;
        		tile.getSprite().getNativeSprite().draw(nX, nY, tileScale, tileScale);
        	}
        }
	}
	
	
	private void renderEnemies(Graphics g) {
		IGameBoard board = gameControl.getGameBoard();
		EnemyList enemies = board.getEnemies();
		
        for(IEnemy enemy : enemies) {
        	Position p = enemy.getPosition();
        	int health = enemy.getHealth();
        	NativeSprite image = enemy.getSprite().getNativeSprite();
        	
        	image.draw(p.getX(), p.getY(), tileScale, tileScale);
        	g.drawString(""+health, p.getX(), p.getY()-tileScale);
        }
	}

	
	private void renderPlayerCharacter(Graphics g) {
		IPlayerCharacter character = gameControl.getPlayer().getCharacter();
		NativeSprite image = character.getSprite().getNativeSprite();
		
		image.draw(character.getPos().getX() - tileScale/2, 
				character.getPos().getY() - tileScale/2, tileScale, tileScale);
	}
	
	
	/**Renders the small square and the big circle around the mouse location that gives the player visual feedback
	   showing if he can build on that tile or not and how far the tower can fire.*/
	void renderBuildingFeedback(Graphics g, ITower tower) {
		int x = input.getMouseX()/tileScale;
		int y = input.getMouseY()/tileScale;
		
		
		if(gameControl.isBuildableSpot(BoardPosition.valueOf(x, y))) {
			g.setColor(Color.green);
			g.setLineWidth(3);
			g.drawRect(x * tileScale, y * tileScale, tileScale, tileScale);
			g.setColor(Color.black);
			g.setLineWidth(1);
			g.draw(new Circle(x * tileScale+tileScale/2, y * tileScale+tileScale/2, tower.getRadius()*tileScale));
			
		} else {
			g.setColor(Color.red);
			g.drawRect(x * tileScale, y * tileScale, tileScale, tileScale);
			g.setColor(Color.black);
		}
	}
	
	
	/**Render attacks from the tower attacking to the enemy being attacked. Uses inner class {@code AttackAnimationDuration}.*/
	public void renderAttacks(Graphics g) {
		//List<AttackAnimation> tmpList = new LinkedList<AttackAnimation>(attacksList);
		
		for(AttackAnimation attack : attacksList) {
			Position from = attack.getFromPos();
			Position to = attack.getToPos();
			
			g.drawLine(from.getX()+tileScale/2, from.getY()+tileScale/2,
					   to.getX()+tileScale/2, to.getY()+tileScale/2);
			g.drawAnimation(explosionAnimation, to.getX(), to.getY());
		}
	}
	
	
	/**
	 * Will add create a new {@code AttackAnimationDuration} with provided {@code TowerShootingEvent} and add
	 * it to the list of attacks that will be render by the game.
	 * @param event instance of {@code TowerShootingEvent} that will be used in the wrapper.
	 */
	@Subscribe
	public void renderTowerShooting(TowerShootingEvent event){
		AttackAnimation attack = new AttackAnimation(event.getFromPosition(), event.getToPosition(), 100);
		attacksList.add(attack);
	}
	
	public void updateAttackTimers(int delta) {
		if(attacksList != null && !attacksList.isEmpty()) {
			for(AttackAnimation attack : attacksList) {
				attack.updateDuration(delta);
				
				if(attack.isAttackOver()) {
					attacksList.remove(attack);
				}
			}
		}
	}
	
	
	@Subscribe
	public void enemyHasEnteredBase(EnemyEnteredBaseEvent e) {
		// TODO: Implement
	}
}
