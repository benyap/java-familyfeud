package bwyap.familyfeud.gui.control;

import static bwyap.familyfeud.gui.window.ControlWindow.DEFAULT_BORDER_WIDTH;
import static bwyap.familyfeud.gui.window.ControlWindow.SELECTED_BORDER_WIDTH;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
	public static final int HEIGHT = StatePlayPanel.HEIGHT;
	
	private JLabel title;
	private JScrollPane tableScroll;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton reveal;
	private JButton strike;
	
	private FamilyFeudGame game;

	public QuestionControlPanel(FamilyFeudGame game) {
		this.game = game;
		setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new GridBagLayout());
		initComponents();
	}
	
	private void initComponents() {
		title = new JLabel("QUESTION CONTROL");
		title.setFont(new Font(ResourceLoader.DEFAULT_FONT_NAME, Font.BOLD, 14));
		
		tableModel = new DefaultTableModel(new Object[]{"Answer", "Points", "Revealed"}, 0){
			private static final long serialVersionUID = 2222748798265167701L;
	        public boolean isCellEditable(int row, int column) { return false; }
        };
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);

		tableScroll = new JScrollPane(table);
		tableScroll.setPreferredSize(new Dimension(WIDTH - 30, (int)(HEIGHT*0.65)));
		
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(0).setPreferredWidth(WIDTH - 30 - 60 - 60 - 5);

		reveal = new JButton("Reveal answer");
		reveal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() > -1) {
					if (game.getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[] {
						StateFamilyPlay.ACTION_OPENANSWER, table.getSelectedRow()
					})) {
						loadQuestion();
						table.clearSelection();
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
		add(tableScroll, new GBC(0, 1).setSpan(2, 1));
		add(reveal, new GBC(0, 2).setWeight(0.4, 1.0).setFill(1).setInsets(2, 6, 2, 2));
		add(strike, new GBC(1, 2).setWeight(0.6, 1.0).setFill(1).setInsets(2, 2, 2, 6));
	}
	
	/**
	 * Load a question into the list to display
	 * @param questions
	 */
	public void loadQuestion() {
		int selected = table.getSelectedRow();
		reset();
		List<Answer> answers = game.getQuestionSet().getSelectedQuestion().getAnswers();
		for(int i = 0; i < answers.size(); i++) {
			tableModel.addRow(new Object[]{answers.get(i).getAnswerString(), answers.get(i).getValue(), answers.get(i).isRevealed()});
		}
		if (selected > -1) table.setRowSelectionInterval(selected, selected);
	}

	/**
	 * Reset the question list
	 */
	public void reset() {
		for(int i = tableModel.getRowCount(); i > 0; i--) {
			tableModel.removeRow(i - 1);
		}
	}
	
	/**
	 * Disable the strike feature
	 */
	public void disableStrike() {
		strike.setEnabled(false);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		reveal.setEnabled(enabled);
		strike.setEnabled(enabled);
		if (enabled) setBorder(BorderFactory.createLineBorder(Color.BLUE, SELECTED_BORDER_WIDTH));
		else setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
	}
	
}
