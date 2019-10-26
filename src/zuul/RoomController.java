package zuul;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import jsonDataHandler.JSONDataHandler;

// TODO: Singleton or not?
public final class RoomController {
	private static JSONDataHandler jsonHandler;
	private JsonObject currentRoomJSON;

	public JsonObject getRoom(String room) {
		return jsonHandler.getField(room);
	}
	public void setNewRoom(String nextRoom) {
		currentRoomJSON = jsonHandler.getField(nextRoom);
	}

	public JsonObject getAllExits() {
		JsonObject exits = (JsonObject) currentRoomJSON.get("exits");
		return (JsonObject) exits.clone();
	}

	public String getExit(String exit) {
		// TODO: Throw an exception if room exit isn't a key in the json.
		return (String) ((JsonObject) currentRoomJSON.get("exits")).get(exit);
	}
	
	public void addExit(String direction, String destination) {
		JsonObject exits = (JsonObject) currentRoomJSON.get("exits");
		exits.put(direction, destination);
	}

	// TODO: This isn't obvious
	public JsonObject ifItemExistsReturnIt(String toCheck) {
		JsonArray arr = (JsonArray) currentRoomJSON.get("takeableItems");
		return (JsonObject) arr.stream().filter(o -> ((JsonObject) o).get("name").equals(toCheck)).findFirst().orElse(null);
	}
	
	public String getLookDescription() {
		return (String) currentRoomJSON.get("lookDescription");
	}

	public void removeTakeableItem(JsonObject toRemove) {
		((JsonArray) currentRoomJSON.get("takeableItems")).remove(toRemove);
	}

	/**
	 * Converts TakeableItem to a JsonObject and adds it to the takeableItems JsonArray.
	 * If the JsonObject for this room doesn't have the JsonArray takeableObjects then it
	 * is initialised here and added to the main JsonObject
	 * 
	 * @param toAdd
	 */
	public void addTakeableItem(TakeableItem toAdd) {
		JsonObject itemJSON = new JsonObject();
		itemJSON.put("name", toAdd.getName());
		itemJSON.put("weight",String.valueOf(toAdd.getWeight()));
		if(!hasTakeableItems()) {currentRoomJSON.put("takeableItems", new JsonArray());}
		((JsonArray) currentRoomJSON.get("takeableItems")).add(itemJSON);
	}
	
	/**
	 * Returns all Actors/NPCs in the requested room's JsonObject
	 * @param roomName key of the JsonObject needed.
	 * @return JsonObject of key roomName.
	 */
	public JsonArray getActorsInRoom(String roomName) {
		// TODO: Throw an exception if room name is bad.
		JsonArray room = (JsonArray) jsonHandler.getField(roomName).get("actorsInRoom");
		if(room == null) {
			jsonHandler.getField(roomName).put("actorsInRoom", new JsonArray());
			room = (JsonArray) jsonHandler.getField(roomName).get("actorsInRoom");
		}
		return room;
	}
	

	/**
	 * Checks if the current room's JsonObject has a 'takeableItems' field.
	 * @return true/false
	 */
	public boolean hasTakeableItems() {
		return currentRoomJSON.get("takeableItems") != null;
	}

	public JsonArray getTakeableItems() {
		if(hasTakeableItems()) {
			return (JsonArray) currentRoomJSON.get("takeableItems");
		}
		return null;
	}

	/**
	 * Checks if the current room's JsonObject has the specified NPC/Actor.
	 * @param actorName the actor/NPC name
	 * @return true/false
	 */
	public boolean hasActor(String actorName) {
		JsonArray actors = (JsonArray) currentRoomJSON.get("actorsInRoom");
		return actors.indexOf(actorName) >= 0;
	}

	public String getDescription() {
		return (String) currentRoomJSON.get("description");
	}

	public RoomController(String room) {
		jsonHandler = new JSONDataHandler("res/roomData.json");
		setNewRoom(room);
	}



}
