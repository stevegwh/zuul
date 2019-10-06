package chooseyourownadventure;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Room {
	private String description;
	private JSONArray items;
	private JSONObject exits;
	private JSONObject interactableObjects;
	private void parseRoomJson(Object nextRoom) {
		JSONParser parser = new JSONParser();
		Object obj = new Object();
        try {
            obj = parser.parse(new FileReader("res/roomData.json"));
 
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject room = (JSONObject) jsonObject.get(nextRoom);
            description = (String) room.get("description");
            items = (JSONArray) room.get("takeableItems");
            exits = (JSONObject) room.get("exits");
            interactableObjects = (JSONObject) room.get("interactableObjects");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void changeRoom(String direction) {
		Object nextRoom = exits.get(direction);
		parseRoomJson(nextRoom);
	}

	public void printDescription() {
		Output.println(description);
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
		for(Object item : items) {
			Output.println((String) item);
		}
	}
	Room() {
		parseRoomJson("room1");
	}
}
