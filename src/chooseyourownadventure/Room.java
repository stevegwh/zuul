package chooseyourownadventure;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Room {
	private JSONObject currentRoomJSON;
	private JSONDataHandler jsonData;
	private String description;
	private String lookDescription;
	private JSONArray takeableItems;
	private JSONArray interactableItems;
	private JSONObject exits;

	private void getNSetRoomData(String nextRoom) {
		currentRoomJSON = jsonData.getRoom(nextRoom);
		description = (String) currentRoomJSON.get("description");
		lookDescription = (String) currentRoomJSON.get("lookDescription");
		takeableItems = (JSONArray) currentRoomJSON.get("takeableItems");
		interactableItems = (JSONArray) currentRoomJSON.get("interactableItems");
		exits = (JSONObject) currentRoomJSON.get("exits");
	}

	public void changeRoom(String direction) {
		Object nextRoom = exits.get(direction);
		if (nextRoom != null) {
			getNSetRoomData(nextRoom.toString());
		} else {
			Output.println("You can't go that way");
		}
	}

	public void printDescription() {
		Output.println(description);
	}
	
	public void look() {
		if (lookDescription != null) {
			Output.println(lookDescription);
		} else {
			Output.println("Nothing interesting to report");
		}
	}
	

	public void printItems() {
		for(Object item : takeableItems) {
			Output.println((String) item);
		}
	}

	public boolean investigate(String toInvestigate) {
		HashMap<String, InteractableItem> instanceHashMap = Game.getInteractableItems();
		for(Object item : interactableItems) {
			if(item.equals(toInvestigate) && instanceHashMap.containsKey(toInvestigate)) {
				Output.println("You investigated " + toInvestigate); //TODO: Replace with interactableItem.onInvestigate()
				return true;
			}
		}
		return false;
	}

	public boolean take(String toTake) { //TODO: Replace for loop with forEach JsonArray method
		HashMap<String, TakeableItem> instanceHashMap = Game.getTakeableItems();
		for(Object item : takeableItems) {
			if(item.equals(toTake) && instanceHashMap.containsKey(toTake)) {
				Output.println("You picked up " + toTake);
				takeableItems.remove(toTake);
				return true;
			}
		}
		return false;
	}

	Room(JSONDataHandler jsonDataHandler) {
		this.jsonData = jsonDataHandler;
		getNSetRoomData("room1");
	}
}
