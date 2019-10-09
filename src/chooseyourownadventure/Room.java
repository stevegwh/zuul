package chooseyourownadventure;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Room {
	private JSONObject currentRoomJSON;
	private JSONDataHandler jsonData;
	private String description;
	private String lookDescription;
	private JSONArray takeableItems;
	private JSONArray interactableItems;
	private JSONObject exits;

	private void getNSetRoomData(String nextRoom) {
		currentRoomJSON = jsonData.getRoom(nextRoom);
		description = (String) currentRoomJSON.get("description");
		lookDescription = (String) currentRoomJSON.get("lookDescription");
		takeableItems = (JSONArray) currentRoomJSON.get("takeableItems");
		interactableItems = (JSONArray) currentRoomJSON.get("interactableItems");
		exits = (JSONObject) currentRoomJSON.get("exits");
	}

	public void changeRoom(String direction) {
		Object nextRoom = exits.get(direction);
		if (nextRoom != null) {
			getNSetRoomData(nextRoom.toString());
		} else {
			Output.println("You can't go that way");
		}
	}
	private JSONObject ifExistsInArrayReturnObj(String toCheck, JSONArray arr) {
		for (Object element : arr) {
			JSONObject obj = (JSONObject) element;
			if (toCheck.equals(obj.get("name"))) {
				return obj;
			}
		}
		return null;
	}

	public void printDescription() {
		Output.println(description);
	}
	
	public void look() {
		if (lookDescription != null) {
			Output.println(lookDescription);
		} else {
			Output.println("Nothing interesting to report");
		}
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

	Room(JSONDataHandler jsonDataHandler) {
		this.jsonData = jsonDataHandler;
		getNSetRoomData("room1");
	}
}
