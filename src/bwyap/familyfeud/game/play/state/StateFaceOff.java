package bwyap.familyfeud.game.play.state;

import bwyap.familyfeud.game.play.FFPlayState;
import bwyap.familyfeud.game.play.FFPlayStateType;

public class StateFaceOff extends FFPlayState {

	protected StateFaceOff() {
		super(FFPlayStateType.FACE_OFF);
	}

	@Override
	public void initState(Object data) {
		if (data instanceof Integer) {
			System.out.println(getType() + " received data: " + data);
		}
	}

	@Override
	public void cleanupState() { }

}
