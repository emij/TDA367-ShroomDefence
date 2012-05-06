package se.chalmers.tda367.std.mapeditor;

import java.awt.Graphics;

import javax.swing.JPanel;

import se.chalmers.tda367.std.core.maps.MapItem;

@SuppressWarnings("serial")
public class MapItemJPanel extends JPanel {

	private final int x, y;
	private final MapItem mapItem;
	
	/**
	 * Create the panel.
	 */
	public MapItemJPanel(int x, int y, MapItem mapItem) {
		this.x       = x;
		this.y       = y;
		this.mapItem = mapItem;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// Painting the tile goes here.
	}

	/**
	 * @return the x coordinate of the panel
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y coordinate of the panel
	 */
	public int getY() {
		return y;
	}

	/**
	 * The {@code MapItem} contains info about the tile, such as if it is a waypoint or not.
	 * @return the {@code MapItem} associated with this panel.
	 */
	public MapItem getMapItem() {
		return mapItem;
	}

	
}
