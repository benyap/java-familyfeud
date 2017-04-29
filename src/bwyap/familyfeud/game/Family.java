package bwyap.familyfeud.game;

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
	
	@Override
	public String toString() {
		return getName();
	}
	
}
