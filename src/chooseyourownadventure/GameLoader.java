package chooseyourownadventure;

// Initialised all necessary game objects and data.
// Pulls data from the JSON config file and stores it in memory
// Initialises Room
// TODO: Make the game completely configurable from the JSON file without being reliant on the HashMaps
// TODO: Decide if this is necessary or not
public class GameLoader {
	private JSONDataHandler jsonDataHandler = new JSONDataHandler();
	private Room room = new Room(jsonDataHandler);

	public JSONDataHandler getJSONDataHandler() {
		return jsonDataHandler;
	}
	
	public Room getRoom() {
		return room;
	}
}
