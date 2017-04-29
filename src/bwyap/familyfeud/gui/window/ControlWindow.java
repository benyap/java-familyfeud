package bwyap.familyfeud.gui.window;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.gui.GBC;
import bwyap.familyfeud.gui.control.ConsolePanel;
import bwyap.familyfeud.gui.control.StatePanel;
import bwyap.familyfeud.gui.control.WindowControlPanel;
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
	
	private GameWindow gameWindow;
	private FamilyFeudGame game;
	
	private JPanel contentPane;
	private ConsolePanel consolePanel;
	private WindowControlPanel windowControlPanel;
	private StatePanel statePanel;
	
	/**
	 * Create a new control window
	 * @param title
	 */
	public ControlWindow(String title, GameWindow gameWindow, FamilyFeudGame game) {
		super(title, WIDTH, HEIGHT);
		this.gameWindow = gameWindow;
		this.game = game;
	}

	/**
	 * Initialize window components
	 */
	public void initWindow() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setLayout(new BorderLayout());
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(ControlWindow.this,
					"Are you sure you want to exit the program?",
					"Exit program?",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE)
					== JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});
		
		// init components
		contentPane = new JPanel();
		contentPane.setLayout(new GridBagLayout());
		
		consolePanel = new ConsolePanel();
		contentPane.add(consolePanel, new GBC(0, 1).setSpan(2, 1));
		
		windowControlPanel = new WindowControlPanel(gameWindow);
		contentPane.add(windowControlPanel, new GBC(0, 0));
		
		statePanel = new StatePanel(game);
		contentPane.add(statePanel, new GBC(1, 0));
		
		add(contentPane, BorderLayout.CENTER);
		Logger.info("Control window initialized.");
	}

}
