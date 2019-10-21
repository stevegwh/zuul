package zuul;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import IO.IOHandler;
import eventHandler.ZuulEventHandler;
import jsonDataHandler.JSONDataHandler;
import npc.NPC;
import zuulutils.ZuulTools;

public final class RoomController {
	private static JSONDataHandler jsonHandler;
	private static JsonObject currentRoomJSON;

	public static void getNewRoom(String nextRoom) {
		currentRoomJSON = jsonHandler.getField(nextRoom);
		printDescription();
		printExits();
		Player.setLocation(nextRoom);
	}

	public static String getExit(String exit) {
		return (String) ((JsonObject) currentRoomJSON.get("exits")).get(exit);
	}
	
	public static void addExit(String direction, String destination) {
		JsonObject exits = (JsonObject) currentRoomJSON.get("exits");
		exits.put(direction, destination);
	}

	//TODO: awful. Replace.
	public static JsonObject ifExistsInArrayReturnObj(String toCheck, String nameOfArr) {
		JsonArray arr = nameOfArr == "takeableItems" ? (JsonArray) currentRoomJSON.get("takeableItems") : 
										(JsonArray) currentRoomJSON.get("interactableItems");
		if(arr == null) {
			System.out.println("Room doesn't have " + nameOfArr + " as an array");
			return null;
		}
		return (JsonObject) arr.stream().filter(o -> ((JsonObject) o).get("name").equals(toCheck)).findFirst().orElse(null);
	}

	public static void printDescription() {
		ZuulEventHandler.printSeperator();
		IOHandler.output.println((String) currentRoomJSON.get("description"));
		ZuulEventHandler.printSeperator();
	}
	
	public static String getLookDescription() {
		return (String) currentRoomJSON.get("lookDescription");
	}

	public static void removeTakeableItem(JsonObject toRemove) {
		((JsonArray) currentRoomJSON.get("takeableItems")).remove(toRemove);
	}

	// Converts TakeableItem to JsonObject and adds it to takeableItems
	public static void addTakeableItem(TakeableItem toAdd) {
		JsonObject itemJSON = new JsonObject();
		itemJSON.put("name", toAdd.getName());
		itemJSON.put("weight",String.valueOf(toAdd.getWeight()));
		// if the JsonObject for this room doesn't have JsonArray takeableObjects then we initialize it here and add it to the json file
		//TODO: Maybe you should always initalize takeableItems/interactableItems instead of doing it here if needed
		if(!hasTakeableItems()) {currentRoomJSON.put("takeableItems", new JsonArray());}
		((JsonArray) currentRoomJSON.get("takeableItems")).add(itemJSON);
	}

	public static void removeInteractableItem(JsonObject toRemove) {
		((JsonArray) currentRoomJSON.get("interactableItems")).remove(toRemove);
	}

	public static void addInteractableItem(JsonObject toAdd) {
		((JsonArray) currentRoomJSON.get("interactableItems")).add(toAdd);
	}
	
	public static JsonArray getActorsInRoom(String roomName) {
		JsonArray room = (JsonArray) jsonHandler.getField(roomName).get("actorsInRoom");
		if(room == null) {
			jsonHandler.getField(roomName).put("actorsInRoom", new JsonArray());
			room = (JsonArray) jsonHandler.getField(roomName).get("actorsInRoom");
		}
		return room;
	}
	
	// Need to make the NPC call this at random
	/** 
	 * Updates the actorsInRoom field of the destination room and the room specified in the NPC's
	 * currentLocation field.
	 * @actor The NPC object.
	 * @destination The actorsInRoom array of the destination room.
	 */
	public static void moveActorToRoom(NPC actor, JsonArray destination) {
		if(destination.indexOf(actor.getName()) < 0) {
			destination.add(actor.getName());
			JsonArray npcCurrentRoom = (JsonArray) jsonHandler.getField(actor.getCurrentLocation()).get("actorsInRoom");
			npcCurrentRoom.remove(actor.getName());
		}
	}

	public static boolean hasTakeableItems() {
		return currentRoomJSON.get("takeableItems") != null;
	}

	public static boolean hasInteractableItems() {
		return currentRoomJSON.get("interactableItems") != null;
	}
	public static boolean hasActor(String actorName) {
		JsonArray actors = (JsonArray) currentRoomJSON.get("actorsInRoom");
		return actors.indexOf(actorName) >= 0;
	}
	public static void printExits() {
		JsonObject exits = (JsonObject) currentRoomJSON.get("exits");
		IOHandler.output.println("Exits: ");
		exits.forEach((k, v) -> IOHandler.output.println(ZuulTools.capitalize((String) k) + ": " + v));
	}

	static {
		jsonHandler = new JSONDataHandler("res/roomData.json");
		getNewRoom("room1");
	}


}
