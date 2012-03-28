package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Abstract representation of a tile.
 * @author Emil Johansson
 * @date Mar 26, 2012
 */
public abstract class AbstractTile implements IBoardTile{
	private Sprite sprite;
	
	public AbstractTile(Sprite sprite){
		this.sprite = sprite;
	}
	
	@Override
	public Sprite getSprite(){
		return sprite;
	}
	
}
