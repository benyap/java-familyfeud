package bwyap.familyfeud.game;

import java.util.ArrayList;
import java.util.List;

import bwyap.familyfeud.res.JSONQuestionSet;

/**
 * A set of questions for a family feud game
 * @author bwyap
 *
 */
public class QuestionSet {
	
	private List<Question> questions;
	private int selectedIndex = -1;
	
	/**
	 * Create a new question set
	 */
	public QuestionSet() {
		reset();
	}
	
	/**
	 * Load a question set from a JSONQuestionSet object.
	 * This will remove any existing questions in the question set.
	 * @param data
	 */
	public void loadFromJSON(JSONQuestionSet data) {
		questions = data.getQuestions();
	}
	
	/**
	 * Load a question set from a JSONQuestionSet object
	 * and add all questions from the data set into the 
	 * current set of questions.
	 * @param data
	 */
	public void addFromJSON(JSONQuestionSet data) {
		questions.addAll(data.getQuestions());
	}
	
	/**
	 * Get a question from the question set
	 * @param index
	 * @return
	 */
	public Question getQuestion(int index) {
		return questions.get(index);
	}
	
	/**
	 * Get the selected question from the question set
	 * @return
	 */
	public Question getSelectedQuestion() {
		return questions.get(selectedIndex);
	}
	
	/**
	 * Set the selected question
	 * @param index
	 */
	public void setSelectedQuestion(int index) {
		selectedIndex = index;
	}

	/**
	 * Get the list of questions in the question set
	 * @return
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * Get the number of questions in the question set
	 * @return
	 */
	public int size() {
		return questions.size();
	}

	/**
	 * Reset the question set. 
	 * This will remove all questions.
	 */
	public void reset() {
		questions = new ArrayList<Question>();
	}
	
}
