package bwyap.familyfeud.gui.control;

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

/**
 * Control panel component responsible for adding families to the game
 * @author bwyap
 *
 */
public class AddFamilyPanel extends JPanel {

	private static final long serialVersionUID = 3506047425993585668L;
	
	public static final int WIDTH = StatePanel.WIDTH;
	public static final int HEIGHT = 120;
	
	private FamilyFeudGame game;
	
	private JLabel title;
	private JTextField field;
	private JButton addFamily;
	private JButton removeFamily;
	
	public AddFamilyPanel(FamilyFeudGame game) {
		this.game = game;
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		initComponents();
	}
	
	private void initComponents() {
		title = new JLabel("Add new family");
		field = new JTextField(10);
		addFamily = new JButton("Add");
		removeFamily = new JButton("Remove");
		
		addFamily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (field.getText().length() > 0) {
					if (game.getState().executeAction(StateAddFamily.ACTION_ADDFAMILY, new Object[]{
						field.getText().toUpperCase()
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
		
		add(title, new GBC(0, 0));
		add(field, new GBC(0, 1).setFill(1));
		add(addFamily, new GBC(0, 2).setFill(1));
		add(removeFamily, new GBC(0, 3).setFill(1));
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		field.setEnabled(enabled);
		addFamily.setEnabled(enabled);
		removeFamily.setEnabled(enabled);
		
		if (enabled) setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		else setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	}
	
}
