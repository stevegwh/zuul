package itemMethods;

import org.json.simple.JSONObject;

import zuul.Output;
import zuul.RoomController;

public class Unlock implements ItemMethod {

	@Override
	public void execute(String[] args) {
		JSONObject exits = (JSONObject) RoomController.getAllExits();
		// TODO: Check if these are valid directions and if the room exists
		exits.put(args[1], args[2]);
		Output.println("Door unlocked! Maybe...");
	}
}
