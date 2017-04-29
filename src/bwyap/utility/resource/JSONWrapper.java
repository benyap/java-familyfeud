package bwyap.utility.resource;

import org.json.simple.JSONObject;

/**
 * A wrapper for a JSONObject.
 * Provides methods to extract specific data types from a JSONObject.
 * @author bwyap
 *
 */
public class JSONWrapper {

	protected JSONObject object;
	
	
	public JSONWrapper(JSONObject object) {
		this.object = object;
	}
	
	
	/**
	 * A helper function for extracting a boolean value from a JSON object
	 * @param o
	 * @param key
	 * @return
	 */
	protected static boolean getBoolean(JSONObject o, String key) {
		return Boolean.parseBoolean(o.get(key).toString());
	}

	
	/**
	 * A helper function for extracting an integer value from a JSON object
	 * @param o
	 * @param key
	 * @return
	 */
	protected static int getInteger(JSONObject o, String key) {
		return Integer.parseInt(o.get(key).toString());
	}
	
	
	/**
	 * A helper function for extracting a String value from a JSON object
	 * @param o
	 * @param key
	 * @return
	 */
	protected static String getString(JSONObject o, String key) {
		return o.get(key).toString();
	}
	
	
	/**
	 * Validates the contents of the loaded JSON object.
	 * <p>
	 * This method should be overridden so that the loaded object can be checked for validity.
	 * @return
	 */
	public boolean isValid() {
		return true;
	}
}
