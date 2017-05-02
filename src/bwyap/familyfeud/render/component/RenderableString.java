package bwyap.familyfeud.render.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import bwyap.familyfeud.render.RenderingPanel;
import bwyap.gridgame.res.ResourceLoader;

/**
 * A container for a string that can be rendered to a RenderPanel.
 * @author bwyap
 *
 */
public class RenderableString extends Renderable {

	private String text; 
	private Color color;
	private int text_width = -1;
	
	private String fontname;
	private int style;
	private boolean centered = true;
	private int size;
	
	
	/**
	 * Create a new renderable string at the specified location.
	 * @param text the string to render
	 * @param x the x position
	 * @param y the y position
	 * @param fontname the font name
	 * @param style the font style
	 * @param size the font size
	 * @param color the font color
	 * @param centered whether the font is centered on the screen (horizontally)
	 */
	public RenderableString(String text, int x, int y, String fontname, int style, int size, Color color, boolean centered) {
		super(x, y, 0, 0);
		this.text = text;
		this.fontname = fontname;
		this.style = style;
		this.size = size;
		this.color = color;
		this.centered = centered;
	}
	
	/**
	 * Create a new renderable string at the specified location.
	 * The string is centered horizontally by default and has a black color.
	 * @param text the string to render
	 * @param y the y position
	 * @param fontname the font name
	 * @param size the font size
	 */
	public RenderableString(String text, int y, String fontname, int size) {
		this(text, 0, y, fontname, Font.PLAIN, size, Color.BLACK, true);
	}
	
	/**
	 * Create a new renderable string at the specified location.
	 * @param text the string to render
	 * @param x the x position
	 * @param y the y position
	 * @param fontname the font name
	 * @param size the font size
	 */
	public RenderableString(String text, int x, int y, String fontname, int size) {
		this(text, x, y, fontname, Font.PLAIN, size, Color.BLACK, false);
	}
	
	/**
	 * Check if the font is rendered in the center of the screen (horizontally)
	 * @return
	 */
	public boolean isCentered() {
		return centered;
	}
	
	/**
	 * Set whether the font is to be rendered in the center of the screen (horizontally)
	 * @param centered
	 */
	public void setCentered(boolean centered) {
		this.centered = centered;
	}
	
	/**
	 * Set the font size
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Get the font size
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Set the font style
	 * @param style
	 */
	public void setStyle(int style) {
		this.style = style;
	}
	
	/**
	 * Get the font style
	 * @return
	 */
	public int getStyle() {
		return style;
	}
	
	/**
	 * Set the name of the font used to render the string
	 * @param fontname
	 */
	public void setFontname(String fontname) {
		this.fontname = fontname;
	}
	
	/**
	 * Get the name of the font used to render the string
	 * @return
	 */
	public String getFontname() {
		return fontname;
	}
	
	/**
	 * Set the color of the string
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Get the colur of the string
	 * @return
	 */
	public Color getColour() {
		return color;
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		g.setFont(new Font(ResourceLoader.getFontName(fontname), style, size));
		g.setColor(color);
		if (centered) {
			if (text_width == -1) {
				text_width = g.getFontMetrics().stringWidth(text);
			}
			g.drawString(text, (panel.getWidth() - text_width)/2, y);
		}
		else g.drawString(text, x, y);
	}

}
