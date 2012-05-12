package se.chalmers.tda367.std.mapeditor;

import java.awt.Graphics;
import javax.swing.JPanel;

import com.google.common.eventbus.Subscribe;

import se.chalmers.tda367.std.core.Properties;
import se.chalmers.tda367.std.core.maps.LevelMap;
import se.chalmers.tda367.std.core.maps.MapItem;
import se.chalmers.tda367.std.mapeditor.events.*;
import se.chalmers.tda367.std.utilities.EventBus;

@SuppressWarnings("serial")
public class MapJPanel extends JPanel {

	LevelMap mapModel;
	public MapJPanel() {
		EventBus.INSTANCE.register(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		if(mapModel == null) {
			super.paintComponents(g);
			return;
		}
		
		int scale = Properties.INSTANCE.getTileScale();
		int width = mapModel.getWidth();
		int height = mapModel.getHeight();
		
		// Set the graphics to the native sprite and draw the tile.
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				MapItem item = mapModel.getMapItem(x, y);
				NativeSwingSprite nss = (NativeSwingSprite)item.getTile().getSprite().getNativeSprite();
				nss.setGraphics(g);
				
				int nX = x * scale;
				int nY = y * scale;
				nss.draw(nX, nY, scale, scale);
				
				// Draw indication of "special" tiles (center the text).
				nY = nY + scale / 2;
				if(item.isStartPosition()) {
					g.drawString("START", nX, nY);
				} else if(item.isWaypoint()) {
					g.drawString("W", nX + scale / 2, nY);
				}
			}
		}
		
		super.paintComponents(g);
	}

	@Subscribe
	public void createNewMap(CreateMapEvent event) {
		MapItem defaultItem = new MapItem(event.getDefaultTile().getTile());
		int width = event.getWidth();
		int height = event.getHeight();
		int level = event.getLevel();
		
		mapModel = new LevelMap(level, width, height);
		
		// Populate the newly created map with the selected default item.
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				mapModel.setMapItem(x, y, defaultItem);
			}
		}
		
		EventBus.INSTANCE.post(new MapLoadedEvent()); // Notify all listeners that a map has been loaded.
		this.repaint();
	}
	
	@Subscribe
	public void placeTileOnMap(TilePlacementEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		MapItem item = e.getTile().getMapItem(x, y);
		setMapItem(x, y, item);
	}
	
	@Subscribe
	public void openMap(OpenMapEvent e) {
		// TODO: implement the opening of the map.
		EventBus.INSTANCE.post(new MapLoadedEvent("The open map option has not been implemented yet."));
	}
	
	@Subscribe
	public void saveMap(SaveMapEvent e) {
		// TODO: implement the map save.
	}
	
	private void setMapItem(int x, int y, MapItem item) {
		mapModel.setMapItem(x, y, item);
		this.repaint();
	}
}
