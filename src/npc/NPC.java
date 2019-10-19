package npc;

import zuul.RoomController;
import zuul.ZuulMessageHandler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import IO.InputHandler;
import IO.OutputHandler;
import jsonDataHandler.JSONDataHandler;

public abstract class NPC {
	private JSONObject json;
	private String name;

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
		JSONArray dialogResponses = (JSONArray) json.get("dialogResponses");
		OutputHandler.println((String) dialogResponses.get(idx));
	}

	public void update() {
		//RoomController.moveActor(this, "room2"); TODO: Should moveActor be part of this class or RoomLoader?
	}
	
	public void move() {
		JSONArray room = RoomController.getActorsInRoom("room2");
		RoomController.moveActorToRoom(this, room);
	}
	
	public void printDialog() {
		JSONArray dialogOptions = (JSONArray) json.get("dialogOptions");
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
	
	private void loadJSON(String name) {
		JSONDataHandler jsonHandler = new JSONDataHandler("res/npcData.json");
		json = jsonHandler.getField(name);
	}

	public NPC(String name, String currentLocation) {
		this.name = name; // Not necessary for json load
		loadJSON(name);
	}



}
