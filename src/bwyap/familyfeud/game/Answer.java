package bwyap.familyfeud.game;

/**
 * An answer contains the answer string and the points the answer is worth.
 * @author bwyap
 *
 */
public class Answer implements Comparable<Answer> {
	
	private String answerString;
	private int value;
	private boolean revealed;
	
	/**
	 * Create a new answer object
	 * @param answerString
	 * @param value
	 */
	public Answer(String answerString, int value) {
		this.answerString = answerString;
		this.value = value;
		this.revealed = false;
	}
	
	/**
	 * Get the answer as a string
	 * @return
	 */
	public String getAnswerString() {
		return answerString;
	}
	
	/**
	 * Get the number of points this answer is worth
	 * @return
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Check if the answer has been revealed
	 * @return
	 */
	public boolean isRevealed() {
		return revealed;
	}
	
	/**
	 * Set if the answer has been revealed
	 * @param revealed
	 */
	public void setReveal(boolean revealed) {
		this.revealed = revealed;
	}

	@Override
	public int compareTo(Answer o) {
		return this.value - o.value;
	}
	
	@Override
	public String toString() {
		return getAnswerString();
	}
	
}
