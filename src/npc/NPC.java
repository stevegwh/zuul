package npc;

import zuul.RoomController;
import zuul.ZuulMessageHandler;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import IO.InputHandler;
import IO.OutputHandler;
import jsonDataHandler.JSONDataHandler;

public abstract class NPC {
	private JsonObject json;
	private String name;
	private String currentLocation;

	private String getUserChoice() {
		InputHandler inputHandler = new InputHandler();
		OutputHandler.printf(">> ");
		String[] inputArray = inputHandler.getInput(1);
		return inputArray[0];
	}

	public abstract boolean onGive(String takeableItem);

	// parse input method should take a MAX_LENGTH variable. This should pass in the length of the dialogOptions
	// TODO: Validate input
	public void onTalk() {
		printDialog();
		String userChoice = getUserChoice();
		int idx = Integer.parseInt(userChoice) - 1;
		JsonArray dialogResponses = (JsonArray) json.get("dialogResponses");
		OutputHandler.println((String) dialogResponses.get(idx));
	}

	public void update() {
		//RoomController.moveActor(this, "room2"); TODO: Should moveActor be part of this class or RoomLoader?
	}
	
	public void move() {
		// TODO: Eventually needs to be randomized by the movePath
		JsonArray destinationRoom = RoomController.getActorsInRoom("room3");
		RoomController.moveActorToRoom(this, destinationRoom);
	}
	
	public void printDialog() {
		JsonArray dialogOptions = (JsonArray) json.get("dialogOptions");
		for(int i = 0, len = dialogOptions.size(); i < len ; i++) {
			OutputHandler.println(Integer.toString(i + 1) + ". " + dialogOptions.get(i));
		}
		ZuulMessageHandler.printSeperator();
	}
	

	public void onInvestigate() {
		OutputHandler.println("You see " + name); //TODO: Could add a more in-depth description of people.
	}
	
	public String getValidItem() {
		return (String) json.get("validItem");
	}

	public String getName() {
		return name;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	private void loadJSON(String name) {
		JSONDataHandler jsonHandler = new JSONDataHandler("res/npcData.json");
		json = jsonHandler.getField(name);
	}

	public NPC(String name, String currentLocation) {
		this.name = name; // Not necessary for json load
		this.setCurrentLocation(currentLocation);
		loadJSON(name);
	}




}
