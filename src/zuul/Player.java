package zuul;

public class Player {
	private InventoryController inventory = new InventoryController();
	private String currentLocation;
	public InventoryController getInvController() {
		return inventory;
	}
	public void setLocation(String location) {
		currentLocation = location;
	}
	public String getLocation() {
		return currentLocation;
	}
}
