package se.chalmers.tda367.std.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import se.chalmers.tda367.std.core.EnemyList;
import se.chalmers.tda367.std.core.GameBoard;
import se.chalmers.tda367.std.core.GameController;
import se.chalmers.tda367.std.core.Properties;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.tiles.IBoardTile;
import se.chalmers.tda367.std.utilities.NativeSprite;
import se.chalmers.tda367.std.utilities.Position;

/**
 * Class for rendering the gameplay graphics.
 * @author Johan Gustafsson
 * @date 2012-05-15
 */
public class GameplayRenderer {
	private Graphics g;
	private GameController gameControl;
	
	private Properties prop = Properties.INSTANCE;
	private int tileScale;
	
	public GameplayRenderer(Graphics g, GameController gameControl) {
		this.g = g;
		this.gameControl = gameControl;
		init();
	}
	
	private void init() {
		tileScale = prop.getTileScale();
		g.setLineWidth(3);
	}
	
	public void renderGame() {
		g.setColor(Color.black);
		renderTiles();
        renderEnemies();
	}
	
	private void renderTiles() {
		GameBoard board = gameControl.getGameBoard();
		
        for(int y = 0; y < board.getHeight(); y++){
        	for(int x = 0; x < board.getWidth(); x++){
        		IBoardTile tile = board.getTileAt(x, y);
        		int nX = x * tileScale;
        		int nY = y * tileScale;
        		tile.getSprite().getNativeSprite().draw(nX, nY, tileScale, tileScale);
        	}
        }
	}
	
	private void renderEnemies() {
		GameBoard board = gameControl.getGameBoard();
		EnemyList enemies = board.getEnemies();
		
        for(IEnemy enemy : enemies) {
        	Position p = enemy.getPosition();
        	int health = enemy.getHealth();
        	NativeSprite image = enemy.getSprite().getNativeSprite();
        	
        	image.draw(p.getX(), p.getY(), tileScale, tileScale);
        	g.drawString(""+health, p.getX(), p.getY()-tileScale);
        }
	}
}
