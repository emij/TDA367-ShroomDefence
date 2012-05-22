package se.chalmers.tda367.std.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.chalmers.tda367.std.core.GameBoard;
import se.chalmers.tda367.std.core.LevelMap;
import se.chalmers.tda367.std.core.PlaceableTile;
import se.chalmers.tda367.std.core.tiles.IBoardTile;
import se.chalmers.tda367.std.utilities.SpriteCreator;

/**
 * Unit test for the gameboard. 
 * Only tests the non-trivial methods.
 * @author Johan Gustafsson
 * @date  May 12, 2012
 */
public class TestGameBoard {
	private LevelMap buildableOnlyMap;
	
	@Before
	public void setUp() {
		SpriteCreator.setNativeSpriteClass(NativeDummySprite.class);
		
		buildableOnlyMap = new LevelMap(1, 25, 20);
		for(int x = 0; x < 25; x++) {
			for(int y = 0; y < 20; y++) {
				buildableOnlyMap.setMapItem(x, y, PlaceableTile.BUILDABLE_TILE);
			}
		}
		buildableOnlyMap.setMapItem(0, 0, PlaceableTile.ENEMY_START_TILE);
		buildableOnlyMap.setMapItem(24, 19, PlaceableTile.PLAYER_BASE_TILE);
	}
	
	//TODO: Test getAttackables()
	
	@Test
	public void getWidth() {
		GameBoard testBoard = new GameBoard(buildableOnlyMap);
		
		assertTrue(testBoard.getWidth() == 25);
	}
	
	@Test
	public void getHeight() {
		GameBoard testBoard = new GameBoard(buildableOnlyMap);
		
		assertTrue(testBoard.getHeight() == 20);
	}
	
	@Test
	public void posOnBoard() {
		GameBoard testBoard = new GameBoard(buildableOnlyMap);
		
		for(int x = 0; x < testBoard.getWidth(); x++) {
			for(int y = 0; y < testBoard.getHeight(); y++) {
				assertTrue(testBoard.posOnBoard(x, y));
			}
		}
		
		assertFalse(testBoard.posOnBoard(-3, 12));
		assertFalse(testBoard.posOnBoard(12, -3));
		assertFalse(testBoard.posOnBoard(-3, -3));
		assertFalse(testBoard.posOnBoard(30, 30));
	}
	
	@Test
	public void getEndPos() {
		GameBoard testBoard = new GameBoard(buildableOnlyMap);
		
		assertTrue(testBoard.getEndPos().getX() == 24);
		assertTrue(testBoard.getEndPos().getY() == 19);
	}
	
	@Test
	public void getTileAt() {
		GameBoard testBoard = new GameBoard(buildableOnlyMap);
		
		for(int x = 0; x < testBoard.getWidth(); x++) {
			for(int y = 0; y < testBoard.getHeight(); y++) {
				assertTrue(testBoard.getTileAt(x, y) instanceof IBoardTile);
			}
		}
	}
}
