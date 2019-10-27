package npc;

import zuul.GameController;

import java.util.Set;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import IO.IOHandler;
import jsonDataHandler.JSONDataHandler;

public abstract class NPC {
	private JsonObject json;
	private String name;
	private String currentLocation;

	private String getUserDialogChoice() {
		String[] inputArray = IOHandler.input.getUserInput();
		return inputArray[0];
	}

	public abstract boolean onGive(String takeableItem);

	public abstract void update();

	public void printDialog() {
		JsonArray dialogOptions = (JsonArray) json.get("dialogOptions");
		for (int i = 0, len = dialogOptions.size(); i < len; i++) {
			String option = (String) dialogOptions.get(i);
			IOHandler.output.printCharDialog(Integer.toString(i + 1) + ": " + option);
		}
	}

	public void onTalk() {
		printDialog();
		String userChoice = getUserDialogChoice();
		if (userChoice.length() > 1 || userChoice.matches("d")) {
			IOHandler.output.printError("Invalid Command");
			return;
		}
		int idx = Integer.parseInt(userChoice) - 1;
		JsonArray dialogResponses = (JsonArray) json.get("dialogResponses");
		IOHandler.output.printCharDialog(((String) dialogResponses.get(idx)));
	}
	
	// TODO: Implement
	public void getRandomRoom() {
		JsonObject room = GameController.getRoomModel().getRoom(currentLocation);
		Set<String> exits = GameController.getRoomModel().getAllExits().keySet();
		String destination = null;
		destination = (String) GameController.getRoomModel().getExit(destination);
//		move(destination);
	}

	/**
	 * Updates the actorsInRoom field of the destination room and the room specified
	 * in the NPC's currentLocation field.
	 * 
	 * @destination The name of the destination room.
	 */
	public void move(String destinationRoomName) {
		JsonArray destinationRoom = GameController.getRoomModel().getActorsInRoom(destinationRoomName);
		JsonArray currentRoom = GameController.getRoomModel().getActorsInRoom(currentLocation);
		if (destinationRoom.indexOf(name) < 0) {
			destinationRoom.add(name);
			currentRoom.remove(name);
			currentLocation = destinationRoomName;
		}
	}

	/**
	 * validItem is the name TakeableItem that this NPC accepts. For example, the
	 * NPC 'John' could accept the TakeableItem 'Gum' Can return null if the NPC
	 * doesn't accept any TakeableItems. Returns the name only, not the object
	 * itself.
	 * 
	 * @return the name of the TakeableItem that this NPC accepts.
	 */
	public String getValidItem() {
		return (String) json.get("validItem");
	}

	/**
	 * @return The name of this NPC.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The current location of this NPC.
	 */
	public String getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * @param newLocation The new location for the NPC.
	 */
	public void setCurrentLocation(String newLocation) {
		currentLocation = newLocation;
	}

	// TODO: Scan the json file and see if the actor already exists
	private void loadJSON(String name) {
		JSONDataHandler jsonHandler = new JSONDataHandler("res/npcData.json");
		json = jsonHandler.getField(name);
		String initialRoom = (String) json.get("location");
		JsonArray destinationRoomJson = GameController.getRoomModel().getActorsInRoom(initialRoom);
		destinationRoomJson.add(name);
		currentLocation = initialRoom;
	}

	public NPC(String name) {
		this.name = name;
		loadJSON(name);
	}
}
