package zuul;

import java.util.ArrayList;

import IO.IOHandler;
import zuulutils.ZuulEventHandler;

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

	@SuppressWarnings("unchecked")
	public static ArrayList<TakeableItem> getInventory() {
		return (ArrayList<TakeableItem>) inventory.clone();
	}

	public static void addItem(TakeableItem item) {
		inventory.add(item);
	}

	public static void removeItem(TakeableItem item) {
		ZuulEventHandler.onRemoveFromInventory(item.getName());
		inventory.remove(item);
	}

	public static TakeableItem getItem(String itemName) {
		return inventory.stream().filter(o -> (o).getName().equals(itemName)).findFirst().orElse(null);
	}

	public static boolean checkIfExists(String itemToCheck) {
		if (inventory.size() == 0) {return false;}
		TakeableItem item = inventory.stream().filter(o -> (o).getName().equals(itemToCheck)).findFirst().orElse(null);
		return item != null;
	}

	public static boolean overWeightLimit(int weight) {
		return totalWeight + weight > WEIGHT_LIMIT;
	}

	static {
		setWeight(0);
	}

}
