package bwyap.familyfeud.gui.window;

import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bwyap.familyfeud.game.fastmoney.state.FastMoney;
import bwyap.familyfeud.gui.UIManager;
import bwyap.familyfeud.gui.control.FastMoneyAnswerPanel;

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
	private FastMoney fastmoney;

	public FastMoneyWindow(GameWindow gameWindow, ControlWindow controlWindow, FastMoney fastmoney) {
		super("Fast money!", WIDTH, HEIGHT);
		this.gameWindow = gameWindow;
		this.fastmoney = fastmoney;
	}
	
	/**
	 * Initialize window components
	 */
	public void initWindow() {
		setLocationRelativeTo(controlWindow);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLayout(new GridBagLayout());
		
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
		fastmoney.reset();
	}
	
}
