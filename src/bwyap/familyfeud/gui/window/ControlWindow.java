package bwyap.familyfeud.gui.window;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import bwyap.familyfeud.gui.GBC;
import bwyap.utility.logging.ConsoleLogger;
import bwyap.utility.logging.Logger;

/**
 * This is a control window with controls to 
 * run the Family Feud game from a separate window
 * @author bwyap
 *
 */
public class ControlWindow extends FamilyFeudWindow {
	
	private static final long serialVersionUID = -4445104890877967661L;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private JPanel contentPane;
	private JTextPane consoleLogger;
	
	/**
	 * Create a new control window
	 * @param title
	 */
	public ControlWindow(String title) {
		super(title, WIDTH, HEIGHT);
	}

	/**
	 * Initialize window components
	 */
	public void initWindow() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setLayout(new BorderLayout());
		
		// init components
		contentPane = new JPanel();
		contentPane.setLayout(new GridBagLayout());
		
		consoleLogger = ConsoleLogger.getInstance();
		contentPane.add(consoleLogger, new GBC(0, 0));
		
		add(contentPane, BorderLayout.CENTER);
		Logger.info("Control window initialized.");
	}

}
