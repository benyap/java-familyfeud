package bwyap.familyfeud.res;

import bwyap.familyfeud.sound.SoundManager;
import bwyap.gridgame.res.ResourceLoader;
import bwyap.gridgame.res.ResourceType;

/**
 * Loads resources for the Family Feud application
 * @author bwyap
 *
 */
public class FamilyFeudResources extends ResourceLoader {

	@Override
	public void init() {
		// Adding custom resources
		addResource("load", "/bwyap/familyfeud/res/ar4_3/loading1280.png", ResourceType.PNG);
		addResource("blur", "/bwyap/familyfeud/res/ar4_3/blur1280.png", ResourceType.PNG);
		addResource("title", "/bwyap/familyfeud/res/ar4_3/familyFeud1280.png", ResourceType.PNG);
		addResource("panel", "/bwyap/familyfeud/res/ar4_3/panel1280.png", ResourceType.PNG);
		addResource("scoreboard", "/bwyap/familyfeud/res/ar4_3/scoreboard1280.png", ResourceType.PNG);
		
		addResource("bg", "/bwyap/familyfeud/res/universal/background.png", ResourceType.PNG);
		addResource("strike", "/bwyap/familyfeud/res/universal/strike.png", ResourceType.PNG);

		// Load fonts
		addResource("Bebas Neue", "/bwyap/familyfeud/res/universal/BebasNeue.otf", ResourceType.FONT);
		addResource("Monaco", "/bwyap/familyfeud/res/universal/Monaco.ttf", ResourceType.FONT);
		
		// Load sounds
		SoundManager.getInstance().loadClip("blip", "/bwyap/familyfeud/res/sound/blip.wav");
		SoundManager.getInstance().loadClip("bell", "/bwyap/familyfeud/res/sound/bell.wav");
		SoundManager.getInstance().loadClip("strike", "/bwyap/familyfeud/res/sound/strike.wav");
		
	}
	
}
