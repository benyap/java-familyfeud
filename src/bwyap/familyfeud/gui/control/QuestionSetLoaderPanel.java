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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONObject;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.state.StateLoadQuestions;
import bwyap.familyfeud.gui.GBC;
import bwyap.familyfeud.gui.window.ControlWindow;
import bwyap.familyfeud.res.JSONQuestionSet;
import bwyap.gridgame.res.ResourceLoader;
import bwyap.utility.logging.Logger;
import bwyap.utility.resource.JSONLoader;

/**
 * Provides a way to load a JSON file as a question set
 * @author bwyap
 *
 */
public class QuestionSetLoaderPanel extends JPanel {

	private static final long serialVersionUID = 689335015288245441L;

	public static final int WIDTH = ControlWindow.WIDTH - StatePanel.WIDTH - WindowControlPanel.WIDTH - 10;
	public static final int HEIGHT = StatePanel.HEIGHT - QuestionSelectionPanel.HEIGHT;
	
	private FamilyFeudGame game;
	private QuestionSelectionPanel panel;
	
	private JFileChooser fc;
	private JLabel title;
	private JButton load;	
	
	public QuestionSetLoaderPanel(FamilyFeudGame game, QuestionSelectionPanel panel) {
		this.game = game;
		this.panel = panel;
		setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new GridBagLayout());
		initComponents();
	}
	
	private void initComponents() {
		title = new JLabel("Question Set: No file loaded...");
		title.setFont(new Font(ResourceLoader.DEFAULT_FONT_NAME, Font.PLAIN, 12));
		
		load = new JButton("Load file");
		load.addActionListener(new ChooseJSON());
		
		add(title, new GBC(0, 0).setInsets(5, 0, 0, 0));
		add(load, new GBC(0, 1));
		
		fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON file", "json");
		fc.setFileFilter(filter);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		load.setEnabled(enabled);
		if (enabled) setBorder(BorderFactory.createLineBorder(Color.RED, SELECTED_BORDER_WIDTH));
		else setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
	}
	
	/**
	 * Reset the GUI components
	 */
	public void reset() {
		title.setText("Question Set: No file loaded...");
	}
	
	/**
	 * An action that opens a file choose dialog and loads a JSON file.
	 * The JSON file is parsed if it is a valid quesiton set.
	 * @author bwyap
	 *
	 */
	private class ChooseJSON implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int returnVal = fc.showOpenDialog(QuestionSetLoaderPanel.this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// create and parse selected JSON file
				JSONObject o = JSONLoader.loadJSON(fc.getSelectedFile());
				JSONQuestionSet q = new JSONQuestionSet(o);
				
				// validate questions
				if (q.isValid()) {
					title.setText("Quesion Set: LOADED [" + fc.getSelectedFile().getName() + "]");
					Logger.info("Successfully loaded question set <" + fc.getSelectedFile().getName() + ">");
					game.getState().executeAction(StateLoadQuestions.ACTION_LOADQUESTIONSET, new Object[]{q});
					panel.loadQuestions(game.getQuestionSet());
				}
				else {
					Logger.err("Error occurred while trying to load question set from <" + fc.getSelectedFile().getName() + ">");
				}
			}
		}
	}
	
}