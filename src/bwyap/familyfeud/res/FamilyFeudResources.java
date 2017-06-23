package bwyap.familyfeud.res;
import bwyap.familyfeud.gui.UIManager;
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
		if (UIManager.isWidescreen()) {
			addResource("load", "/bwyap/familyfeud/res/ar16_9/loading1080-widescreen.png", ResourceType.PNG);
			addResource("blur", "/bwyap/familyfeud/res/ar16_9/blur1080-widescreen.png", ResourceType.PNG);
			addResource("title", "/bwyap/familyfeud/res/ar16_9/familyFeud1080-widescreen.png", ResourceType.PNG);
			addResource("panel", "/bwyap/familyfeud/res/ar16_9/panel1080-widescreen.png", ResourceType.PNG);
		}
		else {
			addResource("load", "/bwyap/familyfeud/res/ar4_3/loading960.png", ResourceType.PNG);
			addResource("blur", "/bwyap/familyfeud/res/ar4_3/blur960.png", ResourceType.PNG);
			addResource("title", "/bwyap/familyfeud/res/ar4_3/familyFeud960.png", ResourceType.PNG);
			addResource("panel", "/bwyap/familyfeud/res/ar4_3/panel960.png", ResourceType.PNG);
		}
		
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
