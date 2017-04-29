package bwyap.utility.resource;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Provides a static method for loading a JSON file from the disk.
 * @author bwyap
 *
 */
public class JSONLoader {	
	
	/**
	 * Loads a JSON object from a file on disk.
	 * Returns null if the file is not found.
	 * @param file <tt>File</tt> which describes the location of the physical file
	 * @return
	 */
	public static JSONObject loadJSON(File file) {
		JSONObject jsonObject = null;
		
		if (!file.exists()) {
			System.err.println("File <" + file.getName() + "> not found.");
			return null;
		}
		
		JSONParser parser = new JSONParser();
		try {
			Object o = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) o;
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
		catch (ParseException e) {
			System.err.println("An error occurred while trying to parse JSON file <" + file.getName() + ">.");
		}
		
		return jsonObject;
	}
	
}
