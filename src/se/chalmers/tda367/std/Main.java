package se.chalmers.tda367.std;

import java.util.Scanner;


import se.chalmers.tda367.std.core.GameBoard;
import se.chalmers.tda367.std.core.GameController;
import se.chalmers.tda367.std.core.Player;
import se.chalmers.tda367.std.core.tiles.BuildableTile;
import se.chalmers.tda367.std.core.tiles.IBoardTile;
import se.chalmers.tda367.std.core.tiles.IBuildableTile;
import se.chalmers.tda367.std.core.tiles.PathTile;
import se.chalmers.tda367.std.core.tiles.towers.AbstractAttackTower;
import se.chalmers.tda367.std.core.tiles.towers.BasicAttackTower;
import se.chalmers.tda367.std.core.tiles.towers.ITower;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Contains the main method. The entrance to the game.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public final class Main {

	/**
	 * The main method. Used to start the game.
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {
		GameBoard board = new GameBoard(25, 20, new Position(1,12), new Position(18,12));

		GameController control = new GameController(new Player("Horv"), board);
		String str = "";
		String strCord = "";
		int xCord;
		int yCord;
		Scanner scn = new Scanner(System.in);
		System.out.println(board);
		System.out.println("Type b to build a tower or q to quit the game: ");
		while(scn.hasNext()){
			str = scn.nextLine();
			if (str.equals("quit") || str.equals("q")){
				System.out.println("Thanks for playing!");
				System.exit(0);
			} else if (str.equals("build") || str.equals("b")){
				System.out.println("Build: please choose x-cordinate");
				while(true){
					strCord = scn.nextLine();
					try {
						xCord = Integer.parseInt(strCord);
						break;
					} catch(NumberFormatException e) {
						System.out.println("only integers please!");
					}
				}
				System.out.println("Build: please choose y-cordinate");
				while(true){
					strCord = scn.nextLine();
					try {
						yCord = Integer.parseInt(strCord);
						break;
					} catch(NumberFormatException e) {
						System.out.println("only integers please!");
					}
				}
				Position tmp = new Position(xCord, yCord);
				if(board.canBuildAt(tmp)) {
					IBoardTile tower = new BasicAttackTower();
					board.placeTile(tower, tmp);
					if(tower instanceof ITower){
						control.buildTower((ITower)tower, tmp);
					}
					System.out.println(board);
				}
				else {
					System.out.println("Cannot build on given position");
				}
				System.out.println("Type b to build a tower or q to quit the game: ");
			} else if(str.equals("update") || str.equals("u")){
				control.updateGame();
			} else if(str.equals("start") || str.equals("s")){
				control.startGame();
			} else if(str.equals("end") || str.equals("e")){
				control.endGame();
			}
		}


	}

	/**
	 * @param board
	 
	private static void placePath(GameBoard board) {
		IBoardTile pathTile = new PathTile(new Sprite());
		int y = (board.getHeight()/2)-1;
		for (int i = 0; i < board.getWidth(); i++) {
			board.placeTile(pathTile, new Position(i,y));
			board.placeTile(pathTile, new Position(i,y+1));
		}
	}
	*/
	/**
	 * @param board
	 */
	private static void randomPlaceTile(GameBoard board) {
		IBoardTile buildTile = new BuildableTile(new Sprite());
		for (int y = 4; y < 17; y++) {
			for (int x = 0; x < 20; x++) {
				board.placeTile(buildTile, new Position(x,y));
			}

		}
	}


}
