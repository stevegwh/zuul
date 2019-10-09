package chooseyourownadventure;

import java.util.ArrayList;

public class Inventory {
	private int weight;
	private static ArrayList<TakeableItem> inventory = new ArrayList<TakeableItem>();

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public static ArrayList<TakeableItem> getInventory() {
		return inventory;
	}

	public static void addItem(TakeableItem item) {
		inventory.add(item);
	}
	
	Inventory() {
		setWeight(0);
	}

	public static boolean checkIfExists(String itemToCheck) {
		if (inventory.size() == 0) {
			return false;
		}
		for(TakeableItem item : inventory) {
			String name = item.getName();
			if(name.equals(itemToCheck)) {
				return true;
			}
		}
		return false;
	}

}
