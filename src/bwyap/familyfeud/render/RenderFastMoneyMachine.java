package bwyap.familyfeud.render;

import java.util.HashMap;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.fastmoney.state.FFFastMoneyState;
import bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateType;
import bwyap.familyfeud.game.fastmoney.state.FastMoney;
import bwyap.familyfeud.game.state.FFStateType;
import bwyap.familyfeud.game.state.StateFastMoney;
import bwyap.familyfeud.render.component.RenderComponentContainer;
import bwyap.familyfeud.render.state.fastmoney.RenderFastMoneyAnswers;
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
		if (game.getState().getType() == FFStateType.FAST_MONEY) {
			previousStateType = null;
			renderFastMoneyStates = new HashMap<Integer, AbstractRenderState>();
			RenderComponentContainer container = null;

			FastMoney fastmoney = ((StateFastMoney)game.getState()).getFastMoneyState().getFastMoney();
			
			// Create containers for each stage
			container = new RenderComponentContainer();
			container.addComponent(new RenderFastMoneyAnswers(fastmoney));
			renderFastMoneyStates.put(STATE_P1_ANSWER, container);
			renderFastMoneyStates.put(STATE_P2_ANSWER, container);

		}
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
					case P1_ANSWER:
						renderState = renderFastMoneyStates.get(STATE_P1_ANSWER);
						break;
					case P1_REVEAL:
					case P2_ANSWER:
						renderState = renderFastMoneyStates.get(STATE_P2_ANSWER);
						break;
					case P2_REVEAL:
					}
				}
				previousStateType = fastMoneyState.getType();
			}
		}
		
		if (renderState != null) renderState.update(timeElapsed);
	}
	
}
