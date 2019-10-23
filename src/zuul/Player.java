package zuul;

public class Player {
	// TODO: Getter?
	public InventoryController inventory = new InventoryController();
	private String currentLocation;
	
	public void setLocation(String location) {
		currentLocation = location;
	}
	public String getLocation() {
		return currentLocation;
	}

}
