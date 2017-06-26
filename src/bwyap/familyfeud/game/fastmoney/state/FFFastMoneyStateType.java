package bwyap.familyfeud.game.fastmoney.state;

/**
 * States in a family feud game whilst playing Fast Money
 * @author bwyap
 *
 */
public enum FFFastMoneyStateType {
	// TODO
	;
	
	private String name;
	
	private FFFastMoneyStateType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
