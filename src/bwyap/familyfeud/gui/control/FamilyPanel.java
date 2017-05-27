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
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import bwyap.familyfeud.game.Family;
import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.state.StatePlay;
import bwyap.familyfeud.gui.GBC;
import bwyap.familyfeud.gui.UIManager;
import bwyap.gridgame.res.ResourceLoader;

/**
 * A panel used to select a family for an action
 * @author bwyap
 * 
 */
public class FamilyPanel extends JPanel {
	
	private static final long serialVersionUID = -5811924958007378691L;

	public static final int WIDTH = UIManager.getInstance().getDimension("familypanel.width");
	public static final int HEIGHT = UIManager.getInstance().getDimension("familypanel.height");
	
	private JLabel title;
	private JScrollPane tableScroll;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton select;
	private JButton updateScores;
	
	private int command = -1;
	
	private FamilyFeudGame game;
	
	public FamilyPanel(FamilyFeudGame game) {
		this.game = game;
		setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		initComponents();
	}
	
	public void initComponents() {
		title = new JLabel("FAMILIES");
		title.setFont(new Font(ResourceLoader.DEFAULT_FONT_NAME, Font.BOLD, 14));

		tableModel = new DefaultTableModel(new Object[]{"Family", "Points"}, 0){
			private static final long serialVersionUID = 2222748798265167701L;
	        public boolean isCellEditable(int row, int column) {
	        	return column == 1 && updateScores.isEnabled();
	        }
        };
        
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
			private static final long serialVersionUID = -5275940432999486004L;
			
			public boolean stopCellEditing() {
				// Ensure that an entered value is a positive integer
				try {
					JTextField textField = (JTextField) getComponent();
					textField.setBorder(new LineBorder(Color.BLACK));
					String editingValue = (String) getCellEditorValue();
					int value = Integer.parseInt(editingValue);
					if (value < 0) throw new Exception();
					textField.setText(value + "");
				}
				catch (Exception e) {
					JTextField textField = (JTextField) getComponent();
					textField.setBorder(new LineBorder(Color.RED));
					textField.selectAll();
					textField.requestFocusInWindow();
					
					JOptionPane.showMessageDialog(null, "Invalid score entered. Must be a positive integer.", "Invalid score!", JOptionPane.ERROR_MESSAGE);
					
					return false;
				}
				return super.stopCellEditing();
			}
			
		});
		
		tableScroll = new JScrollPane(table);
		tableScroll.setMinimumSize(new Dimension(WIDTH - 20, (int)(HEIGHT*0.65)));
		
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(WIDTH - 20 - 100 - 5);
		
		select = new JButton("Select");
		select.setToolTipText("Select the family for the next action");
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() > -1 && command > -1) {
					game.getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
							command, table.getSelectedRow()});
				}
			}
		});
		
		updateScores = new JButton("Update");
		updateScores.setToolTipText("Update the family score");
		updateScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Check if there are any families in table
				if (table.getRowCount() > 0) {
					if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure you want to modify the scores?", "Modifying family scores", JOptionPane.OK_CANCEL_OPTION)) {
						List<Family> families = game.getFamilies();
						for(int i = 0; i < families.size(); i++) {
							
							try {
								if (!(table.getValueAt(i, 1) instanceof Integer)) {
									int value = Integer.parseInt((String)table.getValueAt(i, 1));
									families.get(i).setPoints(value);
								}
							}
							catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
				else JOptionPane.showMessageDialog(null, "No families loaded.", "No families", JOptionPane.OK_OPTION);
			}
		});
		
		add(title, new GBC(0, 0).setSpan(2, 1));
		add(tableScroll, new GBC(0, 1).setSpan(2, 1));
		add(updateScores, new GBC(0, 2));
		add(select, new GBC(1, 2));
	}
	
	/**
	 * Load families into panel list
	 */
	public void loadFamilies() {
		int selected = table.getSelectedRow();
		reset();
		for(Family f : game.getFamilies()) {
			tableModel.addRow(new Object[]{f.getName(), f.getPoints()});
		}
		if (selected > -1) table.setRowSelectionInterval(selected, selected);
	}
	
	/**
	 * Set the command to execute when the select button is pressed
	 * @param command
	 */
	public void setCommand(int command) {
		this.command = command;
	}
	
	/**
	 * Reset the families for a new game
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
		table.setRowSelectionAllowed(enabled);
		if (enabled) setBorder(BorderFactory.createLineBorder(Color.BLUE, SELECTED_BORDER_WIDTH));
		else setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
	}
	
}
