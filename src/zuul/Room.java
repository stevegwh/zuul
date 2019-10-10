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

	//TODO: Replace with forEach from json-simple
	public static JSONObject ifExistsInArrayReturnObj(String toCheck, JSONArray arr) {
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

	public static JSONArray getTakeableItems() {
		return takeableItems;
	}

	public static JSONArray getInteractableItems() {
		return interactableItems;
	}

	static {
		getNewRoom("room1");
	}
}
