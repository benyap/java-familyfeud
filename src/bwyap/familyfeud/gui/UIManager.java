package bwyap.familyfeud.gui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.UIManager.LookAndFeelInfo;

import bwyap.familyfeud.OSValidator;
import bwyap.utility.logging.ConsoleLogger;

/**
 * This class is responsible for handling UI settings to allow macOS and win10 compatibility.
 * This class is implemented as a singleton.
 * @author bwyap
 *
 */
public class UIManager {
	
	public enum SupportedOS {
		WINDOWS,
		MACOS;
	}
	
	private static UIManager INSTANCE;
	private static boolean widescreen = false;
	
	private Map<String, Integer> map;
	
	/**
	 * Get the UI Manager instance.
	 * @return
	 */
	public static UIManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UIManager();
		}
		return INSTANCE;
	}
	
	private UIManager() {
		map = new HashMap<String, Integer>();
		
		// Load dimensions according to detected UI
		if (OSValidator.isMac()) loadMacOSUI();
		else if (OSValidator.isWindows()) loadWindowsUI();
	}
	
	/**
	 * Set whether wide screen dimensions should be used.
	 * This must be called before the first {@code getInstance} call.
	 * Default is false.
	 * @param widescreen
	 */
	public static void setWidescreen(boolean widescreen) {
		UIManager.widescreen = widescreen;
	}
	
	/**
	 * Get whether wide screen dimensions should be used.
	 * Default is false, unless otherwise set by the user.
	 * @return
	 */
	public static boolean isWidescreen() {
		return UIManager.widescreen;
	}
	
	/**
	 * Load macOS UI settings
	 */
	private void loadMacOSUI() {
		loadMacOSDimensions();
	}
	
	/**
	 * Load Windows UI settings
	 */
	private void loadWindowsUI() {
		loadWindowsDimensions();
		// Set the look and feel
		try {
			for (LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				//System.out.println(info.getName());
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
		        	break;
				}
			}
		} 
		catch (Exception e) { }
	}
	
	/**
	 * Load macOS dimensions
	 */
	private void loadMacOSDimensions() {
		// TODO make this more dynamic
		map.put("controlwindow.width", 800);
		map.put("controlwindow.height", 640);
		  
		map.put("windowcontrolpanel.width", 180);
		map.put("windowcontrolpanel.height", 120);
		
		map.put("statepanel.width", 180);
		map.put("statepanel.height", 220);
		  
		map.put("consolepanel.width", map.get("controlwindow.width") - 10 - 180);
		map.put("consolepanel.height", 200);
		
		map.put("soundpanel.width", 180);
		map.put("soundpanel.height", map.get("consolepanel.height"));
		
		map.put("stateplaypanel.width", map.get("statepanel.width"));
		map.put("stateplaypanel.height", map.get("controlwindow.height") - map.get("consolepanel.height") - map.get("statepanel.height") - 25);
		  
		map.put("familypanel.width", map.get("windowcontrolpanel.width"));
		map.put("familypanel.height", map.get("stateplaypanel.height"));
		
		map.put("managefamilypanel.width", map.get("statepanel.width"));
		map.put("managefamilypanel.height", map.get("statepanel.height") - map.get("windowcontrolpanel.height"));
		  
		map.put("questionselectionpanel.width", map.get("controlwindow.width") - map.get("statepanel.width") - map.get("windowcontrolpanel.width") - 10);
		map.put("questionselectionpanel.height", (int)(0.8 * map.get("statepanel.height")));
		  
		map.put("questionsetloaderpanel.width", map.get("controlwindow.width") - map.get("statepanel.width") - map.get("windowcontrolpanel.width") - 10);
		map.put("questionsetloaderpanel.height", map.get("statepanel.height") - map.get("questionselectionpanel.height"));

		map.put("questioncontrolpanel.width", map.get("questionselectionpanel.width"));
		map.put("questioncontrolpanel.height", map.get("stateplaypanel.height"));
		
		if (widescreen) {
			map.put("gamewindow.width", 1280);
			map.put("gamewindow.height", 720);
		}
		else {
			map.put("gamewindow.width", 1024);
			map.put("gamewindow.height", 768);
		}
		
		map.put("questionselectionpanel.col1.width", 50);
		map.put("questionselectionpanel.col2.width", 40);
		map.put("questionselectionpanel.col3.width", 400);
		
		map.put("questioncontrolpanel.col2.width", 60);
		map.put("questioncontrolpanel.col1.width", 60);
		map.put("questioncontrolpanel.col0.width", map.get("questioncontrolpanel.width") - 30 - map.get("questioncontrolpanel.col1.width") - map.get("questioncontrolpanel.col2.width")  - 5);
		
		ConsoleLogger.getInstance().setFontSize(12);	
	}
	
	/**
	 * Load Windows dimensions
	 */
	private void loadWindowsDimensions() {
		loadMacOSDimensions();
		
		// Windows10 Fix
		map.put("windowcontrolpanel.height", 130);
		map.put("statepanel.height", 230);
		map.put("questionsetloaderpanel.height", map.get("statepanel.height") - map.get("questionselectionpanel.height"));
				
		map.put("controlwindow.height", 680);
		
		map.put("stateplaypanel.height", map.get("stateplaypanel.height") + 10);
		map.put("questioncontrolpanel.height", map.get("questioncontrolpanel.height") + 10);
		map.put("familypanel.height", map.get("familypanel.height") + 10);

		map.put("questionselectionpanel.col1.width", 75);
		map.put("questionselectionpanel.col2.width", 50);
		map.put("questionselectionpanel.col3.width", 400);
		
		map.put("questioncontrolpanel.col2.width", 80);
		map.put("questioncontrolpanel.col1.width", 60);
		map.put("questioncontrolpanel.col0.width", map.get("questioncontrolpanel.width") - 30 - map.get("questioncontrolpanel.col1.width") - map.get("questioncontrolpanel.col2.width")  - 5);
				
		ConsoleLogger.getInstance().setFontSize(14);
	}
	
	/**
	 * Get the value for the specified property for the current OS.
	 * @param propertyname
	 * @return
	 */
	public int getProperty(String propertyname) {
		return map.get(propertyname);
	}
	
}
