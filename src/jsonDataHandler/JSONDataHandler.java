package jsonDataHandler;

import org.json.simple.JSONObject;

// Important class that holds the JSON data for the entire game
public final class JSONDataHandler {
	private static JSONObject data;
    
    public static JSONObject getRoom(String roomName) {
		JSONObject roomData = (JSONObject) data.get(roomName);
    	return roomData;
    }

    static {
    	data = (JSONObject) Parser.generateData("res/roomData.json"); 
    }
}