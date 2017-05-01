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
import bwyap.familyfeud.gui.control.AddFamilyPanel;
import bwyap.familyfeud.gui.control.ConsolePanel;
import bwyap.familyfeud.gui.control.QuestionSelectionPanel;
import bwyap.familyfeud.gui.control.QuestionSetLoaderPanel;
import bwyap.familyfeud.gui.control.StatePanel;
import bwyap.familyfeud.gui.control.StatePlayPanel;
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
	public static final int HEIGHT = 640;
	
	private GameWindow gameWindow;
	private FamilyFeudGame game;
	
	private JPanel contentPane;
	private ConsolePanel consolePanel;
	private WindowControlPanel windowControlPanel;
	private StatePanel statePanel;
	private AddFamilyPanel familyPanel;
	private QuestionSetLoaderPanel questionLoaderPanel;
	private QuestionSelectionPanel questionSelectionPanel;
	private StatePlayPanel statePlayPanel;
	
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
		
		windowControlPanel = new WindowControlPanel(gameWindow);
		
		familyPanel = new AddFamilyPanel(game);
		familyPanel.setEnabled(false);
		
		statePanel = new StatePanel(this, game);
		
		questionSelectionPanel = new QuestionSelectionPanel(game);
		questionSelectionPanel.setEnabled(false);
		
		questionLoaderPanel = new QuestionSetLoaderPanel(game, questionSelectionPanel);
		questionLoaderPanel.setEnabled(false);

		statePlayPanel = new StatePlayPanel(this, game);
		statePlayPanel.setEnabled(false);
		
		contentPane.add(consolePanel, new GBC(0, 4).setSpan(3, 1));
		contentPane.add(windowControlPanel, new GBC(0, 0).setSpan(1, 2));
		contentPane.add(familyPanel, new GBC(0, 2));
		contentPane.add(statePanel, new GBC(2, 0).setSpan(1, 3));
		contentPane.add(questionSelectionPanel, new GBC(1, 1).setSpan(1, 2));
		contentPane.add(questionLoaderPanel, new GBC(1, 0));
		contentPane.add(statePlayPanel, new GBC(2, 3));
		
		add(contentPane, BorderLayout.CENTER);
		Logger.info("Control window initialized.");
	}
	
	public void setFamilyPanelEnabled(boolean enabled) {
		familyPanel.setEnabled(enabled);
	}
	
	public void setQuestionLoaderEnabled(boolean enabled) {
		questionLoaderPanel.setEnabled(enabled);
	}
	
	public void setQuestionSelectionEnabled(boolean enabled) {
		questionSelectionPanel.setEnabled(enabled);
	}
	
	public void setStatePlayEnabled(boolean enabled) {
		statePlayPanel.setEnabled(enabled);
	}

	/**
	 * Reset GUI components for a new game
	 */
	public void reset() {
		questionLoaderPanel.reset();
		questionSelectionPanel.reset();
	}

}
