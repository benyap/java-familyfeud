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

import bwyap.familyfeud.game.Answer;
import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.Question;
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
	private JList<Answer> list;
	private DefaultListModel<Answer> listModel;
	private JButton reveal;
	
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
		
		listModel = new DefaultListModel<Answer>();
		list = new JList<Answer>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listScroll = new JScrollPane(list);
		listScroll.setPreferredSize(new Dimension(WIDTH - 30, (int)(HEIGHT*0.65)));
		
		reveal = new JButton("Reveal answer");
		reveal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() > -1) {
					
				}
			}
		});
		
		add(title, new GBC(0, 0).setInsets(5));
		add(listScroll, new GBC(0, 1));
		add(reveal, new GBC(0, 2));
	}
	
	/**
	 * Load a question into the list to display
	 * @param questions
	 */
	public void loadQuestion(Question question) {
		listModel.clear();
		for(Answer a : question.getAnswers()) {
			listModel.addElement(a);
		}
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		reveal.setEnabled(enabled);
	}
	
}
