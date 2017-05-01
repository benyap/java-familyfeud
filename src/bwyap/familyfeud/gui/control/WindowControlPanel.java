package bwyap.familyfeud.gui.control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bwyap.familyfeud.gui.GBC;
import bwyap.familyfeud.gui.window.GameWindow;
import bwyap.gridgame.res.ResourceLoader;

/**
 * A panel to contain game window controls.
 * @author bwyap
 *
 */
public class WindowControlPanel extends JPanel {

	private static final long serialVersionUID = 3506047425993585668L;

	public static final int WIDTH = 180;
	public static final int HEIGHT = 120;
	
	private GameWindow window;
	private JLabel title;
	private JButton fullscreen;
	private JButton showWindow;
	private JButton showFPS;
	
	public WindowControlPanel(GameWindow window) {
		this.window = window;
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		initComponents();
	}
	
	private void initComponents() {
		title = new JLabel("WINDOW CONTROL");
		title.setFont(new Font(ResourceLoader.DEFAULT_FONT_NAME, Font.BOLD, 14));
		
		showWindow = new JButton("Show game window");
		showWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (window.isVisible()) {
					if (window.isFullscreen()) window.toggleFullscreen();
					window.setVisible(false);
					showWindow.setText("Show game window");
					fullscreen.setEnabled(false);
					showFPS.setEnabled(false);
				}
				else {
					window.setVisible(true);
					showWindow.setText("Hide game window");
					fullscreen.setEnabled(true);
					showFPS.setEnabled(true);
				}
			}
		});
		
		fullscreen = new JButton("Toggle fullscreen");
		fullscreen.setEnabled(false);
		fullscreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (window.isVisible()) window.toggleFullscreen();
			}
		}); 
		
		showFPS = new JButton("Show fps");
		showFPS.setEnabled(false);
		showFPS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (window.getRenderSurface().showingFPS()) {
					window.getRenderSurface().showFPS(false);
					showFPS.setText("Show fps");
				}
				else {
					window.getRenderSurface().showFPS(true);
					showFPS.setText("Hide fps");
				}
			}
		}); 

		add(title, new GBC(0, 0));
		add(showWindow, new GBC(0, 1).setFill(1));
		add(fullscreen, new GBC(0, 2).setFill(1));
		add(showFPS, new GBC(0, 3).setFill(1));
	}

}
