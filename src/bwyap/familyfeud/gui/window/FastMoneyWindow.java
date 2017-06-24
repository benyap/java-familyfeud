package bwyap.familyfeud.gui.window;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.gui.UIManager;

/**
 * A control window to control a fast money game
 * @author bwyap
 *
 */
public class FastMoneyWindow extends FamilyFeudWindow {
	
	private static final long serialVersionUID = 5076143532222588736L;
	
	public static final int WIDTH = UIManager.getInstance().getProperty("fastmoneywindow.width");
	public static final int HEIGHT = UIManager.getInstance().getProperty("fastmoneywindow.height");

	private GameWindow gameWindow;
	private ControlWindow controlWindow;
	private FamilyFeudGame game;

	public FastMoneyWindow(GameWindow gameWindow, ControlWindow controlWindow, FamilyFeudGame game) {
		super("Fast money!", WIDTH, HEIGHT);
		this.gameWindow = gameWindow;
		this.game = game;
	}
	
	/**
	 * Initialize window components
	 */
	public void initWindow() {
		setLocationRelativeTo(controlWindow);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(FastMoneyWindow.this,
					"Are you sure you want to exit Fast Money?",
					"Exit Fast Money",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE)
					== JOptionPane.OK_OPTION) {
					dispose();
				}
			}
		});
	}
	
	/**
	 * Reset the fast money window
	 */
	public void reset() {
		setLocationRelativeTo(null);
	}
	
}
