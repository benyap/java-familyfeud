package bwyap.familyfeud.gui.window;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.gui.GBC;
import bwyap.familyfeud.gui.UIManager;
import bwyap.familyfeud.gui.control.ConsolePanel;
import bwyap.familyfeud.gui.control.FamilyPanel;
import bwyap.familyfeud.gui.control.ManageFamilyPanel;
import bwyap.familyfeud.gui.control.QuestionControlPanel;
import bwyap.familyfeud.gui.control.QuestionSelectionPanel;
import bwyap.familyfeud.gui.control.QuestionSetLoaderPanel;
import bwyap.familyfeud.gui.control.SoundControlPanel;
import bwyap.familyfeud.gui.control.StatePanel;
import bwyap.familyfeud.gui.control.StatePlayPanel;
import bwyap.familyfeud.gui.control.WindowControlPanel;
import bwyap.familyfeud.sound.SoundManager;
import bwyap.utility.logging.Logger;

/**
 * This is a control window with controls to 
 * run the Family Feud game from a separate window
 * @author bwyap
 *
 */
public class ControlWindow extends FamilyFeudWindow {
	
	private static final long serialVersionUID = -4445104890877967661L;

	public static final int WIDTH = UIManager.getInstance().getProperty("controlwindow.width");
	public static final int HEIGHT = UIManager.getInstance().getProperty("controlwindow.height");
	public static final int DEFAULT_BORDER_WIDTH = 1;
	public static final int SELECTED_BORDER_WIDTH = 2;
	
	private GameWindow gameWindow;
	private FamilyFeudGame game;
	
	private JPanel contentPane;
	private ConsolePanel consolePanel;
	private WindowControlPanel windowControlPanel;
	private StatePanel statePanel;
	private ManageFamilyPanel manageFamilyPanel;
	private QuestionSetLoaderPanel questionLoaderPanel;
	private QuestionSelectionPanel questionSelectionPanel;
	private StatePlayPanel statePlayPanel;
	private FamilyPanel familyPanel;
	private QuestionControlPanel questionControlPanel;
	private SoundControlPanel soundControlPanel;
	
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
		//setAlwaysOnTop(true);
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
		
		manageFamilyPanel = new ManageFamilyPanel(game);
		manageFamilyPanel.setEnabled(false);
		
		statePanel = new StatePanel(this, game);
		
		questionSelectionPanel = new QuestionSelectionPanel(game);
		questionSelectionPanel.setEnabled(false);
		
		questionLoaderPanel = new QuestionSetLoaderPanel(game, questionSelectionPanel);
		questionLoaderPanel.setEnabled(false);

		statePlayPanel = new StatePlayPanel(this, game);
		statePlayPanel.setEnabled(false);
		
		familyPanel = new FamilyPanel(game);
		familyPanel.setEnabled(false);
		
		questionControlPanel = new QuestionControlPanel(game);
		questionControlPanel.setEnabled(false);
		
		soundControlPanel = new SoundControlPanel();
		
		contentPane.add(consolePanel, new GBC(0, 4).setSpan(2, 1));
		contentPane.add(windowControlPanel, new GBC(0, 0).setSpan(1, 2));
		contentPane.add(manageFamilyPanel, new GBC(0, 2));
		contentPane.add(statePanel, new GBC(2, 0).setSpan(1, 3));
		contentPane.add(questionSelectionPanel, new GBC(1, 1).setSpan(1, 2));
		contentPane.add(questionLoaderPanel, new GBC(1, 0));
		contentPane.add(statePlayPanel, new GBC(2, 3));
		contentPane.add(familyPanel, new GBC(0, 3));
		contentPane.add(questionControlPanel, new GBC(1, 3));
		contentPane.add(soundControlPanel, new GBC(2, 4));
		
		addKeyBindings();
		
		add(contentPane, BorderLayout.CENTER);
		Logger.info("Control window initialized.");
	}
	
	/**
	 * Add key bindings to help control the game window
	 */
	private void addKeyBindings() {
		// Toggle full screen 
		getRootPane().getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.META_DOWN_MASK), "togglefullscreen");
		getRootPane().getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK), "togglefullscreen");
		getRootPane().getActionMap().put("togglefullscreen", new AbstractAction() {
			private static final long serialVersionUID = 8226153971656224768L;
			public void actionPerformed(ActionEvent e) {
				windowControlPanel.toggleFullscreen();
				Logger.info("Toggled fullscreen");
			}
		});
		
		// Toggle show game window
		getRootPane().getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.META_DOWN_MASK), "toggleshowscreen");
		getRootPane().getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK), "toggleshowscreen");
		getRootPane().getActionMap().put("toggleshowscreen", new AbstractAction() {
			private static final long serialVersionUID = 8226153971656224768L;
			public void actionPerformed(ActionEvent e) {
				windowControlPanel.toggleShowscreen();
				Logger.info("Toggled show screen");

			}
		});
		
		// Toggle mute 
		getRootPane().getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.META_DOWN_MASK), "togglemute");
		getRootPane().getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK), "togglemute");
		getRootPane().getActionMap().put("togglemute", new AbstractAction() {
			private static final long serialVersionUID = 8226153971656224768L;
			public void actionPerformed(ActionEvent e) {
				SoundManager.getInstance().toggleMute();
				Logger.info("Toggled mute");
			}
		});

		// Dislpay help commands
		Logger.warning("HINT: Use <cmd + D> or <ctrl + D> to toggle show game window");
		Logger.warning("HINT: Use <cmd + F> or <ctrl + F> to toggle fullscreen");
		Logger.warning("HINT: Use <cmd + M> or <ctrl + M> to toggle mute");
	}
	
	public void setFamilyPanelEnabled(boolean enabled) {
		manageFamilyPanel.setEnabled(enabled);
	}
	
	public void setQuestionLoaderEnabled(boolean enabled) {
		questionLoaderPanel.setEnabled(enabled);
	}
	
	public void reloadQuestions() {
		questionSelectionPanel.reload();
	}
	
	public void setQuestionSelectionEnabled(boolean enabled) {
		questionSelectionPanel.setEnabled(enabled);
	}
	
	public void setStatePlayEnabled(boolean enabled) {
		statePlayPanel.setEnabled(enabled);
	}
	
	public void loadFamilies() {
		familyPanel.loadFamilies();
	}

	public void setChooseFamilyEnabled(boolean enabled) {
		familyPanel.setEnabled(enabled);
	}
	
	public void setChooseFamily(int command) {
		familyPanel.setCommand(command);
		familyPanel.setEnabled(true);
	}
	
	public void updateFamilyPanel() {
		familyPanel.loadFamilies();
	}
	
	public void setQuestionControlEnabled(boolean enabled) {
		if (enabled) questionControlPanel.loadQuestion();
		questionControlPanel.setEnabled(enabled);
	}
	
	public void disableQuestionStrike() {
		questionControlPanel.disableStrike();
	}
	
	/**
	 * Reset GUI components for a new game
	 */
	public void reset() {
		questionLoaderPanel.reset();
		questionSelectionPanel.reset();
		questionControlPanel.reset();
		familyPanel.reset();
	}

}
