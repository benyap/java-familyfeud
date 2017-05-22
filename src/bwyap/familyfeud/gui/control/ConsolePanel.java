package bwyap.familyfeud.gui.control;

import static bwyap.familyfeud.gui.window.ControlWindow.DEFAULT_BORDER_WIDTH;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import bwyap.familyfeud.gui.UIManager;
import bwyap.utility.logging.ConsoleLogger;
import bwyap.utility.logging.Logger;

/**
 * A console panel holds an instance of the Console within a scroll pane
 * @author bwyap
 *
 */
public class ConsolePanel extends JPanel {
	
	private static final long serialVersionUID = 7694955474110163790L;

	public static final int WIDTH = UIManager.getInstance().getDimension("consolepanel.width");
	public static final int HEIGHT = UIManager.getInstance().getDimension("consolepanel.height");
	
	private JScrollPane scroll;
	private static JTextPane text;
	
	/**
	 * Create a new console panel
	 */
	public ConsolePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		setLayout(new BorderLayout());
		initComponents();
	}
	
	/**
	 * Initialize panel components
	 */
	private void initComponents() {
		text = ConsoleLogger.getInstance();
		
		scroll = new JScrollPane(text);
		//scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(scroll, BorderLayout.CENTER);
		Logger.info("Console panel initialized");
	}
	
}