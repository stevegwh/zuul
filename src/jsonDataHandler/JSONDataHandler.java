package jsonDataHandler;

import org.json.simple.JSONObject;

// Important class that holds the JSON data for the entire game
public final class JSONDataHandler {
	private static JSONObject data;
    
    public static JSONObject getField(String fieldName) {
		JSONObject fieldData = (JSONObject) data.get(fieldName);
    	return fieldData;
    }

    static {
    	data = (JSONObject) Parser.generateData("res/roomData.json"); 
    }
}