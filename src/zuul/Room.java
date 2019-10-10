package zuul;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jsonDataHandler.JSONDataHandler;

public class Room {
	private static JSONObject currentRoomJSON;
	private static String description;
	private static String lookDescription;
	private static JSONArray takeableItems;
	private static JSONArray interactableItems;
	private static JSONObject exits;

	public static void getNewRoom(String nextRoom) {
		currentRoomJSON = JSONDataHandler.getRoom(nextRoom);
		description = (String) currentRoomJSON.get("description");
		lookDescription = (String) currentRoomJSON.get("lookDescription");
		takeableItems = (JSONArray) currentRoomJSON.get("takeableItems");
		interactableItems = (JSONArray) currentRoomJSON.get("interactableItems");
		exits = (JSONObject) currentRoomJSON.get("exits");
	}

	public static String getExit(String exit) {
		return (String) exits.get(exit);
	}

	//TODO: Replace with forEach from json-simple
	private JSONObject ifExistsInArrayReturnObj(String toCheck, JSONArray arr) {
		for (Object element : arr) {
			JSONObject obj = (JSONObject) element;
			if (toCheck.equals(obj.get("name"))) {
				return obj;
			}
		}
		return null;
	}

	public static void printDescription() {
		Output.println(description);
	}
	
	public static String getLookDescription() {
		return lookDescription;
	}
	
	// Check if itemToUse is in inventory O
	// Check if interactableItem exists O
	// Pass itemToUse into checkIfValidItem method
	public void use(String itemToUse, String interactableItem) {
		if (!Inventory.checkIfExists(itemToUse)) {
			Output.println("You do not have '" + itemToUse + "' in your inventory");
			return;
		}
		JSONObject obj = ifExistsInArrayReturnObj(interactableItem, interactableItems);
		// Make sure room has interactableItems and the item the player using exists
		// TODO: Check if you should check ifExistsInArray... after checking interactableItems is null. Might cause error.
		if (interactableItems != null && obj != null) {
			String name = (String) obj.get("name");
			String validItem = (String) obj.get("validItem");
			InteractableItem item = new InteractableItem(name, validItem);
			item.onUse(itemToUse, "unlock");
			// TODO: Overwrite/Erase the item and just the old onInvestigate with onInvestigateAfteruse
			return;
		}
		Output.println("Couldn't find " + interactableItem);

	}
	

	public void printItems() {
		Output.println("Printing items");
		for(Object item : takeableItems) {
			JSONObject obj = (JSONObject) item;
			Output.println((String) obj.get("name"));
		}
	}
	
	//TODO: Take, Investigate and Use are very similar

	// Checks if player command is in the interactableItems array. If so, create new instance of InteractableItem and call onInvestigate()
	public void investigate(String toInvestigate) {
		JSONObject obj = ifExistsInArrayReturnObj(toInvestigate, interactableItems);
		if (interactableItems != null && obj != null) {
			//You don't really need to even create a new object here, you could just print the investigate description
			//Maybe for clarity this approach is nicer...
			String descriptionOnInvestigate = (String) obj.get("onInvestigate");
			InteractableItem item = new InteractableItem(descriptionOnInvestigate);
			item.onInvestigate();
			return;
		}
		Output.println("Couldn't find that.");
	}

	//TODO check weight. Make this a method in the Inventory class
	public void take(String toTake) {
		JSONObject obj = ifExistsInArrayReturnObj(toTake, takeableItems);
		if (takeableItems != null && obj != null) {
			Output.println("You picked up " + toTake);
			String name = (String) obj.get("name");
			int weight = Integer.parseInt((String) obj.get("weight"));
			TakeableItem item = new TakeableItem(name, weight);
			Inventory.addItem(item);
			takeableItems.remove(obj);
			return;
		}
		Output.println("Couldn't find that.");
	}

	//Instead of GameLoader you could initialise JSONDataHandler here
	static {
		getNewRoom("room1");
	}
}
