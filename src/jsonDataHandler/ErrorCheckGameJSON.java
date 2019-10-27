package jsonDataHandler;

import java.util.Iterator;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import java.util.HashMap;

// Class that parses the JSON file and checks for any game specific errors made by the developer/designer
// Does not catch JSON specific errors (a JSON plugin can do this for you)

public class ErrorCheckGameJSON {
	JsonObject data;

	private boolean existsInArray(String toCheck, String[] arr) {
		for (String ele : arr) {
			if (ele.contentEquals(toCheck)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * Checks to see if all directions are spelt correctly and that there are only
	 * four exits Does not check if there are duplicates of directions (JSON
	 * plugins/checkers can catch this)
	 * 
	 * @param currentRoom
	 * @param roomName
	 */
	private void checkDirections(JsonObject currentRoom, String roomName) {
		JsonObject exits = (JsonObject) currentRoom.get("exits");
		String[] directions = { "north", "south", "east", "west" };
		if (exits.size() > directions.length) {
			System.err.println("WARNING: duplicate exit found in: " + roomName);
		}
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = exits.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			if (!existsInArray(key, directions)) {
				System.err.println("WARNING: Invalid direction in 'exits' of : " + roomName);
			}
		}
	}

	// Checks if the exits align. So if you are in room2 and go North to room3 then
	// room3 should have room2 to the South.
	// Needs to account for unlock method adding extra exits also
	private void checkExitsCorrespond(JsonObject exits, String currentRoomName) {
		HashMap<String, String> oppositeDirections = new HashMap<String, String>();
		oppositeDirections.put("north", "south");
		oppositeDirections.put("south", "north");
		oppositeDirections.put("east", "west");
		oppositeDirections.put("west", "east");
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = exits.keySet().iterator(); iterator.hasNext();) {
			String direction = (String) iterator.next();
			// Get the JsonObject of the destination
			String destinationName = (String) exits.get(direction);
			JsonObject destination = (JsonObject) data.get(destinationName);
			JsonObject destinationExits = (JsonObject) destination.get("exits");
			// Check if exit is aligned
			String correspondingExit = (String) destinationExits.get(direction);
//		    System.err.println(correspondingExit);
			try {
				// Block of code to try
				if (correspondingExit.contentEquals(currentRoomName)) {
					continue;
				}
			} catch (Exception e) {
				// Block of code to handle errors
				System.err.println("WARNING: The path between " + direction + " " + currentRoomName + " and "
						+ oppositeDirections.get(direction) + " " + destinationName + " do not align"); // TODO: Could
																										// be expressed
																										// better
			}

		}
	}

	private boolean checkThatTakeableItemsExist(JsonObject currentRoomObject, String currentRoomName) {
		return (JsonArray) currentRoomObject.get("takeableItems") == null;
	}

	private void checkTakeableObjectWeight(JsonArray takeableObjects, String currentRoomName) {
		for (Object ele : takeableObjects) {
			JsonObject obj = (JsonObject) ele;
			String name = (String) obj.get("name");
			if (!obj.containsKey("weight")) {
				System.err
						.println("WARNING: " + currentRoomName + " takeableObjects " + name + " does not have weight");
			} else {
				if (obj.get("weight").toString() != obj.get("weight")) {
					System.err.println("WARNING: " + currentRoomName + " takeableObjects " + name
							+ " weight isn't string. Put value in quotation marks.");
				}
			}

		}

	}

	// Makes sure that any exit points to a room that exists
	// Does this by checking the exit's value with all keys in game
	private void validateRoomNames() {

	}

	// Makes sure any method specified in the onUse object is a valid class in the
	// interactableItem package
	private void validateOnUseMethods() {

	}

	private void checkExits() {
		// Adapted from answer at:
		// https://stackoverflow.com/questions/24371957/iterate-through-JsonObject-from-root-in-json-simple
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = data.keySet().iterator(); iterator.hasNext();) {
			String currentRoomName = (String) iterator.next();
			JsonObject currentRoomObject = (JsonObject) data.get(currentRoomName);
			checkDirections(currentRoomObject, currentRoomName);
			if (checkThatTakeableItemsExist(currentRoomObject, currentRoomName)) {
				checkTakeableObjectWeight((JsonArray) currentRoomObject.get("takeableItems"), currentRoomName);
			}

//		    checkExitsCorrespond((JsonObject) currentRoomObject.get("exits"), currentRoomName);
		}
	}

	public void startCheck() {
		checkExits();
	}

	public ErrorCheckGameJSON() {
		JSONDataHandler jsonDataHandler = new JSONDataHandler("res/roomData.json");
		data = jsonDataHandler.getAllData();
	}
}
