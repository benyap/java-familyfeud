package bwyap.familyfeud.res;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bwyap.familyfeud.game.Question;
import bwyap.utility.resource.JSONWrapper;

/**
 * A JSON wrapper object for a question set
 * @author bwyap
 *
 */
public class JSONQuestionSet extends JSONWrapper {

	public JSONQuestionSet(JSONObject object) {
		super(object);
	}
	
	/**
	 * Get the list of questions from the JSON object
	 * @return
	 */
	public List<Question> getQuestions() {
		List<Question> list = new ArrayList<Question>();
		
		JSONArray questions = (JSONArray) object.get("questions");
		
		// Create questions
		for(Object o : questions) {
			JSONObject q = (JSONObject)o;
			Question question = new Question(q.get("question").toString());
			
			JSONArray answers = (JSONArray) q.get("answers");
			for(Object a : answers) {
				JSONObject ans = (JSONObject) a;
				String answer = ans.get("answer").toString();
				int value = Integer.parseInt(ans.get("value").toString());
				question.addAnswer(answer, value);
			}
			list.add(question);
		}
		return list;
	}
	
	/**
	 * Get the questions from the JSON object as a JSON string
	 * @return
	 */
	public String getQuestionsJSONString() {
		JSONArray questions = (JSONArray) object.get("questions");
		return questions.toString();
	}

	/**
	 * Validate the loaded settings file.
	 * @return
	 */
	@Override
	public boolean isValid() {
		try {
			getQuestions();
		}
		catch (Exception e) {
			System.err.println("Invalid JSON file.");
			return false;
		}
		return true;
	}
}
