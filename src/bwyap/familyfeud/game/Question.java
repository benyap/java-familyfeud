package bwyap.familyfeud.game;

import java.util.PriorityQueue;

/**
 * A question contains a question string and a list of answers.
 * 
 * @author bwyap
 *
 */
public class Question {
	
	private String questionString; 
	private PriorityQueue<Answer> answers;
	private boolean answered;
	
	/**
	 * Create a new question object with the specified question string
	 * @param question
	 */
	public Question(String questionString) {
		this.questionString = questionString;
		this.answered = false;
		this.answers = new PriorityQueue<Answer>();
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
	public Answer[] getAnswers() {
		Answer[] list = new Answer[answers.size()];
		
		// Extract answers in order
		for(int i = 0; i < answers.size(); i++) {
			list[i] = answers.remove();
		}
		
		// Add answers back to list
		for(Answer a : list) {
			answers.add(a);			
		}
		
		return list;
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
