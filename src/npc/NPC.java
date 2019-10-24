package npc;

import zuul.GameController;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import IO.IOHandler;
import jsonDataHandler.JSONDataHandler;

public abstract class NPC {
	private JsonObject json;
	private String name;
	private String currentLocation;

	// TODO: Coupled with text input. Possibly.
	private String getUserDialogChoice() {
		String[] inputArray = IOHandler.input.getUserInput();
		return inputArray[0];
	}

	public abstract boolean onGive(String takeableItem);

	public void printDialog() {
		JsonArray dialogOptions = (JsonArray) json.get("dialogOptions");
		for(int i=0, len=dialogOptions.size(); i<len; i++) {
			String option = (String) dialogOptions.get(i);
			IOHandler.output.printCharDialog(Integer.toString(i + 1) + ": " + option);
		}
	}

	public void onTalk() {
		printDialog();
		String userChoice = getUserDialogChoice();
		if(userChoice.length() > 1 || userChoice.matches("d")) {
			IOHandler.output.printError("Invalid Command");
			return;
		}
		int idx = Integer.parseInt(userChoice) - 1;
		JsonArray dialogResponses = (JsonArray) json.get("dialogResponses");
		IOHandler.output.printCharDialog(((String) dialogResponses.get(idx)));
			
	}

	public void update() {
		//RoomController.moveActor(this, "room2"); TODO: Should moveActor be part of this class or RoomLoader?
	}
	/** 
	 * Selects a location from the NPC's path field and calls RoomController's moveActorToRoom method
	 * Updates NPC's currentLocation field to destinationRoomName
	 * @destinationRoomName The name of the room you wish to move the NPC to.
	 */
	public void move(String destinationRoomName) {
		// TODO: Eventually needs to be randomized by the movePath
		JsonArray destinationRoom = GameController.getRoomController().getActorsInRoom(destinationRoomName);
		GameController.getRoomController().moveActorToRoom(this, destinationRoom);
		currentLocation = destinationRoomName;
	}
	/**
	 * validItem is the name TakeableItem that this NPC accepts.
	 * For example, the NPC 'John' could accept the TakeableItem 'Gum'
	 * Can return null if the NPC doesn't accept any TakeableItems.
	 * Returns the name only, not the object itself.
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
	
	private void loadJSON(String name) {
		JSONDataHandler jsonHandler = new JSONDataHandler("res/npcData.json");
		json = jsonHandler.getField(name);
	}

	public NPC(String name, String currentLocation) {
		this.name = name;
		this.setCurrentLocation(currentLocation);
		loadJSON(name);
	}




}
