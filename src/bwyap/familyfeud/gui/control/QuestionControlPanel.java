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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import bwyap.familyfeud.game.Answer;
import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.play.state.StateFamilyPlay;
import bwyap.familyfeud.game.state.StatePlay;
import bwyap.familyfeud.gui.GBC;
import bwyap.gridgame.res.ResourceLoader;

/**
 * A panel to control the question state.
 * Provides GUI components to reveal answers 
 * @author bwyap
 *
 */
public class QuestionControlPanel extends JPanel {

	private static final long serialVersionUID = 689335015288245441L;

	public static final int WIDTH = QuestionSelectionPanel.WIDTH;
	public static final int HEIGHT = StatePlayPanel.HEIGHT ;
	
	private JLabel title;
	private JScrollPane listScroll;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton reveal;
	private JButton strike;
	
	private FamilyFeudGame game;

	public QuestionControlPanel(FamilyFeudGame game) {
		this.game = game;
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new GridBagLayout());
		initComponents();
	}
	
	private void initComponents() {
		title = new JLabel("QUESTION CONTROL");
		title.setFont(new Font(ResourceLoader.DEFAULT_FONT_NAME, Font.BOLD, 14));
		
		tableModel = new DefaultTableModel(new Object[]{"Answer", "Points", "Revealed"}, 0);
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);

		listScroll = new JScrollPane(table);
		listScroll.setPreferredSize(new Dimension(WIDTH - 30, (int)(HEIGHT*0.65)));
		
		reveal = new JButton("Reveal answer");
		reveal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() > -1) {
					if (game.getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[] {
						StateFamilyPlay.ACTION_OPENANSWER, table.getSelectedRow()
					})) {
						loadQuestion();
					}
				}
			}
		});
		
		strike = new JButton("Strike");
		strike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[] {
						StateFamilyPlay.ACTION_STRIKE
					});
			}
		});
		
		add(title, new GBC(0, 0).setInsets(5).setSpan(2, 1));
		add(listScroll, new GBC(0, 1).setSpan(2, 1));
		add(reveal, new GBC(0, 2).setWeight(0.4, 1.0).setFill(1).setInsets(2, 6, 2, 2));
		add(strike, new GBC(1, 2).setWeight(0.6, 1.0).setFill(1).setInsets(2, 2, 2, 6));
	}
	
	/**
	 * Load a question into the list to display
	 * @param questions
	 */
	public void loadQuestion() {
		int selected = table.getSelectedRow();
		for(int i = tableModel.getRowCount(); i > 0; i--) {
			tableModel.removeRow(i - 1);
		}
		for(Answer a : game.getQuestionSet().getSelectedQuestion().getAnswers()) {
			tableModel.addRow(new Object[]{a.getAnswerString(), a.getValue(), a.isRevealed()});
		}
		if (selected > -1) table.setRowSelectionInterval(selected, selected);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		reveal.setEnabled(enabled);
		strike.setEnabled(enabled);
	}
	
}