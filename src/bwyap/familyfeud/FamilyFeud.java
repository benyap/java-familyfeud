package bwyap.familyfeud;

/**
 * Main driver class for Family Feud
 * @author bwyap
 *
 */
public class FamilyFeud {
	
	public static final String TITLE = "Family Feud";
	public static final String VERSION = "v1.0.0";
	
	public static FamilyFeudController app;
	
	public static void main(String[] args) {
		init();
		start();
	}
	
	/**
	 * Initialize the controller
	 */
	public static void init() {
		app = new FamilyFeudController();
	}
	
	/**
	 * Start the controller
	 */
	public static void start() {
		app.init();
		app.start();
	}
	
}
