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
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import bwyap.familyfeud.game.fastmoney.state.FastMoney;
import bwyap.familyfeud.gui.GBC;
import bwyap.familyfeud.gui.UIManager;
import bwyap.familyfeud.gui.window.FastMoneyWindow;
import bwyap.familyfeud.sound.SoundManager;
import bwyap.gridgame.res.ResourceLoader;

public class FastMoneyTimerPanel extends JPanel {

	private static final long serialVersionUID = 4166153481087438206L;
	
	public static final int WIDTH = UIManager.getInstance().getProperty("fastmoneytimerpanel.width");
	public static final int HEIGHT = UIManager.getInstance().getProperty("fastmoneytimerpanel.height");
	
	private FastMoneyWindow window;
	private FastMoney fastmoney;
	
	private JLabel title;
	private JButton start;
	private JButton stop;
	private JButton reset;
	private JTextField time;
	private JButton tryAgainSound;
	
	public FastMoneyTimerPanel(FastMoney fastmoney, FastMoneyWindow window) {
		this.fastmoney = fastmoney;
		this.window = window;
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		setLayout(new GridBagLayout());
		initComponents();
	}
	
	/**
	 * Initialise components
	 */
	private void initComponents() {
		title = new JLabel("TIMER");
		title.setFont(new Font(ResourceLoader.DEFAULT_FONT_NAME, Font.BOLD, 14));

		start = new JButton("Start");
		start.setToolTipText("Start the timer");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fastmoney.setTimerRunning(true);
				stop.setEnabled(true);
				start.setEnabled(false);
				window.enableReveal(0, false);
				window.enableReveal(1, false);
			}
		});
		
		stop = new JButton("Stop");
		stop.setEnabled(false);
		stop.setToolTipText("Stop the timer");
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fastmoney.isTimerRunning()) SoundManager.getInstance().playClip("fm_ding2");
				fastmoney.setTimerRunning(false);
				stop.setEnabled(false);
				start.setEnabled(true);
				window.enableReveal(0, true);
				window.enableReveal(1, true);
			}
		});

		reset = new JButton("Reset");
		reset.setToolTipText("Reset the timer to the specified value");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fastmoney.setTimer(Integer.parseInt(time.getText()));
			}
		});
		
		time = new JTextField("20");
		time.setToolTipText("Set the value of the timer in seconds");
		time.setColumns(5);
		time.setMinimumSize(new Dimension(50, 30));
		time.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) { validateTime(); }
			@Override
			public void removeUpdate(DocumentEvent e) { validateTime(); }
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});

		tryAgainSound = new JButton("Try Again");
		tryAgainSound.setToolTipText("Play the \'Try Again\' sound");
		tryAgainSound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SoundManager.getInstance().playClip("fm_tryagain");
			}
		});
		
		add(title, new GBC(0, 0).setSpan(2, 1));
		add(start, new GBC(0, 1));
		add(stop, new GBC(1, 1));
		add(reset, new GBC(0, 2));
		add(time, new GBC(1, 2));
		add(tryAgainSound, new GBC(0, 3).setSpan(2, 1));
	}
	
	/**
	 * Validate the time value entered
	 */
	private void validateTime() {
		boolean valid = true;
		String text = time.getText();
		try {
			if (!text.equals("")) {
				if (Integer.parseInt(text) < 0)
					throw new Exception();
			}
			time.setBackground(Color.WHITE);
			reset.setEnabled(true);
		}
		catch (Exception e) { 
			time.setBackground(new Color(0xff9999));
			valid = false;
		}		
		reset.setEnabled(valid);
	}

	/**
	 * Reset the controls
	 */
	public void reset() {
		start.setEnabled(true);
		stop.setEnabled(false);
		time.setText("20");
	}
}
