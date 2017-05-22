package bwyap.utility.logging;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import bwyap.familyfeud.gui.control.ConsolePanel;
import bwyap.gridgame.res.ResourceLoader;

/**
 * The Console class is a singleton which provides a 
 * console style window in the form of a JTextPane.
 * @author bwyap
 *
 */
public class ConsoleLogger extends JTextPane implements LoggerInterface {

	private static final long serialVersionUID = 2781201210027434019L;

	private static ConsoleLogger INSTANCE;

	private static int SIZE = 12;
	private static String FONT_NAME = "Monaco";
	private static SimpleAttributeSet WARNING_COLOUR;
	private static SimpleAttributeSet INFO_COLOUR;
	private static SimpleAttributeSet DEFAULT_COLOUR;
	private static SimpleAttributeSet ERR_COLOUR;
	
	/**
	 * Get a console instance.
	 * If no instance currently exists, a new one is created.
	 * @return
	 */
	public static ConsoleLogger getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConsoleLogger();
		}
		return INSTANCE;
	}
	
	/**
	 * Create a new Console instance and set default colours
	 */
	private ConsoleLogger() {
		setEditable(false);
		setPreferredSize(new Dimension(ConsolePanel.WIDTH, ConsolePanel.HEIGHT));
		setFont(new Font(ResourceLoader.getFontName(FONT_NAME), Font.PLAIN, SIZE));
		setBackground(Color.BLACK);
				
		// auto scroll console pane
		DefaultCaret caret = (DefaultCaret)this.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		DEFAULT_COLOUR = new SimpleAttributeSet();
		StyleConstants.setFontFamily(DEFAULT_COLOUR, ResourceLoader.getFontName(FONT_NAME));
		StyleConstants.setForeground(DEFAULT_COLOUR, new Color(0xffffff));
		
		ERR_COLOUR = new SimpleAttributeSet();
		StyleConstants.setFontFamily(ERR_COLOUR, ResourceLoader.getFontName(FONT_NAME));
		StyleConstants.setForeground(ERR_COLOUR, new Color(0xff0000));
		
		INFO_COLOUR = new SimpleAttributeSet();
		StyleConstants.setFontFamily(INFO_COLOUR, ResourceLoader.getFontName(FONT_NAME));
		StyleConstants.setForeground(INFO_COLOUR, new Color(0x55a2ff));
		
		WARNING_COLOUR = new SimpleAttributeSet();
		StyleConstants.setFontFamily(WARNING_COLOUR, ResourceLoader.getFontName(FONT_NAME));
		StyleConstants.setForeground(WARNING_COLOUR, new Color(0xffff00));
	}
	
	/**
	 * Set the font size of the logger
	 * @param size
	 */
	public void setFontSize(int size) {
		SIZE = size;
		setFont(new Font(ResourceLoader.getFontName(FONT_NAME), Font.PLAIN, SIZE));
	}
	

	@Override
	public void printMessage(String msg) {
		appendToPane(msg + "\n", DEFAULT_COLOUR);
	}

	@Override
	public void logMessage(String msg) {
		appendToPane("[LOG] " + msg + "\n", DEFAULT_COLOUR);
	}

	@Override
	public void logInfo(String msg) {
		appendToPane("[INF] " + msg + "\n", INFO_COLOUR);
	}

	@Override
	public void logErr(String msg) {
		appendToPane("[ERR] " + msg + "\n", ERR_COLOUR);
	}

	@Override
	public void logWarning(String msg) {
		appendToPane("[WRN] " + msg + "\n", WARNING_COLOUR);
	}
	
	/**
	 * Append a coloured message to the text pane
	 * @param msg
	 * @param a
	 */
	private static void appendToPane(String msg, SimpleAttributeSet a) {		
		try {
			int len = INSTANCE.getDocument().getLength();
			INSTANCE.getDocument().insertString(len, msg, a);
		}
		catch (BadLocationException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
}
