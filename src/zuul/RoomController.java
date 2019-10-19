package zuul;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import IO.OutputHandler;
import jsonDataHandler.JSONDataHandler;
import npc.NPC;

public final class RoomController {
	private static JSONDataHandler jsonHandler;
	private static JSONObject currentRoomJSON;

	public static void getNewRoom(String nextRoom) {
		currentRoomJSON = jsonHandler.getField(nextRoom);
		OutputHandler.println((String) currentRoomJSON.get("description"));
		printExits();
		Player.setLocation(nextRoom);
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
		OutputHandler.println((String) currentRoomJSON.get("description"));
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
	
	@SuppressWarnings("unchecked")
	public static JSONArray getActorsInRoom(String roomName) {
		JSONArray room = (JSONArray) jsonHandler.getField(roomName).get("actorsInRoom");
		if(room == null) {
			jsonHandler.getField(roomName).put("actorsInRoom", new JSONArray());
			getActorsInRoom(roomName); // TODO: potential infinite loop
		}
		return room;
	}
	
	// Need to fix this and then make the NPC call this at random
	@SuppressWarnings("unchecked")
	public static void moveActorToRoom(NPC actor, JSONArray destination) {
		destination.add(actor.getName());
		// This assumes the NPC is always in the same room as the player. Needs to have its own
		// currentLocation that you update instead
		JSONArray currentRoom = (JSONArray) currentRoomJSON.get("actorsInRoom");
		if(currentRoom != null) {
			currentRoom.remove(actor.getName());
		}
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
	@SuppressWarnings("unchecked")
	public static void printExits() {
		JSONObject exits = (JSONObject) currentRoomJSON.get("exits");
		OutputHandler.println("Exits: ");
		exits.forEach((k, v) -> OutputHandler.println(ZuulTools.capitalize((String) k) + ": " + v));
	}

	static {
		jsonHandler = new JSONDataHandler("res/roomData.json");
		getNewRoom("room1");
	}


}
