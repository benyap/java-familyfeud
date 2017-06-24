package bwyap.familyfeud.gui.control;

import static bwyap.familyfeud.gui.window.ControlWindow.DEFAULT_BORDER_WIDTH;

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

import bwyap.familyfeud.gui.GBC;
import bwyap.familyfeud.gui.UIManager;
import bwyap.familyfeud.sound.SoundManager;
import bwyap.gridgame.res.ResourceLoader;

/**
 * A panel to control sound
 * @author bwyap
 *
 */
public class SoundControlPanel extends JPanel {
	
	private static final long serialVersionUID = 54451995030112354L;

	public static final int WIDTH = UIManager.getInstance().getProperty("soundpanel.width");
	public static final int HEIGHT = UIManager.getInstance().getProperty("soundpanel.height");
	
	private JLabel title;
	private JButton mute;
	
	public SoundControlPanel() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		initComponents();
	}
	
	private void initComponents() {
		title = new JLabel("SOUND CONTROL");
		title.setFont(new Font(ResourceLoader.DEFAULT_FONT_NAME, Font.BOLD, 14));
		
		mute = new JButton("Mute");
		mute.setToolTipText("Mute/Unmute the program");
		mute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SoundManager.getInstance().isMuted()) {
					SoundManager.getInstance().setMuted(false);
					mute.setText("Mute");
				}
				else {
					SoundManager.getInstance().setMuted(true);
					mute.setText("Unmute");
				}
			}
		});
		
		add(title, new GBC(0, 0));
		add(mute, new GBC(0, 1).setFill(1));
	}

}
