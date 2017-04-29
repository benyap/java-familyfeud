package bwyap.familyfeud.game;

import bwyap.familyfeud.test.FamilyFeudTestDriver;

/**
 * This class holds information about a Family Feud game, its components and its state.
 * @author bwyap
 *
 */
public class FamilyFeudGame {
	
	private FFStateMachine stateMachine;
	
	public FamilyFeudGame() {
		
	}
	
	public void init() {
		stateMachine = new FFStateMachine();
		stateMachine.init();
		
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) System.out.println("Game initialized.");
	}
	
}
