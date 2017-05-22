package bwyap.familyfeud.gui;

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
		
		map.put("controlwindow.width", 800);
		map.put("controlwindow.height", 640);
		  
		map.put("windowcontrolpanel.width", 180);
		map.put("windowcontrolpanel.height", 120);
		
		map.put("statepanel.width", 180);
		map.put("statepanel.height", 220);
		  
		map.put("consolepanel.width", map.get("controlwindow.width") - 10);
		map.put("consolepanel.height", 200);
		  
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
		
		map.put("gamewindow.width", 1024);
		map.put("gamewindow.height", 768);
		
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
