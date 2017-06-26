package bwyap.familyfeud.game.fastmoney.state;

/**
 * States in a family feud game whilst playing Fast Money
 * @author bwyap
 *
 */
public enum FFFastMoneyStateType {
	
	P1_ANSWER("p1 answer"),
	P1_REVEAL("p1 reveal"),
	P2_ANSWER("p2 answer"),
	P2_REVEAL("p2 reveal");
	
	private String name;
	
	private FFFastMoneyStateType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
