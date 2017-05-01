package bwyap.familyfeud.gui.control;

import static bwyap.familyfeud.game.play.FFPlayStateType.ALLOCATE_POINTS;
import static bwyap.familyfeud.game.play.FFPlayStateType.FACE_OFF;
import static bwyap.familyfeud.game.play.FFPlayStateType.FAMILY_PLAY;
import static bwyap.familyfeud.game.play.FFPlayStateType.FAMILY_STEAL;
import static bwyap.familyfeud.game.play.FFPlayStateType.REVEAL_ANSWERS;
import static bwyap.familyfeud.game.play.FFPlayStateType.SELECT_QUESTION;

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

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.play.FFPlayState;
import bwyap.familyfeud.game.play.state.StateFaceOff;
import bwyap.familyfeud.game.play.state.StateFamilyPlay;
import bwyap.familyfeud.game.play.state.StateFamilySteal;
import bwyap.familyfeud.game.state.StatePlay;
import bwyap.familyfeud.gui.GBC;
import bwyap.familyfeud.gui.window.ControlWindow;
import bwyap.gridgame.res.ResourceLoader;

/**
 * A panel to control state play transitions 
 * @author bwyap
 *
 */
public class StatePlayPanel extends JPanel {

	private static final long serialVersionUID = 4051495798628977025L;

	public static final int WIDTH = StatePanel.WIDTH;
	public static final int HEIGHT = ControlWindow.HEIGHT - ConsolePanel.HEIGHT - StatePanel.HEIGHT - 25;

	private ControlWindow window;
	private FamilyFeudGame game;
	
	private JLabel title;
	private JButton select;
	private JButton faceOff;
	private JButton play;
	private JButton steal;
	private JButton allocate;
	private JButton reveal;

	public StatePlayPanel(ControlWindow window, FamilyFeudGame game) {
		this.window = window;
		this.game = game;
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		initComponents();
	}
	
	private void initComponents() {
		title = new JLabel("PLAY CONTROL");
		title.setFont(new Font(ResourceLoader.DEFAULT_FONT_NAME, Font.BOLD, 14));
		
		select = new JButton("Select question");
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getState().executeAction(StatePlay.CHANGESTATE_SELECTQUESTION, null)) {
					enableButtons();
					window.setQuestionSelectionEnabled(true);
				}
			}
		});
		
		faceOff = new JButton("Face off");
		faceOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getState().executeAction(StatePlay.CHANGESTATE_FACEOFF, null)) {
					enableButtons();
					window.setChooseFamily(StateFaceOff.ACTION_CHOOSEFAMILY);	
					window.setQuestionSelectionEnabled(false);
					window.setQuestionControlEnabled(true);
				}
			}
		});
		
		play = new JButton("Family play");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getState().executeAction(StatePlay.CHANGESTATE_FAMILYPLAY, null)) {
					enableButtons();
					window.setChooseFamily(StateFamilyPlay.ACTION_SELECTSTEALFAMILY);					
				}
			}
		});
		
		steal = new JButton("Family steal");
		steal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getState().executeAction(StatePlay.CHANGESTATE_FAMILYSTEAL, null)) {
					window.setChooseFamily(StateFamilySteal.ACTION_SELECTWINFAMILY);
				}
			}
		});
		
		allocate = new JButton("Allocate points");
		allocate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getState().executeAction(StatePlay.CHANGESTATE_ALLOCATEPOINTS, null)) {
					game.getState().executeAction(StatePlay.CHANGESTATE_REVEALANSWERS, null);
					// State should have automatically changed to reveal questions
					window.updateFamilyPanel();
					window.setChooseFamilyEnabled(false);
					window.setQuestionControlEnabled(true);
					enableButtons();
				}
			}
		});
		
		reveal = new JButton("Reveal questions");
		reveal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getState().executeAction(StatePlay.CHANGESTATE_REVEALANSWERS, null)) {
					// This may never happen 
					window.setQuestionControlEnabled(true);
					enableButtons();
				}
			}
		});
		
		add(title, new GBC(0, 0));
		add(select, new GBC(0, 1).setFill(1));
		add(faceOff, new GBC(0, 2).setFill(1));
		add(play, new GBC(0, 3).setFill(1));
		add(steal, new GBC(0, 4).setFill(1));
		add(allocate, new GBC(0, 5).setFill(1));
		add(reveal, new GBC(0, 6).setFill(1));
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(false);
		enableButtons();
		if (enabled) setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		else setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	}
	
	private void enableButtons() {
		if (game.getState() instanceof StatePlay) {
			FFPlayState state = ((StatePlay)game.getState()).getPlayState();	
			select.setEnabled(state.getType() == REVEAL_ANSWERS);
			faceOff.setEnabled(state.getType() == SELECT_QUESTION);
			play.setEnabled(state.getType() == FACE_OFF);
			steal.setEnabled(state.getType() == FAMILY_PLAY);
			allocate.setEnabled(state.getType() == FAMILY_PLAY || state.getType() == FAMILY_STEAL);
			reveal.setEnabled(state.getType() == ALLOCATE_POINTS);
		}
		else {
			select.setEnabled(false);
			faceOff.setEnabled(false);
			play.setEnabled(false);
			steal.setEnabled(false);
			allocate.setEnabled(false);
			reveal.setEnabled(false);
		}
	}
	
}
