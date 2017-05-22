package bwyap.familyfeud.gui.window;

import java.util.HashMap;
import java.util.Map;

import bwyap.familyfeud.OSValidator;

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
	
	private static UIManager manager;
	
	private Map<SupportedOS, Map<String, Integer>> dimensions;
	
	private Map<String, Integer> currentOSDimensions;
	
	/**
	 * Get the UI Manager instance.
	 * @return
	 */
	public static UIManager getInstance() {
		if (manager == null) {
			manager = new UIManager();
		}
		return manager;
	}
	
	private UIManager() {
		dimensions = new HashMap<SupportedOS, Map<String, Integer>>();
		loadMacOSDimensions();
		loadWindowsDimensions();
	}
	
	private void loadMacOSDimensions() {
		// TODO make this more dynamic
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		
		
		dimensions.put(SupportedOS.MACOS, map);
	}
	
	private void loadWindowsDimensions() {
		// TODO make this more dynamic
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		
		
		dimensions.put(SupportedOS.WINDOWS, map);
	}
	
	/**
	 * Get the dimension for the specified property for the current OS.
	 * @param propertyname
	 * @return
	 */
	public int getDimension(String propertyname) {
		if (currentOSDimensions == null) {
			if (OSValidator.isMac()) {
				currentOSDimensions = dimensions.get(SupportedOS.MACOS);
			}
			else if (OSValidator.isWindows()) {
				currentOSDimensions = dimensions.get(SupportedOS.WINDOWS);
			}
		}
		
		return currentOSDimensions.get(propertyname);
	}
	
}
