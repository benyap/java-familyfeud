package bwyap.familyfeud.gui.control;

import static bwyap.familyfeud.gui.window.ControlWindow.DEFAULT_BORDER_WIDTH;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import bwyap.familyfeud.game.fastmoney.state.FastMoney;
import bwyap.familyfeud.gui.GBC;
import bwyap.familyfeud.gui.UIManager;
import bwyap.familyfeud.sound.SoundManager;
import bwyap.gridgame.res.ResourceLoader;

/**
 * This panel holds the input components for a player in Fast Money
 * @author bwyap
 *
 */
public class FastMoneyAnswerPanel extends JPanel {
	
	private static final long serialVersionUID = -4766058372180946035L;
	
	public static final int WIDTH = UIManager.getInstance().getProperty("fastmoneyanswerpanel.width");
	public static final int HEIGHT = UIManager.getInstance().getProperty("fastmoneyanswerpanel.height");
	
	private FastMoney fastmoney;
	private final int PLAYER;
	
	private JLabel title;
	private List<JLabel> labels;
	private List<JTextField> answers;
	private List<JTextField> scores;
	private List<JButton> revealAnswers;
	private List<JButton> revealScores;
	private JButton save;
	private JButton showAnswers;
	
	public FastMoneyAnswerPanel(FastMoney fastmoney, int player) {
		this.fastmoney = fastmoney;
		this.PLAYER = player;
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		setLayout(new GridBagLayout());
		initComponents();
	}
	
