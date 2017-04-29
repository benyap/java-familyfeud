package bwyap.familyfeud.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A question contains a question string and a list of answers.
 * 
 * @author bwyap
 *
 */
public class Question {
	
	private String questionString; 
	private List<Answer> answers;
	private boolean answered;
	
	/**
	 * Create a new question object with the specified question string
	 * @param question
	 */
	public Question(String questionString) {
		this.questionString = questionString;
		this.answered = false;
		this.answers = new ArrayList<Answer>();
	}
	
	/**
	 * Add an answer to the question
	 * @param answerString
	 * @param value
	 */
	public void addAnswer(String answerString, int value) {
		answers.add(new Answer(answerString, value));
	}
	
	/**
	 * Gets a list of the answers to the question, sorted in order from highest points to lower points
	 * @return
	 */
	public List<Answer> getAnswers() {		
		Collections.sort(answers, Collections.reverseOrder());
		return answers;
	}
	
	/**
	 * Get the question as a string
	 * @return
	 */
	public String getQuestionString() {
		return questionString;
	}

	/**
	 * Check if this question has been answered
	 * @return
	 */
	public boolean isAnswered() {
		return answered;
	}
	
	/**
	 * Set whether the answer has been answered or not
	 * @param answered
	 */
	public void setAnswered(boolean answered) {
		this.answered = answered;
	}
	
	@Override
	public String toString() {
		return getQuestionString();
	}
	
}
