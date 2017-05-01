package bwyap.familyfeud.gui.control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

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
	public static final int HEIGHT = (int)(WindowControlPanel.HEIGHT*0.75);
	
	private JLabel title;
	private JScrollPane listScroll;
	private JList<Question> list;
	private DefaultListModel<Question> listModel;
	private JButton select;
	
	private FamilyFeudGame game;

	public QuestionSelectionPanel(FamilyFeudGame game) {
		this.game = game;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new GridBagLayout());
		initComponents();
	}
	
	private void initComponents() {
		title = new JLabel("QUESTION SELECTOR");
		title.setFont(new Font(ResourceLoader.DEFAULT_FONT_NAME, Font.BOLD, 14));
		
		listModel = new DefaultListModel<Question>();
		list = new JList<Question>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listScroll = new JScrollPane(list);
		listScroll.setMinimumSize(new Dimension(WIDTH - 20, (int)(HEIGHT*0.65)));
		
		select = new JButton("Select");
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() > -1) {
					game.getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
							StateSelectQuestion.ACTION_SELECTQUESTION, list.getSelectedIndex()});
				}
			}
		});
		
		add(title, new GBC(0, 0).setInsets(5));
		add(listScroll, new GBC(0, 1));
		add(select, new GBC(0, 2));
	}
	
	/**
	 * Load a question set into the list to display
	 * @param questions
	 */
	public void loadQuestions(QuestionSet questions) {
		listModel.clear();
		for(Question q : questions.getQuestions()) {
			listModel.addElement(q);
		}
	}
	
	/**
	 * Clear the questions loaded 
	 */
	public void reset() {
		listModel.clear();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		select.setEnabled(enabled);
	}

}
