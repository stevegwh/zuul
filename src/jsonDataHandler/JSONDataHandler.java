package jsonDataHandler;

import com.github.cliftonlabs.json_simple.JsonObject;

// Important class that holds JSON data
public class JSONDataHandler {
	private JsonObject data;
    
	/** 
	 * Queries the JsonObject for a certain field.
	 * @param key The key of the JsonObject you wish to return
	 * @return
	 */
    public JsonObject getField(String key) {
		JsonObject fieldData = (JsonObject) data.get(key);
    	return fieldData;
    }

    /**
     * Constructor for the JSONDataHandler. Requires you specify a path
     * for the JSON file.
     * @param path The path of the JSON file in the project.
     */
    public JSONDataHandler(String path) {
    	Parser parser = new Parser();
    	data = (JsonObject) parser.generateData(path); 
    }
}