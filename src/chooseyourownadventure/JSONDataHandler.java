package chooseyourownadventure;

import org.json.simple.JSONObject;

// Important class that holds the JSON data for the entire game
public class JSONDataHandler {
	private JSONObject data;
    
    public JSONObject getRoom(String roomName) {
		JSONObject roomData = (JSONObject) data.get(roomName);
    	return roomData;
    }

    JSONDataHandler() {
    	ParseJSON parser = new ParseJSON();
    	data = (JSONObject) parser.generateData("res/roomData.json"); 
    }
}
