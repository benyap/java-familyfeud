package bwyap.familyfeud.render;

import java.util.HashMap;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.fastmoney.state.FFFastMoneyState;
import bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateType;
import bwyap.familyfeud.game.play.state.FFPlayStateType;
import bwyap.familyfeud.game.state.FFStateType;
import bwyap.familyfeud.game.state.StateFastMoney;
import bwyap.familyfeud.render.component.RenderComponentContainer;
import bwyap.familyfeud.render.state.play.RenderQuestionSet;
import bwyap.familyfeud.render.state.play.RenderStrikes;

public class RenderFastMoneyMachine extends AbstractRenderMachine {
	
	protected static final int STATE_P1_ANSWER = 0x00;
	protected static final int STATE_P1_REVEAL = 0x01;
	protected static final int STATE_P2_ANSWER = 0x02;
	protected static final int STATE_P2_REVEAL = 0x03;
	
	protected HashMap<Integer, AbstractRenderState> renderFastMoneyStates;
	protected RenderQuestionSet questionRenderer;
	protected RenderStrikes strikeRenderer;
	protected FFFastMoneyStateType previousStateType;
	
	/**
	 * Create a new render play machine
	 * @param game
	 */
	public RenderFastMoneyMachine(FamilyFeudGame game) {
		super(game);
	}
	
	/**
	 * Reset the render machine
	 */
	public void reset() {
		initRenderStates();
	}
	
	@Override
	protected void initRenderStates() {
		previousStateType = null;
		renderFastMoneyStates = new HashMap<Integer, AbstractRenderState>();
		RenderComponentContainer container = null;
		
		// Create containers for each stage
		
	}
	
	@Override
	public void update(float timeElapsed) {
		if (game.getState().getType() == FFStateType.FAST_MONEY) {
			StateFastMoney state = (StateFastMoney) game.getState();
			FFFastMoneyState fastMoneyState = state.getFastMoneyState();
			
			if (fastMoneyState != null) {
				FFFastMoneyStateType type = fastMoneyState.getType();
				if (previousStateType != type) {
					switch (type) {
					
					}
				}
				previousStateType = fastMoneyState.getType();
			}
		}
		
		if (renderState != null) renderState.update(timeElapsed);
	}
	
}
