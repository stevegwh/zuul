package zuul;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jsonDataHandler.JSONDataHandler;

public final class Room {
	private static JSONObject currentRoomJSON;
	private static String description;
	private static String lookDescription;
	private static JSONArray takeableItems;
	private static JSONArray interactableItems;
	private static JSONObject exits;

	public static void getNewRoom(String nextRoom) {
		currentRoomJSON = JSONDataHandler.getField(nextRoom);
		description = (String) currentRoomJSON.get("description");
		lookDescription = (String) currentRoomJSON.get("lookDescription");
		takeableItems = (JSONArray) currentRoomJSON.get("takeableItems");
		interactableItems = (JSONArray) currentRoomJSON.get("interactableItems");
		exits = (JSONObject) currentRoomJSON.get("exits");
	}

	public static String getExit(String exit) {
		return (String) exits.get(exit);
	}

	// TODO: Bad practice to return private array like this as it gives full access to a private field.
	public static JSONObject getAllExits() {
		return exits;
	}

	//TODO: awful. Replace.
	public static JSONObject ifExistsInArrayReturnObj(String toCheck, String nameOfArr) {
		JSONArray arr = null;
		if(nameOfArr.contentEquals("takeableItems")) {
			arr = takeableItems;
		} else if (nameOfArr.contentEquals("interactableItems")) {
			arr = interactableItems;
		} else {
			System.out.println("Room doesn't have " + nameOfArr + " as an array");
			return null;
		}
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

	public static void removeTakeableItem(JSONObject toRemove) {
		takeableItems.remove(toRemove);
	}

	// Converts TakeableItem to JSONObject and adds it to takeableItems
	public static void addTakeableItem(TakeableItem toAdd) {
		JSONObject itemJSON = new JSONObject();
		itemJSON.put("name", toAdd.getName());
		String weight = String.valueOf(toAdd.getWeight());
		itemJSON.put("weight", weight);
		// if the JSONObject for this room doesn't have JSONArray takeableObjects then we initialize it here and add it to the json file
		if(!hasTakeableItems()) { //TODO: Maybe you should always initalize takeableItems/interactableItems instead of doing it here if needed
			takeableItems = new JSONArray();
			currentRoomJSON.put("takeableItems", takeableItems);
		}
		takeableItems.add(itemJSON);
	}

	public static void removeInteractableItem(JSONObject toRemove) {
		interactableItems.remove(toRemove);
	}

	public static void addInteractableItem(JSONObject toAdd) {
		interactableItems.add(toAdd);
	}

	public static boolean hasTakeableItems() {
		return takeableItems != null;
	}

	public static boolean hasInteractableItems() {
		return interactableItems != null;
	}


	static {
		getNewRoom("room1");
	}
}
