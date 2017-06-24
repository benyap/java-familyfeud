package bwyap.familyfeud.gui.control;

import static bwyap.familyfeud.gui.window.ControlWindow.DEFAULT_BORDER_WIDTH;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.state.FFStateType;
import bwyap.familyfeud.gui.GBC;
import bwyap.familyfeud.gui.UIManager;
import bwyap.familyfeud.gui.window.ControlWindow;
import bwyap.gridgame.res.ResourceLoader;
import bwyap.utility.logging.Logger;

/**
 * A panel to control state transitions
 * @author bwyap
 *
 */
public class StatePanel extends JPanel {

	private static final long serialVersionUID = 4051495798628977025L;

	public static final int WIDTH = UIManager.getInstance().getProperty("statepanel.width");
	public static final int HEIGHT = UIManager.getInstance().getProperty("statepanel.height");

	private ControlWindow window;
	private FamilyFeudGame game;
	
	private JLabel title;
	private JButton newGame;
	private JButton addFamily;
	private JButton loadQuestions;
	private JButton initGame;
	private JButton play;
	private JButton endGame;
	private JButton fastmoney;
	
	public StatePanel(ControlWindow window, FamilyFeudGame game) {
		this.window = window;
		this.game = game;
		setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		initComponents();
	}
	
	private void initComponents() {
		title = new JLabel("STATE CONTROL");
		title.setFont(new Font(ResourceLoader.DEFAULT_FONT_NAME, Font.BOLD, 14));
		
		newGame = new JButton("New Game");
		newGame.setToolTipText("Start a new game (removes any questions and families)");
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.changeState(FFStateType.NEW_GAME);
				addFamily.setEnabled(true);
				loadQuestions.setEnabled(true);
				initGame.setEnabled(true);
				newGame.setEnabled(false);
				fastmoney.setEnabled(false);
				window.setFamilyPanelEnabled(false);
				window.setChooseFamilyEnabled(false);
				window.reset();
			}
		});
		
		addFamily = new JButton("Manage families");
		addFamily.setToolTipText("Add or remove families");
		addFamily.setEnabled(false);
		addFamily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.changeState(FFStateType.ADD_FAMILY)) {
					window.setFamilyPanelEnabled(true);
					window.setQuestionLoaderEnabled(false);
				}
			}
		});
		
		loadQuestions = new JButton("Load question set");
		loadQuestions.setToolTipText("Load a set of questions from an external JSON file");
		loadQuestions.setEnabled(false);
		loadQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.changeState(FFStateType.LOAD_QUESTIONS);
				window.setFamilyPanelEnabled(false);
				window.setQuestionLoaderEnabled(true);
			}
		});
		
		initGame = new JButton("Initialize game");
		initGame.setToolTipText("Initialize the game (must have loaded a question and have at least two families)");
		initGame.setEnabled(false);
		initGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.changeState(FFStateType.INITIALIZE_GAME)) {
					initGame.setEnabled(false);
					newGame.setEnabled(false);
					addFamily.setEnabled(false);
					loadQuestions.setEnabled(false);
					window.setFamilyPanelEnabled(false);
					window.setQuestionLoaderEnabled(false);
					window.loadFamilies();
					play.setEnabled(true);
				}
			}
		});
		
		play = new JButton("Play game");
		play.setToolTipText("Switch the game into PLAY mode (use PLAY CONTROL to control game flow)");
		play.setEnabled(false);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.changeState(FFStateType.PLAY)) {
					play.setEnabled(false);
					initGame.setEnabled(false);
					fastmoney.setEnabled(true);
					endGame.setEnabled(true);
					window.setQuestionSelectionEnabled(true);
					window.setStatePlayEnabled(true);
				}
			}
		});
		
		endGame = new JButton("End game");
		endGame.setToolTipText("End the game and show the scores");
		endGame.setEnabled(false);
		endGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean transition = false;
				if (!game.finished())  {
					transition = (JOptionPane.showConfirmDialog(StatePanel.this, 
							"Are you sure you want to end the game?",
							"Ending game",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE) == 
							JOptionPane.OK_OPTION);
				}
				else transition = true;
				if (transition && game.changeState(FFStateType.END_GAME)) {
					play.setEnabled(false);
					endGame.setEnabled(false);
					newGame.setEnabled(true);
					fastmoney.setEnabled(true);
					window.setQuestionSelectionEnabled(false);
					window.setStatePlayEnabled(false);
					window.setQuestionControlEnabled(false);
				}
			}
		});
		
		fastmoney = new JButton("Fast money");
		fastmoney.setToolTipText("Play fast money");
		fastmoney.setEnabled(false);
		fastmoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.changeState(FFStateType.FAST_MONEY)) {
					Logger.info("Playing fast money!");
					Logger.err("Feature not implemented.");
					fastmoney.setEnabled(false);
					window.setQuestionSelectionEnabled(false);
					window.setQuestionControlEnabled(false);
					if (!game.finished()) play.setEnabled(true);
					endGame.setEnabled(true);
				}
			}
		});
		
		add(title, new GBC(0, 0));
		add(newGame, new GBC(0, 1).setFill(1));
		add(addFamily, new GBC(0, 2).setFill(1));
		add(loadQuestions, new GBC(0, 3).setFill(1));
		add(initGame, new GBC(0, 4).setFill(1));
		add(play, new GBC(0, 5).setFill(1));
		add(endGame, new GBC(0, 6).setFill(1));
		add(fastmoney, new GBC(0, 7).setFill(1));
	}
	
}
