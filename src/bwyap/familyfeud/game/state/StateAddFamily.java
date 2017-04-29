package bwyap.familyfeud.game.state;

import bwyap.familyfeud.game.FamilyCollection;
import bwyap.familyfeud.game.InvalidDataException;

/**
 * This state is used to add new families to the game before the game starts.
 * @author bwyap
 *
 */
public class StateAddFamily extends FFState {
	
	// ACTIONS
	public static final int ACTION_ADDFAMILY = 0x0;
	
	private FamilyCollection families;
	
	protected StateAddFamily(FamilyCollection families) {
		super(FFStateType.ADD_FAMILY);
		this.families = families;
	}

	@Override
	public void initState() { }

	@Override
	public void cleanupState() {
		returnObject = families;
	}
	
	@Override
	public boolean executeAction(int action, Object[] data) {
		switch(action) {
		case ACTION_ADDFAMILY:
			// Add a family to the game
			if (data[0] instanceof String) {
				addFamily((String) data[0]);
			}
			else throw new InvalidDataException("Expecting a {String} when using action ACTION_ADDFAMILY");
			break;
		}
		return false;
	}
	
	/**
	 * Add a new family to the game
	 * @param familyName
	 */
	private void addFamily(String familyName) {
		families.addFamily(familyName);
	}

}
