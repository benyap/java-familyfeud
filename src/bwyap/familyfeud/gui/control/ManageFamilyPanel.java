package bwyap.familyfeud.gui.control;

import static bwyap.familyfeud.gui.window.ControlWindow.DEFAULT_BORDER_WIDTH;
import static bwyap.familyfeud.gui.window.ControlWindow.SELECTED_BORDER_WIDTH;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.state.StateAddFamily;
import bwyap.familyfeud.gui.GBC;
import bwyap.familyfeud.gui.UIManager;

/**
 * Control panel component responsible for adding families to the game
 * @author bwyap
 *
 */
public class ManageFamilyPanel extends JPanel {

	private static final long serialVersionUID = 3506047425993585668L;
	
	public static final int WIDTH = UIManager.getInstance().getDimension("managefamilypanel.width");
	public static final int HEIGHT = UIManager.getInstance().getDimension("managefamilypanel.height");
	
	private FamilyFeudGame game;
	
	private JLabel title;
	private JTextField field;
	private JButton addFamily;
	private JButton removeFamily;
	
	public ManageFamilyPanel(FamilyFeudGame game) {
		this.game = game;
		setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		initComponents();
	}
	
	private void initComponents() {
		title = new JLabel("Add new family");
		field = new JTextField(10);
		
		addFamily = new JButton("Add");
		addFamily.setToolTipText("Add a unique family name. Cannot be empty.");
		
		removeFamily = new JButton("Remove");
		removeFamily.setToolTipText("Removes the last added family");
		
		addFamily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (field.getText().trim().length() > 0) {
					if (game.getState().executeAction(StateAddFamily.ACTION_ADDFAMILY, new Object[]{
						field.getText().trim().toUpperCase()
					})) {
						field.setText("");						
					}
				}
			}
		});
		
		removeFamily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.getState().executeAction(StateAddFamily.ACTION_REMOVEFAMILY, null);
			}
		});
		
		add(title, new GBC(0, 0).setSpan(2, 1));
		add(field, new GBC(0, 1).setSpan(2, 1).setFill(1));
		add(addFamily, new GBC(0, 2));
		add(removeFamily, new GBC(1, 2));
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		field.setEnabled(enabled);
		addFamily.setEnabled(enabled);
		removeFamily.setEnabled(enabled);
		
		if (enabled) setBorder(BorderFactory.createLineBorder(Color.RED, SELECTED_BORDER_WIDTH));
		else setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
	}
	
}
