package bwyap.familyfeud.game.state;

/**
 * States in a family feud game
 * @author bwyap
 *
 */
public enum FFStateType {
	
	START("start"),
	NEW_GAME("new game"),
	ADD_FAMILY("add family"),
	LOAD_QUESTIONS("load questions"),
	INITIALIZE_GAME("initialize game"),
	PLAY("play"),
	END_GAME("end game");
	
	private String name;
	
	private FFStateType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
