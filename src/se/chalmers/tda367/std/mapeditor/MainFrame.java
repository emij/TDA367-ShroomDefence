package se.chalmers.tda367.std.mapeditor;

import java.awt.BorderLayout;
import javax.swing.*;

import com.google.common.eventbus.Subscribe;

import se.chalmers.tda367.std.core.Properties;
import se.chalmers.tda367.std.core.maps.MapItem;
import se.chalmers.tda367.std.mapeditor.events.NewMapEvent;
import se.chalmers.tda367.std.utilities.EventBus;
import se.chalmers.tda367.std.utilities.SpriteCreator;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.logging.Logger;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The main frame of the Map Editor.
 * @author Emil Edholm
 * @date   Apr 26, 2012
 */
@SuppressWarnings("serial")
public final class MainFrame extends JFrame {
	
	/**
	 * Launches the map editor GUI.
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {
		SpriteCreator.setNativeSpriteClass(NativeSwingSprite.class);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			Logger.getLogger("se.chalmers.tda367.std.mapeditor").severe("Unable to set look and feel of the map editor");
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame mf = new MainFrame();
				mf.setVisible(true);
			}
		});
	}
	
	private final JSplitPane splitPane = new JSplitPane();
	private final MapJPanel mapPanel = new MapJPanel();
	private final JPanel nothingLoadedPanel = new JPanel();
	
	private MapItem selectedMapItem;
	
	public MainFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Add this class to the eventbus.
		EventBus.INSTANCE.register(this);
		
		initializeFrame();
	}
	
	private void initializeFrame(){
	    splitPane.setEnabled(false);
	    splitPane.setVisible(false);
	    getContentPane().setLayout(new CardLayout(0, 0));
	    
	    getContentPane().add(nothingLoadedPanel, "name_30931586860224");
	    
	    JLabel lblNewLabel = new JLabel("No map loaded/created");
	    lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 29));
	    nothingLoadedPanel.add(lblNewLabel);
	    getContentPane().add(splitPane, "name_30931601367131");
	    
	    JPanel leftPanel = new JPanel();
	    splitPane.setLeftComponent(leftPanel);
	    leftPanel.setLayout(new BorderLayout(0, 0));
	    
	    
	    JPanel panel = new JPanel();
	    leftPanel.add(panel, BorderLayout.WEST);
	    GridBagLayout gbl_panel = new GridBagLayout();
	    gbl_panel.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0};
	    gbl_panel.columnWeights = new double[]{0.0};
	    gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    panel.setLayout(gbl_panel);
	    
	    
	    JLabel lblChooseTile = new JLabel("Choose tile");
	    GridBagConstraints gbc_lblChooseTile = new GridBagConstraints();
	    gbc_lblChooseTile.anchor = GridBagConstraints.WEST;
	    gbc_lblChooseTile.insets = new Insets(0, 0, 5, 5);
	    gbc_lblChooseTile.gridx = 0;
	    gbc_lblChooseTile.gridy = 0;
	    panel.add(lblChooseTile, gbc_lblChooseTile);
	    lblChooseTile.setFont(new Font("Tahoma", Font.BOLD, 12));
	    
	    ButtonGroup buttonGroup = new ButtonGroup();
	    JRadioButton rdbtnTerrainTile = new JRadioButton("Terrain tile");
	    GridBagConstraints gbc_rdbtnTerrainTile = new GridBagConstraints();
	    gbc_rdbtnTerrainTile.anchor = GridBagConstraints.NORTHWEST;
	    gbc_rdbtnTerrainTile.insets = new Insets(0, 0, 5, 5);
	    gbc_rdbtnTerrainTile.gridx = 0;
	    gbc_rdbtnTerrainTile.gridy = 1;
	    panel.add(rdbtnTerrainTile, gbc_rdbtnTerrainTile);
	    rdbtnTerrainTile.setSelected(true);
	    buttonGroup.add(rdbtnTerrainTile);
	    
	    JRadioButton rdbtnBuildableTile = new JRadioButton("Buildable tile");
	    GridBagConstraints gbc_rdbtnBuildableTile = new GridBagConstraints();
	    gbc_rdbtnBuildableTile.anchor = GridBagConstraints.NORTHWEST;
	    gbc_rdbtnBuildableTile.insets = new Insets(0, 0, 5, 5);
	    gbc_rdbtnBuildableTile.gridx = 0;
	    gbc_rdbtnBuildableTile.gridy = 2;
	    panel.add(rdbtnBuildableTile, gbc_rdbtnBuildableTile);
	    buttonGroup.add(rdbtnBuildableTile);
	    
	    JRadioButton rdbtnPathTile = new JRadioButton("Path tile");
	    GridBagConstraints gbc_rdbtnPathTile = new GridBagConstraints();
	    gbc_rdbtnPathTile.anchor = GridBagConstraints.NORTHWEST;
	    gbc_rdbtnPathTile.insets = new Insets(0, 0, 5, 5);
	    gbc_rdbtnPathTile.gridx = 0;
	    gbc_rdbtnPathTile.gridy = 3;
	    panel.add(rdbtnPathTile, gbc_rdbtnPathTile);
	    buttonGroup.add(rdbtnPathTile);
	    
	    JRadioButton rdbtnPlayerBaseTile = new JRadioButton("Player base tile");
	    GridBagConstraints gbc_rdbtnPlayerBaseTile = new GridBagConstraints();
	    gbc_rdbtnPlayerBaseTile.anchor = GridBagConstraints.NORTHWEST;
	    gbc_rdbtnPlayerBaseTile.insets = new Insets(0, 0, 5, 5);
	    gbc_rdbtnPlayerBaseTile.gridx = 0;
	    gbc_rdbtnPlayerBaseTile.gridy = 4;
	    panel.add(rdbtnPlayerBaseTile, gbc_rdbtnPlayerBaseTile);
	    buttonGroup.add(rdbtnPlayerBaseTile);
	    
	    JRadioButton rdbtnEnemyStartTile = new JRadioButton("Enemy start tile");
	    GridBagConstraints gbc_rdbtnEnemyStartTile = new GridBagConstraints();
	    gbc_rdbtnEnemyStartTile.anchor = GridBagConstraints.NORTHWEST;
	    gbc_rdbtnEnemyStartTile.insets = new Insets(0, 0, 5, 5);
	    gbc_rdbtnEnemyStartTile.gridx = 0;
	    gbc_rdbtnEnemyStartTile.gridy = 5;
	    panel.add(rdbtnEnemyStartTile, gbc_rdbtnEnemyStartTile);
	    buttonGroup.add(rdbtnEnemyStartTile);
	    
	    JRadioButton rdbtnPlaceWaypoint = new JRadioButton("Place Waypoint");
	    GridBagConstraints gbc_rdbtnPlaceWaypoint = new GridBagConstraints();
	    gbc_rdbtnPlaceWaypoint.insets = new Insets(0, 0, 0, 5);
	    gbc_rdbtnPlaceWaypoint.anchor = GridBagConstraints.NORTHWEST;
	    gbc_rdbtnPlaceWaypoint.gridx = 0;
	    gbc_rdbtnPlaceWaypoint.gridy = 6;
	    panel.add(rdbtnPlaceWaypoint, gbc_rdbtnPlaceWaypoint);
	    buttonGroup.add(rdbtnPlaceWaypoint);
	    mapPanel.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		int scale = Properties.INSTANCE.getTileScale();
	    		
	    		int x = e.getX() / scale;
	    		int y = e.getY() / scale;
	    		
	    		mapPanel.setMapItem(x, y, MapItem.createBuildableMapItem());
	    	}
	    });
	    
	    splitPane.setRightComponent(mapPanel);
	    this.setTitle("STD Map Editor");
	    this.setLocationRelativeTo(null); // Center screen
	    this.setSize(690,547);
	    
	    JMenuBar menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    JMenu mnFile = new JMenu("File");
	    menuBar.add(mnFile);
	    
	    JMenuItem mntmNewMap = new JMenuItem("New...");
	    mntmNewMap.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		NewMapWizard wizard = new NewMapWizard();
	    		wizard.setLocationRelativeTo(null);
	    		wizard.setVisible(true);
	    	}
	    });
	    mnFile.add(mntmNewMap);
	    
	    JMenuItem mntmOpen = new JMenuItem("Open...");
	    mnFile.add(mntmOpen);
	    
	    JSeparator separator_1 = new JSeparator();
	    mnFile.add(separator_1);
	    
	    JMenuItem mntmSaveAs = new JMenuItem("Save as...");
	    mnFile.add(mntmSaveAs);
	    
	    JSeparator separator = new JSeparator();
	    mnFile.add(separator);
	    
	    JMenuItem mntmExit = new JMenuItem("Exit");
	    mntmExit.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.exit(0);
	    	}
	    });
	    mnFile.add(mntmExit);
	    
	    JMenu mnEdit = new JMenu("Edit");
	    menuBar.add(mnEdit);
	    
	    JMenuItem mntmMenuItemGoes = new JMenuItem("Menu item goes here...");
	    mnEdit.add(mntmMenuItemGoes);
	}
	
	@Subscribe
	public void createNewMap(NewMapEvent event) {
		splitPane.setVisible(true);
		splitPane.setEnabled(true);
		nothingLoadedPanel.setVisible(false);
	}
}
