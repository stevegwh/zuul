package zuul;

public class Player {
	private InventoryModel inventory = new InventoryModel();
	private String currentLocation;

	public InventoryModel getInvModel() {
		return inventory;
	}

	public void setLocation(String location) {
		currentLocation = location;
	}

	public String getLocation() {
		return currentLocation;
	}

	public Player(String startingLocation) {
		currentLocation = startingLocation;
	}
}
