package zuul;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jsonDataHandler.JSONDataHandler;

public final class Room {
	private static JSONObject currentRoomJSON;

	public static void getNewRoom(String nextRoom) {
		currentRoomJSON = JSONDataHandler.getField(nextRoom);
	}

	public static String getExit(String exit) {
		return (String) ((JSONObject) currentRoomJSON.get("exits")).get(exit);
	}

	// TODO: Bad practice to return private array like this as it gives full access to a private field.
	public static JSONObject getAllExits() {
		return (JSONObject) currentRoomJSON.get("exits");
	}

	//TODO: awful. Replace.
	public static JSONObject ifExistsInArrayReturnObj(String toCheck, String nameOfArr) {
		JSONArray arr = nameOfArr == "takeableItems" ? (JSONArray) currentRoomJSON.get("takeableItems") : 
										(JSONArray) currentRoomJSON.get("interactableItems");
		if(arr == null) {
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
		Output.println((String) currentRoomJSON.get("description"));
	}
	
	public static String getLookDescription() {
		return (String) currentRoomJSON.get("lookDescription");
	}

	public static void removeTakeableItem(JSONObject toRemove) {
		((JSONArray) currentRoomJSON.get("takeableItems")).remove(toRemove);
	}

	// Converts TakeableItem to JSONObject and adds it to takeableItems
	public static void addTakeableItem(TakeableItem toAdd) {
		JSONObject itemJSON = new JSONObject();
		itemJSON.put("name", toAdd.getName());
		String weight = String.valueOf(toAdd.getWeight());
		itemJSON.put("weight", weight);
		// if the JSONObject for this room doesn't have JSONArray takeableObjects then we initialize it here and add it to the json file
		if(!hasTakeableItems()) { //TODO: Maybe you should always initalize takeableItems/interactableItems instead of doing it here if needed
			JSONArray takeableItems = new JSONArray();
			currentRoomJSON.put("takeableItems", takeableItems);
		}
		((JSONArray) currentRoomJSON.get("takeableItems")).add(itemJSON);
	}

	public static void removeInteractableItem(JSONObject toRemove) {
		((JSONArray) currentRoomJSON.get("interactableItems")).remove(toRemove);
	}

	public static void addInteractableItem(JSONObject toAdd) {
		((JSONArray) currentRoomJSON.get("interactableItems")).add(toAdd);
	}

	public static boolean hasTakeableItems() {
		return currentRoomJSON.get("takeableItems") != null;
	}

	public static boolean hasInteractableItems() {
		return currentRoomJSON.get("interactableItems") != null;
	}


	static {
		getNewRoom("room1");
	}
}
