package se.chalmers.tda367.std.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import se.chalmers.tda367.std.core.BuildController;
import se.chalmers.tda367.std.core.DynamicLoader;
import se.chalmers.tda367.std.core.GameBoard;
import se.chalmers.tda367.std.core.LevelMap;
import se.chalmers.tda367.std.core.PlaceableTile;
import se.chalmers.tda367.std.core.Player;
import se.chalmers.tda367.std.core.exported.BasicAttackTower;
import se.chalmers.tda367.std.core.tiles.towers.ITower;
import se.chalmers.tda367.std.utilities.BoardPosition;
import se.chalmers.tda367.std.utilities.SpriteCreator;

public class TestBuildController {

	GameBoard board;
	Player player;
	BuildController buildControl;

	@Before
	public void setUp() throws Exception {
		SpriteCreator.setNativeSpriteClass(NativeDummySprite.class);

		LevelMap map = new LevelMap(1, 2, 2);
		for(int x = 0; x < map.getWidth(); x++) {
			for(int y = 0; y < map.getHeight(); y++) {
				map.setMapItem(x, y, PlaceableTile.BUILDABLE_TILE);
			}
		}
		map.setMapItem(0, 0, PlaceableTile.ENEMY_START_TILE);
		map.setMapItem(0, 1, PlaceableTile.PLAYER_BASE_TILE);
		
		player = new Player("Test");
		player.removeMoney(player.getMoney()); //Makes sure player has 0 money.
		
		board = new GameBoard(map);
		
		buildControl = new BuildController(board, player);
		
	}
	
	@Test
	public void testBuildTower(){
		
		ITower tower = DynamicLoader.createInstance(BasicAttackTower.class);
		assertFalse(buildControl.buildTower(tower, BoardPosition.valueOf(0, 0)));
		player.addMoney(500);
		//Invalid position
		assertFalse(buildControl.buildTower(tower, BoardPosition.valueOf(10, 10)));
		
		assertTrue(buildControl.buildTower(tower, BoardPosition.valueOf(1, 1)));
		assertTrue(board.getTileAt(1, 1) instanceof BasicAttackTower);	
	}
	
	@Test
	public void testIsBuildableSpot(){
		//Empty buildable tile, should be buildable
		assertTrue(buildControl.isBuildableSpot(BoardPosition.valueOf(1, 1)));
		//Enemy start pos, shouldn't be buildable
		assertFalse(buildControl.isBuildableSpot(BoardPosition.valueOf(0, 1)));
		
		//PlayerBase pos, shouldn't be buildable
		assertFalse(buildControl.isBuildableSpot(BoardPosition.valueOf(0, 0)));
		
	}

	@Test
	public void testCanPlayerAfford(){
		ITower tower = DynamicLoader.createInstance(BasicAttackTower.class);
		
		assertFalse(buildControl.playerCanAffordTower(tower));
		assertFalse(buildControl.playerCanAffordUpgrade(tower));
		
		player.addMoney(1000);
		
		assertTrue(buildControl.playerCanAffordTower(tower));
		assertTrue(buildControl.playerCanAffordUpgrade(tower));
	}
	
	@Test
	public void testSellTower(){
		ITower tower = DynamicLoader.createInstance(BasicAttackTower.class);
		player.addMoney(500);
		buildControl.buildTower(tower, BoardPosition.valueOf(1, 1));
		int curMoney = player.getMoney();
		assertFalse(buildControl.sellTower(tower, BoardPosition.valueOf(1,0))); 	//Wrong spot for tower
		assertTrue(buildControl.sellTower(tower, BoardPosition.valueOf(1,1)));
		assertTrue(curMoney < player.getMoney());
	}
	
	@Test
	public void testUpgradeTower(){
		ITower tower = DynamicLoader.createInstance(BasicAttackTower.class);
		
		int curMoney = player.getMoney();
		int curLevel = tower.getCurrentLevel();
		
		//Player has no money, should fail to upgrade
		assertFalse(buildControl.upgradeTower(tower));
		assertTrue(curMoney == player.getMoney());
		assertTrue(curLevel == tower.getCurrentLevel());
		
		player.addMoney(500);
		curMoney = player.getMoney();
		curLevel = tower.getCurrentLevel();
		//Player has money, should be able to upgrade
		assertTrue(buildControl.upgradeTower(tower));
		assertTrue(curMoney > player.getMoney());
		assertTrue(curLevel < tower.getCurrentLevel());
	}
	
	@Test
	public void testIsTowerPosition(){
		ITower tower = DynamicLoader.createInstance(BasicAttackTower.class);
		player.addMoney(500);
		buildControl.buildTower(tower, BoardPosition.valueOf(1, 1));
		
		assertFalse(buildControl.isTowerPosition(BoardPosition.valueOf(0, 0)));
		assertFalse(buildControl.isTowerPosition(BoardPosition.valueOf(1, 0)));
		assertFalse(buildControl.isTowerPosition(BoardPosition.valueOf(0, 1)));
		assertFalse(buildControl.isTowerPosition(BoardPosition.valueOf(5, 5)));
		assertTrue(buildControl.isTowerPosition(BoardPosition.valueOf(1, 1)));
	
	}
	
	

	
	
	
	
	
	



	
	
	

}
