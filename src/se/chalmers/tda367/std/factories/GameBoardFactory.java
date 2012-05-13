package se.chalmers.tda367.std.factories;

import se.chalmers.tda367.std.core.GameBoard;

/**
 * A {@code GameBoardFactory} used to create different levels/maps of the game.
 * @author Emil Edholm
 * @date May 13, 2012
 */
public class GameBoardFactory implements IFactory<GameBoard, Integer> {

	/**
	 * Create a new game board. 
	 * Effectively a level of the game.
	 * @param level - the level to create. Controls what map are loaded etc.
	 */
	@Override
	public GameBoard create(Integer level) {
		// TODO: Map loading goes here.
		return new GameBoard(25,20, GameBoard.BoardPosition.valueOf(0,12), GameBoard.BoardPosition.valueOf(19,12));
	}

}
