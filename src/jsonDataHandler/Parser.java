package jsonDataHandler;


import java.io.FileReader;


import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

// Solely responsible for importing JSON from a file and returning it
public final class Parser {
	private Object obj = new Object();

	/**
	 * Deserializes the JSON file
	 * @param file Path of the JSON file
	 * @return The deserialized JSON object
	 */
    public Object generateData(String file) { //TODO: hacky
        try {
            obj = Jsoner.deserialize(new FileReader(file));
            return (JsonObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
