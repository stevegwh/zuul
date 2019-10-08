package chooseyourownadventure;

import org.json.simple.JSONObject;

public class JSONDataHandler {
	private JSONObject data;
    
    public JSONObject getRoom(String roomName) {
		JSONObject roomData = (JSONObject) data.get(roomName);
    	return roomData;
    }

    JSONDataHandler() {
    	data = (JSONObject) ParseJSON.generateData("res/roomData.json"); 
    }
}
