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
	private List<JButton> reveal;
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
		reveal = new ArrayList<JButton>();
		
		for(int i = 0; i < FastMoney.QUESTIONS; i++) {
			JLabel label = new JLabel("Q" + (i+1) + ".");
			JTextField answer = new JTextField();
			answer.setToolTipText("Player " + (PLAYER + 1) + "\'s answer");
			answer.setColumns(10);
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
			score.setColumns(3);
			score.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) { validateScores(); }
				@Override
				public void removeUpdate(DocumentEvent e) { validateScores(); }
				@Override
				public void changedUpdate(DocumentEvent e) {}
			});
			
			JButton button = new JButton("Reveal");
			button.setToolTipText("Reveal the answer");
			button.setEnabled(false);
			button.setPreferredSize(new Dimension(100, 30));
			final int question = i;
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (fastmoney.getAnswer(PLAYER, question).isRevealedAnswer()) {
						fastmoney.setRevealedAnswer(PLAYER, question, false);
						fastmoney.setRevealedScore(PLAYER, question, false);
						button.setText("Reveal");
						button.setToolTipText("Reveal the answer and its score");
					}
					else {
						SoundManager.getInstance().playClip("blip");
						fastmoney.setRevealedAnswer(PLAYER, question, true);
						try { Thread.sleep(1000); }
						catch (Exception ex) { }
						fastmoney.setRevealedScore(PLAYER, question, true);
						button.setText("Hide");
						button.setToolTipText("Hide the answer and its score");
						if (fastmoney.getAnswer(PLAYER, question).getScore() > 0) {
							SoundManager.getInstance().playClip("fm_answer");
						}
						else SoundManager.getInstance().playClip("fm_strike");
					}
				}
			});
			
			labels.add(label);
			answers.add(answer);
			scores.add(score);
			reveal.add(button);
			add(label, new GBC(0, i + 1));
			add(answer, new GBC(1, i + 1));
			add(score, new GBC(2, i + 1));
			add(button, new GBC(3, i + 1));
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
						reveal.get(i).setEnabled(true);
					}
				}
			}
		});
		
		add(title, new GBC(0, 0).setSpan(5, 1));
		add(save, new GBC(0, 6).setSpan(2, 1));
		add(showAnswers, new GBC(2, 6).setSpan(2, 1));

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

}
