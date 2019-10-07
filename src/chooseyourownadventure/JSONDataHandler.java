package chooseyourownadventure;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONDataHandler {
	private JSONObject data;
    
    public JSONObject getRoom(String roomName) {
		JSONObject roomData = (JSONObject) data.get(roomName);
    	return roomData;
    }
    
    public void writeRoom(String roomName, JSONObject roomJSON) { //TODO: save the room data to the json object
		JSONObject roomData = (JSONObject) data.get(roomName);
    }
    
    public void writeJSON(JSONObject json) {
    	data = json;
    }
    
    public JSONObject getData() {
    	return data;
    }

    JSONDataHandler() {
    	data = (JSONObject) ParseJSON.generateData("res/roomData.json"); 
    }
}
