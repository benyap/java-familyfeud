package bwyap.gridgame.res;

/**
 * A resource used by the game engine
 * @author bwyap
 *
 */
public class Resource {
	
	private String id;
	private String location;
	private ResourceType type;
	
	public Resource(String id, String location, ResourceType type) {
		this.id = id;
		this.location = location;
		this.type = type;
	}
	
	/**
	 * Get the resource id
	 * @return
	 */
	public String id() {
		return id;
	}
	
	/**
	 * Get the location of the resource
	 * @return
	 */
	public String location() {
		return location;
	}
	
	/**
	 * Get the resource type
	 * @return
	 */
	public ResourceType type() {
		return type;
	}
	
}
