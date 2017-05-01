package bwyap.familyfeud.render.component;

import bwyap.familyfeud.render.RenderableInterface;

/**
 * An abstract class that represents a renderable object
 * that has a size and position.
 * @author bwyap
 *
 */
public abstract class Renderable implements RenderableInterface {

	protected int x, y;
	protected int w, h;
	
	public Renderable(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	/**
	 * Get the x position of the image
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get the y position of the image
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	/** 
	 * Get the width of the image
	 * @return
	 */
	public int getWidth() {
		return w;
	}
	
	/**
	 * Get the height of the image
	 * @return
	 */
	public int getHeight() {
		return h;
	}
	
	/**
	 * Set the rendering position of the image
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Set the size of the image
	 * @param w
	 * @param h
	 */
	public void setSize(int w, int h){
		this.w = w;
		this.h = h;
	}
	
}
