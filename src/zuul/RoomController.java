package zuul;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jsonDataHandler.JSONDataHandler;
import npc.NPC;

public final class RoomController {
	private static JSONDataHandler jsonHandler;
	private static JSONObject currentRoomJSON;

	public static void getNewRoom(String nextRoom) {
		currentRoomJSON = jsonHandler.getField(nextRoom);
	}

	public static String getExit(String exit) {
		return (String) ((JSONObject) currentRoomJSON.get("exits")).get(exit);
	}
	
	@SuppressWarnings("unchecked")
	public static void addExit(String direction, String destination) {
		JSONObject exits = (JSONObject) currentRoomJSON.get("exits");
		exits.put(direction, destination);
	}

	//TODO: awful. Replace.
	@SuppressWarnings("unchecked")
	public static JSONObject ifExistsInArrayReturnObj(String toCheck, String nameOfArr) {
		JSONArray arr = nameOfArr == "takeableItems" ? (JSONArray) currentRoomJSON.get("takeableItems") : 
										(JSONArray) currentRoomJSON.get("interactableItems");
		if(arr == null) {
			System.out.println("Room doesn't have " + nameOfArr + " as an array");
			return null;
		}
		return (JSONObject) arr.stream().filter(o -> ((JSONObject) o).get("name").equals(toCheck)).findFirst().orElse(null);
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
	@SuppressWarnings("unchecked")
	public static void addTakeableItem(TakeableItem toAdd) {
		JSONObject itemJSON = new JSONObject();
		itemJSON.put("name", toAdd.getName());
		itemJSON.put("weight",String.valueOf(toAdd.getWeight()));
		// if the JSONObject for this room doesn't have JSONArray takeableObjects then we initialize it here and add it to the json file
		//TODO: Maybe you should always initalize takeableItems/interactableItems instead of doing it here if needed
		if(!hasTakeableItems()) {currentRoomJSON.put("takeableItems", new JSONArray());}
		((JSONArray) currentRoomJSON.get("takeableItems")).add(itemJSON);
	}

	public static void removeInteractableItem(JSONObject toRemove) {
		((JSONArray) currentRoomJSON.get("interactableItems")).remove(toRemove);
	}

	@SuppressWarnings("unchecked")
	public static void addInteractableItem(JSONObject toAdd) {
		((JSONArray) currentRoomJSON.get("interactableItems")).add(toAdd);
	}
	
	// Need to fix this and then make the NPC call this at random
	@SuppressWarnings("unchecked")
	public static void moveActor(NPC actor, String destination) {
		JSONObject destinationJSON = jsonHandler.getField(destination);
		JSONArray destinationActorList = (JSONArray) destinationJSON.get("actorsInRoom");
//		// If there isn't an array of actors in destination then make one
//		if(destinationActorList == null) {
//			destinationJSON.put("actorsInRoom", new JSONArray());
//			destinationActorList = (JSONArray) destinationJSON.get("actorsInRoom"); //TODO: Not sure if this is necessary
//		}
//		// Add to destination array
		destinationActorList.add(actor.getName());
		// TODO: Get actor's previous location's JSONObject
//		// TODO: Remove actor from array in previous room's JSON
	}

	public static boolean hasTakeableItems() {
		return currentRoomJSON.get("takeableItems") != null;
	}

	public static boolean hasInteractableItems() {
		return currentRoomJSON.get("interactableItems") != null;
	}
	public static boolean hasActor(String actorName) {
		JSONArray actors = (JSONArray) currentRoomJSON.get("actorsInRoom");
		return actors.indexOf(actorName) >= 0;
	}

	static {
		jsonHandler = new JSONDataHandler("res/roomData.json");
		getNewRoom("room1");
	}


}
