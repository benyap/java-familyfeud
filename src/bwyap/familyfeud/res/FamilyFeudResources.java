package bwyap.familyfeud.res;

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
		addResource("load", "/bwyap/familyfeud/res/ar4_3/Loading.png", ResourceType.PNG);
		addResource("bg", "/bwyap/familyfeud/res/ar4_3/Background.png", ResourceType.PNG);
		addResource("title", "/bwyap/familyfeud/res/ar4_3/FamilyFeud.png", ResourceType.PNG);
		
		// Load fonts
		addResource("Bebas Neue", "", ResourceType.FONTNAME);
		addResource("Monaco", "", ResourceType.FONTNAME);

	}
	
}
