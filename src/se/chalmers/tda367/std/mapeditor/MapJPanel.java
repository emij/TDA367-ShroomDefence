package se.chalmers.tda367.std.mapeditor;

import java.awt.Graphics;
import javax.swing.JPanel;

import com.google.common.eventbus.Subscribe;

import se.chalmers.tda367.std.core.Properties;
import se.chalmers.tda367.std.core.maps.LevelMap;
import se.chalmers.tda367.std.core.maps.MapItem;
import se.chalmers.tda367.std.mapeditor.events.NewMapEvent;
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
				
				nss.draw(x*scale, y*scale, scale, scale);
			}
		}
		
		super.paintComponents(g);
	}

	@Subscribe
	public void createNewMap(NewMapEvent event) {
		MapItem defaultItem = new MapItem(event.getDefaultTile().getTile());
		int width = event.getWidth();
		int height = event.getHeight();
		int level = event.getLevel();
		
		mapModel = new LevelMap(level, width, height);
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				mapModel.setMapItem(x, y, defaultItem);
			}
		}
		
		this.repaint();
	}
	
	public void setMapItem(int x, int y, MapItem item) {
		mapModel.setMapItem(x, y, item);
		this.repaint();
	}
}
