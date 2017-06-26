package bwyap.familyfeud.game.fastmoney.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents an instance of a Fast Money game
 * @author bwyap
 *
 */
public class FastMoney {
	
	public static final int QUESTIONS = 5;
	
	private Map<Integer, List<FastMoneyAnswer>> answers;
	private Map<Integer, Boolean> show;
	private final int players;
	private float timer;
	private boolean timerRunning;
	
	/**
	 * Create a new Fast Money game
	 * @param players
	 */
	public FastMoney(int players) {
		this.players = players;
		reset();
	}
	
	/**
	 * Create a Fast Money game with a default of 2 players
	 */
	public FastMoney() {
		this(2);
	}
	
	/**
	 * Get the number of players in fast money
	 * @return
	 */
	public int getNumPlayers() {
		return players;
	}
	
	/**
	 * Reset the fast money game
	 */
	public void reset() {
		timerRunning = false;
		timer = 20;
		answers = new HashMap<Integer, List<FastMoneyAnswer>>();
		show = new HashMap<Integer, Boolean>();
		
		// Fill answers and scores with blank answers		
		for(int i = 0; i < players; i++) {
			List<FastMoneyAnswer> answerList = new ArrayList<FastMoneyAnswer>();
			for(int j = 0; j < QUESTIONS; j++) {
				answerList.add(new FastMoneyAnswer());
			}
			answers.put(i, answerList);
			show.put(i, false);
		}
	}
	
	/**
	 * Get a player's answer to a fast money question
	 * @param player
	 * @param question
	 * @return
	 */
	public FastMoneyAnswer getAnswer(int player, int question) {
		return answers.get(player).get(question);
	}
	
	/**
	 * Set a player's answer to a question
	 * @param player
	 * @param question
	 * @param answer
	 */
	public void setAnswer(int player, int question, String answer) {
		answers.get(player).get(question).setAnswer(answer);
	}
	
	/**
	 * Set the score of a player's answer to a question
	 * @param player
	 * @param question
	 * @param score
	 */
	public void setScore(int player, int question, int score) {
		answers.get(player).get(question).setScore(score);
	}
	
	/**
	 * Set whether a player's answer is revealed
	 * @param player
	 * @param question
	 * @param revealed
	 */
	public void setRevealed(int player, int question, boolean revealed) {
		answers.get(player).get(question).setRevealed(revealed);
	}
	
	/**
	 * Check if the player's answers should be shown
	 * @param player
	 * @return
	 */
	public boolean showAnswers(int player) {
		return show.get(player);
	}
	
	/**
	 * Set whether a player's answers should be shown
	 * @param player
	 * @param question
	 * @param revealed
	 */
	public void setShow(int player, boolean show) {
		this.show.put(player, show);
	}
	
	/**
	 * Get the timer 
	 * @return
	 */
	public float getTimer() {
		return timer/1000;
	}
	
	/**
	 * Set the timer
	 * @param timer
	 */
	public void setTimer(float timer) {
		this.timer = timer * 1000;
	}
	
	/**
	 * Update the timer with the time elapsed
	 * @param timeElapsed
	 */
	public void updateTimer(float timeElapsed) {
		timer -= timeElapsed;
		
		// Automatically stop if timer has reached 0
		if (timer <= 0) {
			timer = 0;
			timerRunning = false;
		}
	}
	
	/**
	 * Check if the timer is running
	 * @return
	 */
	public boolean isTimerRunning() {
		return timerRunning;
	}
	
	/**
	 * Set whether the timer is running
	 * @param running
	 */
	public void setTimerRunning(boolean running) {
		this.timerRunning = running;
	}
	
	/**
	 * Check if a player has answered all questions
	 * @param player
	 * @return
	 */
	public boolean allAnswered(int player) {
		List<FastMoneyAnswer> answers = this.answers.get(player);
		for(FastMoneyAnswer answer : answers) {
			if (answer.getScore() < 0 || answer.getAnswer().equals("")) return false; 
		}
		return true;
	}
	
	/**
	 * This class represents an answer from the player in the fast money game
	 * @author bwyap
	 *
	 */
	public class FastMoneyAnswer {
		private String answer = "";
		private int score = -1;
		private boolean revealed;
		public String getAnswer() { return answer; }
		public int getScore() { return score; }
		public boolean isRevealed() { return revealed; }
		public void setAnswer(String answer) { this.answer = answer; }
		public void setScore(int score) { this.score = score; }
		public void setRevealed(boolean revealed) { this.revealed = revealed; }
	}

}
