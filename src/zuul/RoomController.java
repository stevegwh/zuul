package zuul;

import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import eventHandler.ZuulEventHandler;
import jsonDataHandler.JSONDataHandler;
import npc.NPC;

public final class RoomController {
	private static JSONDataHandler jsonHandler;
	private static JsonObject currentRoomJSON;

	// TODO: Think if maybe you can call more than one room at a time with this
	public static void getNewRoom(String nextRoom) {
		currentRoomJSON = jsonHandler.getField(nextRoom);
		ZuulEventHandler.output.onRoomEnter(currentRoomJSON);
		Player.setLocation(nextRoom);
	}

	public static JsonObject getAllExits() {
		JsonObject exits = (JsonObject) currentRoomJSON.get("exits");
		return (JsonObject) exits.clone();
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
	
	public static String getLookDescription() {
		return (String) currentRoomJSON.get("lookDescription");
	}

	public static void removeTakeableItem(JsonObject toRemove) {
		((JsonArray) currentRoomJSON.get("takeableItems")).remove(toRemove);
	}

	/**
	 * Converts TakeableItem to a JsonObject and adds it to the takeableItems JsonArray.
	 * If the JsonObject for this room doesn't have the JsonArray takeableObjects then it
	 * is initialised here and added to the main JsonObject
	 * 
	 * @param toAdd
	 */
	public static void addTakeableItem(TakeableItem toAdd) {
		JsonObject itemJSON = new JsonObject();
		itemJSON.put("name", toAdd.getName());
		itemJSON.put("weight",String.valueOf(toAdd.getWeight()));
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
	
	/**
	 * Returns all Actors/NPCs in the requested room's JsonObject
	 * @param roomName key of the JsonObject needed.
	 * @return JsonObject of key roomName.
	 */
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

	/**
	 * Checks if the current room's JsonObject has a 'takeableItems' field.
	 * @return true/false
	 */
	public static boolean hasTakeableItems() {
		return currentRoomJSON.get("takeableItems") != null;
	}

	/**
	 * Checks if the current room's JsonObject has an 'interactableItems' field.
	 * @return true/false
	 */
	public static boolean hasInteractableItems() {
		return currentRoomJSON.get("interactableItems") != null;
	}

	// TODO: Messy. Refactor.
	public static ArrayList<String> getInteractableItems() {
		if(hasInteractableItems()) {
			JsonArray interactableItems = (JsonArray) currentRoomJSON.get("interactableItems");
			ArrayList<String> arr = new ArrayList<>();
			for(Object item : interactableItems) {
				JsonObject obj = (JsonObject) item;
				String name = (String) obj.get("name");
				arr.add(name);
			}
			return arr;
		}
		return null;
	}

	/**
	 * Checks if the current room's JsonObject has the specified NPC/Actor.
	 * @param actorName the actor/NPC name
	 * @return true/false
	 */
	public static boolean hasActor(String actorName) {
		JsonArray actors = (JsonArray) currentRoomJSON.get("actorsInRoom");
		return actors.indexOf(actorName) >= 0;
	}

	public static void renderExits() {
		JsonObject exits = (JsonObject) currentRoomJSON.get("exits");
		ZuulEventHandler.output.renderExits(exits);
	}

	static {
		jsonHandler = new JSONDataHandler("res/roomData.json");
		getNewRoom("room1");
	}


}