	/**
	 * Initialize panel components
	 */
	private void initComponents() {
		title = new JLabel("PLAYER " + (PLAYER + 1) + " ANSWERS");
		title.setFont(new Font(ResourceLoader.DEFAULT_FONT_NAME, Font.BOLD, 14));
		
		showAnswers = new JButton("Hide answers");
		showAnswers.setToolTipText("Hide the player's answers from the screen");
		showAnswers.setPreferredSize(new Dimension(150, 30));
		showAnswers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fastmoney.showAnswers(PLAYER)) {
					fastmoney.setShow(PLAYER, false);
					showAnswers.setText("Show answers");
					showAnswers.setToolTipText("Show the player's answers on the screen");
				}
				else {
					fastmoney.setShow(PLAYER, true);
					showAnswers.setText("Hide answers");
					showAnswers.setToolTipText("Hide the player's answers from the screen");
				}
			}
		});
		
		labels = new ArrayList<JLabel>();
		answers = new ArrayList<JTextField>();
		scores = new ArrayList<JTextField>();
		revealAnswers = new ArrayList<JButton>();
		revealScores = new ArrayList<JButton>();
		
		for(int i = 0; i < FastMoney.QUESTIONS; i++) {
			JLabel label = new JLabel("Q" + (i+1) + ".");
			JTextField answer = new JTextField();
			answer.setPreferredSize(new Dimension(140, 30));
			answer.setMinimumSize(new Dimension(150, 30));
			answer.setToolTipText("Player " + (PLAYER + 1) + "\'s answer");
			answer.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) { save.setEnabled(true); save.setText("Save"); }
				@Override
				public void removeUpdate(DocumentEvent e) { save.setEnabled(true); save.setText("Save"); }
				@Override
				public void changedUpdate(DocumentEvent e) {}
			});
			
			JTextField score = new JTextField();
			score.setToolTipText("Answer score (must be a non-negative integer)");
			score.setPreferredSize(new Dimension(30, 30));
			score.setMinimumSize(new Dimension(50, 30));
			score.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) { validateScores(); }
				@Override
				public void removeUpdate(DocumentEvent e) { validateScores(); }
				@Override
				public void changedUpdate(DocumentEvent e) {}
			});

			final int question = i;

			JButton revealScore = new JButton("Reveal S");
			revealScore.setToolTipText("Reveal the score");
			revealScore.setEnabled(false);
			revealScore.setPreferredSize(new Dimension(85, 30));
			revealScore.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (fastmoney.getAnswer(PLAYER, question).isRevealedScore()) {
						fastmoney.setRevealedScore(PLAYER, question, false);
						revealScore.setText("Reveal S");
						revealScore.setToolTipText("Reveal the score");
					}
					else {
						fastmoney.setRevealedScore(PLAYER, question, true);
						revealScore.setText("Hide S");
						revealScore.setToolTipText("Hide the score");
						if (fastmoney.getAnswer(PLAYER, question).getScore() > 0) {
							SoundManager.getInstance().playClip("fm_answer");
						}
						else SoundManager.getInstance().playClip("fm_strike");
					}
				}
			});
			
			JButton revealAnswer = new JButton("Reveal A");
			revealAnswer.setToolTipText("Reveal the answer");
			revealAnswer.setEnabled(false);
			revealAnswer.setPreferredSize(new Dimension(85, 30));
			revealAnswer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (fastmoney.getAnswer(PLAYER, question).isRevealedAnswer()) {
						fastmoney.setRevealedAnswer(PLAYER, question, false);
						revealAnswer.setText("Reveal A");
						revealAnswer.setToolTipText("Reveal the answer");
						if (fastmoney.getAnswer(PLAYER, question).isRevealedScore()) revealScore.doClick();
					}
					else {
						SoundManager.getInstance().playClip("blip");
						fastmoney.setRevealedAnswer(PLAYER, question, true);
						revealAnswer.setText("Hide A");
						revealAnswer.setToolTipText("Hide the answer");
					}
				}
			});
			
			
			labels.add(label);
			answers.add(answer);
			scores.add(score);
			revealAnswers.add(revealAnswer);
			revealScores.add(revealScore);
			add(label, new GBC(0, i + 1));
			add(answer, new GBC(1, i + 1));
			add(score, new GBC(2, i + 1));
			add(revealAnswer, new GBC(3, i + 1));
			add(revealScore, new GBC(4, i + 1));
		}
		
		save = new JButton("Save");
		save.setToolTipText("Save the scores and answers (all inputs must be valid)");
		save.setPreferredSize(new Dimension(100, 30));
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Save the answers to the fastmoney object
				for(int i = 0; i < FastMoney.QUESTIONS; i++) {
					if (!scores.get(i).getText().equals("")) {
						int score = Integer.parseInt(scores.get(i).getText());
						fastmoney.setScore(PLAYER, i, score);
						fastmoney.setAnswer(PLAYER, i, answers.get(i).getText());
						save.setEnabled(false);
						save.setText("Saved");
						enableReveal(!fastmoney.isTimerRunning());
					}
				}
			}
		});
		
		add(title, new GBC(0, 0).setSpan(6, 1));
		add(save, new GBC(0, 6).setSpan(3, 1));
		add(showAnswers, new GBC(3, 6).setSpan(2, 1));
	}
	
	/**
	 * Reset all controls
	 */
	public void reset() {
		for(int i = 0; i < FastMoney.QUESTIONS; i++) {
			answers.get(i).setText("");
			scores.get(i).setText("");
		}
		validateScores();
		enableReveal(false);
		showAnswers.setEnabled(true);
		showAnswers.setText("Hide answers");
		showAnswers.setToolTipText("Hide the player's answers from the screen");
	}
	
	/**
	 * Validate that the scores are numerical
	 */
	private void validateScores() {
		boolean valid = true;
		for(JTextField score : scores) {
			String text = score.getText();
			try {
				if (!text.equals("")) {
					if (Integer.parseInt(text) < 0)
						throw new Exception();
				}
				score.setBackground(Color.WHITE);
				save.setEnabled(true);
				save.setText("Save");
			}
			catch (Exception e) { 
				score.setBackground(new Color(0xff9999));
				valid = false;
			}
		}
		
		save.setEnabled(valid);
	}
	
	/**
	 * Set whether the user can reveal answers or not
	 * @param reveal
	 */
	public void enableReveal(boolean reveal) {
		if (reveal) {
			for(int i = 0; i < FastMoney.QUESTIONS; i++) {
				if (!scores.get(i).getText().equals("")) {
					this.revealAnswers.get(i).setEnabled(true);
					this.revealScores.get(i).setEnabled(true);
				}
			}
		}
		else {
			for(int i = 0; i < FastMoney.QUESTIONS; i++) {
				this.revealAnswers.get(i).setEnabled(false);
				this.revealScores.get(i).setEnabled(false);
			}
		}
	}

}
