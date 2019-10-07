package chooseyourownadventure;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Room {
	private JSONDataHandler jsonDataHandler = new JSONDataHandler();
	private JSONObject currentRoomJSON;
	private String currentRoomName;
	private String description;
	private String lookDescription;
	private JSONArray takeableItems;
	private JSONObject exits;
	private JSONObject interactableObjects;

	private void getNSetRoomData(String nextRoom) {
		currentRoomJSON = jsonDataHandler.getRoom(nextRoom);
		currentRoomName = nextRoom;
		description = (String) currentRoomJSON.get("description");
		lookDescription = (String) currentRoomJSON.get("lookDescription");
		takeableItems = (JSONArray) currentRoomJSON.get("takeableItems");
		exits = (JSONObject) currentRoomJSON.get("exits");
		interactableObjects = (JSONObject) currentRoomJSON.get("interactableObjects");
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
	
	public void investigate(String toInvestigate) {
		if(interactableObjects.get(toInvestigate) != null) {
			Output.println((String) interactableObjects.get(toInvestigate));
		} else {
			Output.println("Can't find \"" + toInvestigate + "\"");
		}
		
	}

	public void printExits() {
		Output.println((String) exits.get("north"));
		Output.println((String) exits.get("east"));
		Output.println((String) exits.get("west"));
		Output.println((String) exits.get("south"));
	}

	public void printItems() {
		for(Object item : takeableItems) {
			Output.println((String) item);
		}
	}
	
	Room() {
		getNSetRoomData("room1");
	}

//	public Room(Object json) {
//		roomJSON = (JSONObject) json;
//		getNSetRoomData("room1");
//	}
	
	private void updateRoom() { 
		jsonDataHandler.writeRoom(currentRoomName, currentRoomJSON);
	}

	@SuppressWarnings("unchecked")
	public boolean take(String toTake) { //TODO: remove item you take from roomJSON, add to player inv
		for(Object item : takeableItems) {
			if(item.equals(toTake)) {
				Output.println("You picked up " + toTake);
				takeableItems.remove(toTake);
				updateRoom();
				return true;
			}
		}
		return false;
	}
}
