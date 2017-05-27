package bwyap.familyfeud.game;

import bwyap.utility.logging.Logger;

/**
 * Represents a family in the game.
 * @author bwyap
 *
 */
public class Family {
	
	private String familyName;
	private int points;
	// private List<FamilyMember> members;
	
	/**
	 * Create a family with the given name
	 * @param familyName
	 */
	public Family(String familyName) {
		this.familyName = familyName;
		this.points = 0;
	}

	/**
	 * Get the name of the family
	 * @return
	 */
	public String getName() {
		return familyName;
	}
	
	/**
	 * Get the number of points this family current has.
	 * @return
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Add points to the family's score
	 * @param amount
	 */
	public void addPoints(int amount) {
		points += amount;
	}
	
	/**
	 * Set a family's points.
	 * This should only be used as a hard override if necessary.
	 * @param amount
	 */
	public void setPoints(int points) {
		Logger.warning(familyName + "'s score modified from " + this.points + " to " + points);
		this.points = points;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
