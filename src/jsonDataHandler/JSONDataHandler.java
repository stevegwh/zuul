package jsonDataHandler;

import org.json.simple.JSONObject;

// Important class that holds the JSON data for the entire game
public class JSONDataHandler {
	private static JSONObject data;
	
	public static void setData() {
    	Parser parser = new Parser();
    	data = (JSONObject) parser.generateData("res/roomData.json"); 
	}
    
    public static JSONObject getRoom(String roomName) {
		JSONObject roomData = (JSONObject) data.get(roomName);
    	return roomData;
    }

    JSONDataHandler() {
    	setData();
    }
}