package zuul;

import java.util.ArrayList;

public class InventoryController {
	private static int totalWeight;
	private final static int WEIGHT_LIMIT = 10;
	private static ArrayList<TakeableItem> inventory = new ArrayList<TakeableItem>();

	public int getWeight() {
		return totalWeight;
	}

	public static void setWeight(int weight) {
		totalWeight += weight;
	}

	public static ArrayList<TakeableItem> getInventory() { //TODO: use reflection to return clone
		return inventory;
	}

	public static void addItem(TakeableItem item) {
		inventory.add(item);
	}

	public static void removeItem(TakeableItem item) {
		Output.println(item.getName() + " was removed from your inventory");
		inventory.remove(item);
	}

	public static TakeableItem getItem(String itemName) {
		for(TakeableItem takeableItem : inventory) {
			if(takeableItem.getName().contentEquals(itemName)) {
				return takeableItem;
			}
		}
		return null;
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

	public static boolean overWeightLimit(int weight) {
		if(totalWeight + weight > WEIGHT_LIMIT) {
			return true;
		}
		return false;
	}

	static {
		setWeight(0);
	}

}
