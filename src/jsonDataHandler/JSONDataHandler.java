package jsonDataHandler;

import com.github.cliftonlabs.json_simple.JsonObject;

// Important class that holds JSON data
public class JSONDataHandler {
	private JsonObject data;
    
    public JsonObject getField(String fieldName) {
		JsonObject fieldData = (JsonObject) data.get(fieldName);
    	return fieldData;
    }

    public JSONDataHandler(String path) {
    	Parser parser = new Parser();
    	data = (JsonObject) parser.generateData(path); 
    }
}