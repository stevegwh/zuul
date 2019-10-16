package jsonDataHandler;

import org.json.simple.JSONObject;

// Important class that holds JSON data
public class JSONDataHandler {
	private JSONObject data;
    
    public JSONObject getField(String fieldName) {
		JSONObject fieldData = (JSONObject) data.get(fieldName);
    	return fieldData;
    }

    public JSONDataHandler(String path) {
    	Parser parser = new Parser();
    	data = (JSONObject) parser.generateData(path); 
    }
}