package bwyap.familyfeud.game.state;

/**
 * This class is responsible for creating and initializing the game states of a Family Feud game.
 * Each state should only be created once.
 * @author bwyap
 *
 */
public class FFStateFactory {
	
	private static FFState start;
	private static FFState newGame;
	private static FFState addFamily;
	private static FFState loadQuestions;
	private static FFState initializeGame;
	private static FFState play;
	private static FFState endGame;
	
	/**
	 * Get the FFState object for the specified state.
	 * A new state object is created if it has not already been created.
	 * @param type
	 * @return
	 */
	public static FFState getState(FFStateType type) {
		switch(type) {
		case ADD_FAMILY:
			if (addFamily == null) createAddFamilyState();
			return addFamily;
		case END_GAME:
			if (endGame == null) createEndGameState();
			return endGame;
		case INITIALIZE_GAME:
			if (initializeGame == null) createInitializeGameState();
			return initializeGame;
		case LOAD_QUESTIONS:
			if (loadQuestions == null) createLoadQuestionsState();
			return loadQuestions;
		case NEW_GAME:
			if (newGame == null) createNewGameState();
			return newGame;
		case PLAY:
			if (play == null) createPlayState();
			return play;
		case START:
			if (start == null) createStartState();
			return start;
		}
		return null;
	}

	
	// =================
	// Create the states
	// =================
	
	private static void createStartState() {
		start = new StateStart();
	}

	private static void createPlayState() {
		play = new StatePlay();
	}

	private static void createNewGameState() {
		newGame = new StateNewGame();
	}

	private static void createLoadQuestionsState() {
		loadQuestions = new StateLoadQuestions();
	}

	private static void createInitializeGameState() {
		initializeGame = new StateInitializeGame();
	}

	private static void createEndGameState() {
		endGame = new StateEndGame();
	}

	private static void createAddFamilyState() {
		addFamily = new StateAddFamily();
	}
	
}
