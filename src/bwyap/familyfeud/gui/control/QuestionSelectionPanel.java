package bwyap.familyfeud.gui.control;

import static bwyap.familyfeud.gui.window.ControlWindow.DEFAULT_BORDER_WIDTH;
import static bwyap.familyfeud.gui.window.ControlWindow.SELECTED_BORDER_WIDTH;

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

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.Question;
import bwyap.familyfeud.game.QuestionSet;
import bwyap.familyfeud.game.play.state.StateSelectQuestion;
import bwyap.familyfeud.game.state.StatePlay;
import bwyap.familyfeud.gui.GBC;
import bwyap.familyfeud.gui.window.ControlWindow;
import bwyap.gridgame.res.ResourceLoader;

/**
 * A panel to select the question to display on the game screen during play
 * @author bwyap
 *
 */
public class QuestionSelectionPanel extends JPanel {

	private static final long serialVersionUID = 689335015288245441L;

	public static final int WIDTH = ControlWindow.WIDTH - StatePanel.WIDTH - WindowControlPanel.WIDTH - 10;
	public static final int HEIGHT = (int)(StatePanel.HEIGHT*0.8);
	
	private JLabel title;
	private JScrollPane tableScroll;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton select;
	
	private FamilyFeudGame game;

	public QuestionSelectionPanel(FamilyFeudGame game) {
		this.game = game;
		setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new GridBagLayout());
		initComponents();
	}
	
	private void initComponents() {
		title = new JLabel("QUESTION SELECTOR");
		title.setFont(new Font(ResourceLoader.DEFAULT_FONT_NAME, Font.BOLD, 14));
		
		tableModel = new DefaultTableModel(new Object[]{"Answers", "Question"}, 0) {
			private static final long serialVersionUID = 2222748798265167701L;
	        public boolean isCellEditable(int row, int column) { return false; }
        };

		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tableScroll = new JScrollPane(table);
		tableScroll.setMinimumSize(new Dimension(WIDTH - 20, (int)(HEIGHT*0.65)));
		
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(WIDTH - 20 - 55 - 5);
		
		select = new JButton("Select");
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() > -1) {
					game.getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
							StateSelectQuestion.ACTION_SELECTQUESTION, table.getSelectedRow()});
				}
			}
		});
		
		add(title, new GBC(0, 0).setInsets(5));
		add(tableScroll, new GBC(0, 1));
		add(select, new GBC(0, 2));
	}
	
	/**
	 * Load a question set into the list to display
	 * @param questions
	 */
	public void loadQuestions(QuestionSet questions) {
		reset();
		for(Question q : questions.getQuestions()) {
			tableModel.addRow(new Object[]{q.getAnswers().size(), q.getQuestionString()});
		}
	}
	
	/**
	 * Clear the questions loaded 
	 */
	public void reset() {
		for(int i = tableModel.getRowCount(); i > 0; i--) {
			tableModel.removeRow(i - 1);
		}
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		select.setEnabled(enabled);
		if (enabled) setBorder(BorderFactory.createLineBorder(Color.BLUE, SELECTED_BORDER_WIDTH));
		else setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
	}

}
