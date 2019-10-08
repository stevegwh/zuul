package chooseyourownadventure;

import java.util.HashMap;

// Initialised all necessary game objects and data.
// Pulls data from the JSON config file and stores it in memory
// Initialises Room
// Initialises all TakeableItems and InteractableItems and stores them in a HashMap
// TODO: Make the game completely configurable from the JSON file without being reliant on the HashMaps
public class GameLoader {
	private JSONDataHandler jsonDataHandler = new JSONDataHandler();
	private HashMap<String, InteractableItem> interactableItems = new HashMap<String, InteractableItem>();
	private HashMap<String, TakeableItem> takeableItems = new HashMap<String, TakeableItem>();
	private Room room = new Room(jsonDataHandler);

	public JSONDataHandler getJSONDataHandler() {
		return jsonDataHandler;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public HashMap getInteractableItems() {
		return interactableItems;
	}
	
	public HashMap getTakeableItems() {
		return takeableItems;
	}
	
//  Method to return any HashMap that belongs to this class by taking in a string name
//	public void getHashMap(String name) {
//		Object hashMap = null;
//		try {
//			hashMap = getClass().getDeclaredField(name);
//		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		}
//		System.out.println(hashMap.getClass().getName());
//	}
}
