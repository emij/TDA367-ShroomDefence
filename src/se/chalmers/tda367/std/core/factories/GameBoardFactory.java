package se.chalmers.tda367.std.core.factories;

import java.io.File;
import java.io.IOException;

import se.chalmers.tda367.std.core.GameBoard;
import se.chalmers.tda367.std.core.IGameBoard;
import se.chalmers.tda367.std.core.LevelMap;
import se.chalmers.tda367.std.utilities.IO;

/**
 * A {@code GameBoardFactory} used to create different levels/maps of the game.
 * @author Emil Edholm
 * @date May 13, 2012
 */
public class GameBoardFactory implements IFactory<IGameBoard, Integer> {

	/**
	 * Create a new game board. 
	 * Effectively a level of the game.
	 * @param level - the level to create. Controls what map are loaded etc.
	 * @return a {@code IGameBoard} if able to create one, else null;
	 */
	@Override
	public IGameBoard create(Integer level) {
		LevelMap map = null;
		File f = new File("maps/level" + level + ".map");
		
		try {
			map = IO.loadObject(LevelMap.class, f);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		if(map == null)
			return null;
		else
			return new GameBoard(map);
	}

}
