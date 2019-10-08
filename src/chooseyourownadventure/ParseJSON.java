package chooseyourownadventure;


import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ParseJSON {
	static JSONParser parser = new JSONParser();
	static Object obj = new Object();

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
