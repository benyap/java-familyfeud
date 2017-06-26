package bwyap.familyfeud.res;
import bwyap.familyfeud.gui.UIManager;
import bwyap.familyfeud.sound.SoundManager;
import bwyap.familyfeud.sound.SoundManager.SoundRegion;
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
			addResource("fastmoney", "/bwyap/familyfeud/res/ar16_9/fastmoney1080-widescreen.png", ResourceType.PNG);
		}
		else {
			addResource("load", "/bwyap/familyfeud/res/ar4_3/loading960.png", ResourceType.PNG);
			addResource("blur", "/bwyap/familyfeud/res/ar4_3/blur960.png", ResourceType.PNG);
			addResource("title", "/bwyap/familyfeud/res/ar4_3/familyFeud960.png", ResourceType.PNG);
			addResource("panel", "/bwyap/familyfeud/res/ar4_3/panel960.png", ResourceType.PNG);
			addResource("fastmoney", "/bwyap/familyfeud/res/ar4_3/fastmoney960.png", ResourceType.PNG);
		}
		
		addResource("bg", "/bwyap/familyfeud/res/universal/background.png", ResourceType.PNG);
		addResource("strike", "/bwyap/familyfeud/res/universal/strike.png", ResourceType.PNG);

		// Load fonts
		addResource("Bebas Neue", "/bwyap/familyfeud/res/universal/BebasNeue.otf", ResourceType.FONT);
		addResource("Monaco", "/bwyap/familyfeud/res/universal/Monaco.ttf", ResourceType.FONT);
		
		// Load sounds
		SoundManager.getInstance().loadClip("answer", SoundRegion.AU, "/bwyap/familyfeud/res/sound/au_answer.wav");
		SoundManager.getInstance().loadClip("topanswer", SoundRegion.AU, "/bwyap/familyfeud/res/sound/au_topanswer.wav");
		SoundManager.getInstance().loadClip("blip", SoundRegion.AU, "/bwyap/familyfeud/res/sound/au_blip.wav");
		SoundManager.getInstance().loadClip("strike", SoundRegion.AU, "/bwyap/familyfeud/res/sound/au_strike.wav");
		SoundManager.getInstance().loadClip("theme", SoundRegion.AU, "/bwyap/familyfeud/res/sound/au_theme_short.wav");
		SoundManager.getInstance().loadClip("fm_answer", SoundRegion.AU, "/bwyap/familyfeud/res/sound/au_answer.wav");
		SoundManager.getInstance().loadClip("fm_strike", SoundRegion.AU, "/bwyap/familyfeud/res/sound/us_strike.wav");
		SoundManager.getInstance().loadClip("fm_tryagain", SoundRegion.AU, "/bwyap/familyfeud/res/sound/us_tryagain.wav");

		SoundManager.getInstance().loadClip("blip", SoundRegion.US, "/bwyap/familyfeud/res/sound/us_blip.wav");
		SoundManager.getInstance().loadClip("answer", SoundRegion.US, "/bwyap/familyfeud/res/sound/us_bell.wav");
		SoundManager.getInstance().loadClip("strike", SoundRegion.US, "/bwyap/familyfeud/res/sound/us_strike.wav");
		//SoundManager.getInstance().loadClip("theme", SoundRegion.US, "/bwyap/familyfeud/res/sound/us_theme_short.wav");
		SoundManager.getInstance().loadClip("fm_answer", SoundRegion.US, "/bwyap/familyfeud/res/sound/us_bell.wav");
		SoundManager.getInstance().loadClip("fm_strike", SoundRegion.US, "/bwyap/familyfeud/res/sound/us_strike.wav");
		SoundManager.getInstance().loadClip("fm_tryagain", SoundRegion.US, "/bwyap/familyfeud/res/sound/us_tryagain.wav");

	}
	
}
