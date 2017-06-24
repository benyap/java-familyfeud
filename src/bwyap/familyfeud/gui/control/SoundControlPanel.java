package bwyap.familyfeud.gui.control;

import static bwyap.familyfeud.gui.window.ControlWindow.DEFAULT_BORDER_WIDTH;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import bwyap.familyfeud.gui.GBC;
import bwyap.familyfeud.gui.UIManager;
import bwyap.familyfeud.sound.SoundManager;
import bwyap.familyfeud.sound.SoundManager.SoundRegion;
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
	private JLabel playSoundLabel;
	private JScrollPane soundScroll;
	private JList<String> soundList;
	private DefaultListModel<String> soundListModel;
	private JButton playSound;
	private JButton stopSound;
	private JLabel regionLabel;
	private JComboBox<SoundRegion> selectRegion;
	
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
			@Override
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
		
		playSoundLabel = new JLabel("Play a sound: ");
		
		soundListModel = new DefaultListModel<String>();
		loadSounds();
		
		soundList = new JList<String>(soundListModel);
		soundList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		soundScroll = new JScrollPane(soundList);
		soundScroll.setMinimumSize(new Dimension(WIDTH - 10, HEIGHT / 3));

		playSound = new JButton("Play");
		playSound.setToolTipText("Play the selected sound if it exists");
		playSound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (soundList.getSelectedValue() != null) {
					SoundManager.getInstance().playClip(soundList.getSelectedValue());
				}
			}
		});
		
		stopSound = new JButton("Stop");
		stopSound.setToolTipText("Stop the selected sound if it is playing");
		stopSound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (soundList.getSelectedValue() != null) {
					SoundManager.getInstance().stopClip(soundList.getSelectedValue());
				}
			}
		});
		
		regionLabel = new JLabel("Region: ");
		
		selectRegion = new JComboBox<SoundRegion>();
		for (SoundRegion region : SoundRegion.values()) {
			selectRegion.addItem(region);
		}
		selectRegion.setEditable(false);
		selectRegion.setSelectedItem(SoundManager.getInstance().getCurrentRegion());
		selectRegion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SoundManager.getInstance().setSoundRegion((SoundRegion) selectRegion.getSelectedItem());
				loadSounds();
			}
		});
		
		
		add(title, new GBC(0, 0).setSpan(2, 1));
		add(mute, new GBC(0, 1).setFill(1).setSpan(2, 1));
		add(playSoundLabel, new GBC(0, 2).setSpan(2, 1));
		add(soundScroll, new GBC(0, 3).setSpan(2, 1).setFill(1));
		add(playSound, new GBC(0, 4).setSpan(1, 1));
		add(stopSound, new GBC(1, 4).setSpan(1, 1));
		add(regionLabel, new GBC(0, 5).setSpan(1, 1));
		add(selectRegion, new GBC(1, 5).setSpan(1, 1));
	}
	
	
	/**
	 * Load sound clips into the sound selection list
	 */
	private void loadSounds() {
		soundListModel.clear();
		SoundManager manager = SoundManager.getInstance();
		
		// Add sounds to default list model
		for(String sound : manager.getClips(manager.getCurrentRegion())) {
			soundListModel.addElement(sound);
		}
	}

}
