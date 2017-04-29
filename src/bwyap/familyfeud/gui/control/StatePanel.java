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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.state.FFStateType;
import bwyap.familyfeud.gui.GBC;
import bwyap.familyfeud.gui.window.ControlWindow;
import bwyap.gridgame.res.ResourceLoader;

/**
 * A panel to control state transitions
 * @author bwyap
 *
 */
public class StatePanel extends JPanel {

	private static final long serialVersionUID = 4051495798628977025L;

	public static final int WIDTH = 180;
	public static final int HEIGHT = 200;

	private ControlWindow window;
	private FamilyFeudGame game;
	
	private JLabel title;
	private JButton newGame;
	private JButton addFamily;
	private JButton loadQuestions;
	private JButton initGame;
	private JButton play;
	private JButton endGame;
	
	public StatePanel(ControlWindow window, FamilyFeudGame game) {
		this.window = window;
		this.game = game;
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		initComponents();
	}
	
	private void initComponents() {
		title = new JLabel("STATE CONTROL");
		title.setFont(new Font(ResourceLoader.DEFAULT_FONT_NAME, Font.BOLD, 14));
		
		newGame = new JButton("New Game");
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.changeState(FFStateType.NEW_GAME);
				addFamily.setEnabled(true);
				loadQuestions.setEnabled(true);
				initGame.setEnabled(true);
				newGame.setEnabled(false);
				window.setFamilyPanelEnabled(false);
				window.reset();
			}
		});
		
		addFamily = new JButton("Manage families");
		addFamily.setEnabled(false);
		addFamily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.changeState(FFStateType.ADD_FAMILY)) {
					window.setFamilyPanelEnabled(true);
					window.setQuestionLoaderEnabled(false);
				}
			}
		});
		
		loadQuestions = new JButton("Load questions");
		loadQuestions.setEnabled(false);
		loadQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.changeState(FFStateType.LOAD_QUESTIONS);
				window.setFamilyPanelEnabled(false);
				window.setQuestionLoaderEnabled(true);
			}
		});
		
		initGame = new JButton("Initialize game");
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
					play.setEnabled(true);
				}
			}
		});
		
		play = new JButton("Play game");
		play.setEnabled(false);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.changeState(FFStateType.PLAY)) {
					play.setEnabled(false);
					initGame.setEnabled(false);
					endGame.setEnabled(true);
				}
			}
		});
		
		endGame = new JButton("End game");
		endGame.setEnabled(false);
		endGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(StatePanel.this, 
						"Are you sure you want to end the game?",
						"Ending game",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE) == 
						JOptionPane.OK_OPTION) {
					if (game.changeState(FFStateType.END_GAME)) {
						play.setEnabled(false);
						endGame.setEnabled(false);
						newGame.setEnabled(true);
					}
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
	}
	
}
