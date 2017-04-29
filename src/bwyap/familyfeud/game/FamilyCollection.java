package bwyap.familyfeud.game;

import java.util.ArrayList;
import java.util.List;

import bwyap.familyfeud.test.FamilyFeudTestDriver;
import bwyap.utility.logging.Logger;

/**
 * Manages a list of families for Family Feud.
 * @author bwyap
 *
 */
public class FamilyCollection {
	
	private List<Family> families;
	
	/**
	 * Create a new  empty Family collection.
	 */
	public FamilyCollection() {
		families = new ArrayList<Family>();
	}
	
	/**
	 * Add a new family with the given name to the collection of families.
	 * Avoid adding families with the same name.
	 * @param familyName
	 */
	public void addFamily(String familyName) {
		families.add(new Family(familyName));
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) Logger.log(" + Added family: " + familyName);
	}
	
	/**
	 * Get the family with the specified name. 
	 * Returns {@code null} if the family does not exist.
	 * @param familyName
	 * @return
	 */
	public Family getFamily(String familyName) {
		for(Family family : families) {
			if (family.getName().equals(familyName)) {
				return family;
			}
		}
		return null;
	}

	/**
	 * Get the list of families in this collection
	 * @return
	 */
	public List<Family> getFamilies() {
		return families;
	}
}
