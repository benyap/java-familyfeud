package bwyap.utility.logging;

/**
 * This class provides a static Logger class.
 * An concrete Logger class should be implemented 
 * and created in the {@code createInstance()} method.
 * @author bwyap
 *
 */
public abstract class Logger implements LoggerInterface {
	
	protected static Logger INSTANCE = null;
	
	private static void createInstance() {
		// Create concrete Logger class and store it in the INSTANCE variable
		INSTANCE = new SystemLogger();
	}
	
	/**
	 * Log plain text
	 * @param msg
	 */
	public static void print(String msg) {
		if (INSTANCE == null) createInstance();
		INSTANCE.printMessage(msg);
	}

	/**
	 * Log a message
	 * @param msg
	 */
	public static void log(String msg) {
		if (INSTANCE == null) createInstance();
		INSTANCE.logMessage(msg);
	}

	/**
	 * Log an info message
	 * @param msg
	 */
	public static void info(String msg) {
		if (INSTANCE == null) createInstance();
		INSTANCE.logInfo(msg);
	}

	/**
	 * Log an error message
	 * @param msg
	 */
	public static void err(String msg) {
		if (INSTANCE == null) createInstance();
		INSTANCE.logErr(msg);
	}
	
	/**
	 * Log a warning message
	 * @param msg
	 */
	public static void warning(String msg) {
		if (INSTANCE == null) createInstance();
		INSTANCE.logWarning(msg);	
	}
	
}
