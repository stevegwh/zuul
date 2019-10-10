package jsonDataHandler;


import java.io.FileReader;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// Solely responsible for importing JSON from a file and returning it
public final class Parser {
	private static JSONParser parser = new JSONParser();
	private static Object obj = new Object();

    public static Object generateData(String file) { //TODO: hacky
        try {
            obj = parser.parse(new FileReader(file));
            return (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
