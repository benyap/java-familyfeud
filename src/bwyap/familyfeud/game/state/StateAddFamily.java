package bwyap.familyfeud.game.state;

import bwyap.familyfeud.game.FamilyCollection;
import bwyap.familyfeud.game.InvalidDataException;
import bwyap.utility.logging.Logger;

/**
 * This state is used to add new families to the game before the game starts.
 * @author bwyap
 *
 */
public class StateAddFamily extends FFState {
	
	// ACTIONS
	public static final int ACTION_ADDFAMILY = 0x0;
	public static final int ACTION_REMOVEFAMILY = 0x1;
	
	private FamilyCollection families;
	
	protected StateAddFamily(FamilyCollection families) {
		super(FFStateType.ADD_FAMILY);
		this.families = families;
	}

	@Override
	public void initState(Object data) { }

	@Override
	public void cleanupState() { }
	
	@Override
	public boolean executeAction(int action, Object[] data) {
		switch(action) {
		case ACTION_ADDFAMILY:
			// Add a family to the game
			if (data[0] instanceof String) {
				return addFamily((String) data[0]);
			}
			else throw new InvalidDataException("Expecting a {String} when using action ACTION_ADDFAMILY");
		case ACTION_REMOVEFAMILY:
			// Remove a family from the game
			return removeFamily();
		}
		return false;
	}
	
	/**
	 * Add a new family to the game
	 * @param familyName
	 */
	private boolean addFamily(String familyName) {
		if (families.getFamily(familyName) != null) {
			Logger.err("Family already exists.");
			return false;
		}
		else {
			families.addFamily(familyName);
			return true;
		}
	}
	
	/**
	 * Remove the previously added family to the game
	 * @param familyName
	 */
	private boolean removeFamily() {
		if (families.size() == 0) {
			return false;
		}
		else {
			families.removeFamily(families.size() - 1);
			return true;
		}
	}

}
