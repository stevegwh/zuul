package chooseyourownadventure;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Room {
	private String description;
	private JSONArray items;
	private JSONObject exits;
	private void parseRoomJson(Object nextRoom) {
		JSONParser parser = new JSONParser();
		Object obj = new Object();
        try {
            obj = parser.parse(new FileReader("res/roomData.json"));
 
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject room = (JSONObject) jsonObject.get(nextRoom);
            description = (String) room.get("description");
            items = (JSONArray) room.get("items");
            exits = (JSONObject) room.get("exits");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void changeRoom(String direction) {
		Object nextRoom = exits.get(direction);
		parseRoomJson(nextRoom);
	}

	public void printDescription() {
		System.out.println(description);
	}

	public void printExits() {
		System.out.println(exits.get("north"));
		System.out.println(exits.get("east"));
		System.out.println(exits.get("west"));
		System.out.println(exits.get("south"));
	}

	public void printItems() {
		for(Object item : items) {
			System.out.println(item);
		}
	}
	Room() {
		parseRoomJson("room1");
	}
}
