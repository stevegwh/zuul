package zuul;
// TODO: make sure weight is a string, not an int
import java.util.Iterator;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jsonDataHandler.JSONDataHandler;

// Class that parses the JSON file and checks for any game specific errors made by the developer/designer
// Does not catch JSON specific errors (a JSON plugin can do this for you)

public class ErrorCheckGameJSON extends JSONDataHandler {
	private static boolean existsInArray(String toCheck, String[] arr) {
		for(String ele : arr) {
			if(ele.contentEquals(toCheck)) {
				return true;
			}
		}
		return false;
	}
	// Checks to see if all directions are spelt correctly and that there are only four exits
	// Does not check if there are duplicates of directions (JSON plugins/checkers can catch this)
	private static void checkDuplicateDirections(JSONObject currentRoom, String roomName) {
		JSONObject exits = (JSONObject) currentRoom.get("exits");
		String[] directions = {"north", "south", "east", "west"};
		if(exits.size() > directions.length) {
			Output.println("WARNING: duplicate exit found in: " + roomName);
		}
		for(Iterator iterator = exits.keySet().iterator(); iterator.hasNext();) {
		    String key = (String) iterator.next();
		    if(!existsInArray(key, directions)) {
				Output.println("WARNING: Invalid direction in 'exits' of : " + roomName);
		    }
		}
	}

	// Checks if the exits align. So if you are in room2 and go North to room3 then room3 should have room2 to the South.
	// Needs to account for unlock method adding extra exits also
	private static void checkExitsCorrespond(JSONObject exits, String currentRoomName) {
		HashMap<String, String> oppositeDirections = new HashMap<String, String>();
		oppositeDirections.put("north", "south");
		oppositeDirections.put("south", "north");
		oppositeDirections.put("east", "west");
		oppositeDirections.put("west", "east");
		for(Iterator iterator = exits.keySet().iterator(); iterator.hasNext();) {
		    String direction = (String) iterator.next();
		    // Get the JSONObject of the destination
		    String destinationName = (String) exits.get(direction);
		    JSONObject destination = (JSONObject) data.get(destinationName);
		    JSONObject destinationExits = (JSONObject) destination.get("exits");
		    // Check if exit is aligned
		    String correspondingExit = (String) destinationExits.get(direction);
//		    Output.println(correspondingExit);
		    try {
		    	  //  Block of code to try
				if (correspondingExit.contentEquals(currentRoomName)) {
					continue;
				}
			}
			catch(Exception e) {
			  //  Block of code to handle errors
				Output.println("WARNING: The path between " + direction + " " + currentRoomName + " and " + oppositeDirections.get(direction) + " " + 
			  destinationName + " do not align"); // TODO: Could be expressed better
			}

		}
	}
	
	private static boolean checkThatTakeableItemsExist(JSONObject currentRoomObject, String currentRoomName) {
		JSONArray takeableItems = (JSONArray) currentRoomObject.get("takeableItems");
		if(takeableItems == null) {
			Output.println("WARNING: " + currentRoomName + " doesn't have takeableObjects. If this is intentional then ignore this message");
			return false;
		}
		return true;
	}

	private static void checkTakeableObjectWeight(JSONArray takeableObjects, String currentRoomName) {
		for(Object ele : takeableObjects) {
			JSONObject obj = (JSONObject) ele;
			String name = (String) obj.get("name");
			if(!obj.containsKey("weight")) {
				Output.println("WARNING: " + currentRoomName + " takeableObjects " + name + " does not have weight");
			} else {
				if(obj.get("weight").toString() != obj.get("weight")) {
					Output.println("WARNING: " + currentRoomName + " takeableObjects " + name + " weight isn't string. Put value in quotation marks.");
				}
			}
			
		}
		
	}
	
	// Makes sure that any exit points to a room that exists
	// Does this by checking the exit's value with all keys in game
	private static void validateRoomNames() {
		
	}

	// Makes sure any method specified in the onUse object is a valid class in the itemMethods package
	private static void validateOnUseMethods() {
		
	}

	private static void checkExits() {
		// Adapted from answer at: https://stackoverflow.com/questions/24371957/iterate-through-jsonobject-from-root-in-json-simple
		for(Iterator iterator = data.keySet().iterator(); iterator.hasNext();) {
		    String currentRoomName = (String) iterator.next();
		    JSONObject currentRoomObject = (JSONObject) data.get(currentRoomName);
		    checkDuplicateDirections(currentRoomObject, currentRoomName);
		    if(checkThatTakeableItemsExist(currentRoomObject, currentRoomName)) {
				checkTakeableObjectWeight((JSONArray) currentRoomObject.get("takeableItems"), currentRoomName);
		    }

//		    checkExitsCorrespond((JSONObject) currentRoomObject.get("exits"), currentRoomName);
		}
	}
	public static void startCheck() {
		checkExits();
	}
}
