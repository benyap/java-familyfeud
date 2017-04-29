package bwyap.familyfeud.game.play;

/**
 * States in a family feud game whilst in play
 * @author bwyap
 *
 */
public enum FFPlayStateType {
	
	SELECT_QUESTION("select question"),
	FACE_OFF("face off"),
	FAMILY_PLAY("family play"),
	FAMILY_STEAL("family steal"),
	ALLOCATE_POINTS("allocate points"),
	REVEAL_ANSWERS("reveal answers");	
	
	private String name;
	
	private FFPlayStateType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
