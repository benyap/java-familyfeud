package bwyap.gridgame.res;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * A class responsible for loading resources 
 * @author bwyap
 *
 */
public class ResourceLoader {
	
	public static final String DEFAULT_FONT_NAME = "SansSerif";
	
	private HashMap<String, Resource> resources = new HashMap<String, Resource>();
	
	private static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();	
	private static HashMap<String, String> fontNames = new HashMap<String, String>();	

	private String[] fonts;
	
	/**
	 * Get an image from the resource loader
	 * @param id
	 * @return
	 */
	public static BufferedImage getImage(String id) {
		return images.get(id);
	}
	
	/**
	 * Get the font name if it is available.
	 * Method returns a default font if it is not available.
	 * @param id
	 * @return
	 */
	public static String getFontName(String id) {
		return fontNames.get(id);
	}
	
	/**
	 * Initialize resource loader.
	 * This method should be overridden to load custom resources.
	 */
	public void init() {
		
	}
	
	/**
	 * Add a resource to the resource loader.
	 * This should be done in the init() method before the resources are loaded.
	 * @param id
	 * @param path
	 * @param type
	 */
	protected void addResource(String id, String path, ResourceType type) {
		resources.put(id, new Resource(id, path, type));
	}
	
	/**
	 * Load resources
	 */
	public void load() {
		// load all resources
		for(String s : resources.keySet()) {
			Resource r = resources.get(s);
			switch(r.type()) {
			case PNG:
				loadImage(r.id(), r.location());
				break;
			case FONTNAME:
				loadFont(r.id());
				break;
			case FONT:
				loadFont(r.id(), r.location());
				break;
			}
		}
	}
	
	/**
	 * Load an image from within class
	 * @param id
	 * @param location
	 */
	protected void loadImage(String id, String location) {
		try {
			BufferedImage bg = ImageIO.read(getClass().getResource(location));
		    images.put(id, bg);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Check if a font exists
	 * @param fontname
	 */
	protected void loadFont(String fontname) {
		if (fonts == null) {
			GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        fonts = g.getAvailableFontFamilyNames();
		}
        for (int i = 0; i < fonts.length; i++) {
        	if(fonts[i].equals(fontname)){
            	// Font exists
                fontNames.put(fontname, fontname);
        		return;
        	}
        }
        // put default font if font does not exist
        fontNames.put(fontname, DEFAULT_FONT_NAME);
	}
	
	/**
	 * Load a new font onto the system
	 * @param fontname
	 */
	protected void loadFont(String fontname, String path) {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			InputStream is = this.getClass().getResourceAsStream(path);
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
		} 
		catch (IOException|FontFormatException e) { 
			System.err.println("An error occurred when trying to load font " + fontname);
		}
		loadFont(fontname);
	}

}
