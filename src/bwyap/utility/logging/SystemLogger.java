package bwyap.utility.logging;

/**
 * A concrete logger class that prints log messages to the System output stream
 * @author bwyap
 *
 */
public class SystemLogger extends Logger {

	protected SystemLogger() { }
	
	@Override
	public void logMessage(String msg) {
		System.out.println("[MSG] " + msg);
	}

	@Override
	public void logInfo(String msg) {
		System.out.println("[INF] " + msg);
	}

	@Override
	public void logErr(String msg) {
		System.err.println("[ERR] " + msg);
	}

	@Override
	public void logWarning(String msg) {
		System.out.println("[WRN] " + msg);
	}

}
