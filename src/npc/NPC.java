package npc;

import zuul.InputHandler;
import zuul.Output;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jsonDataHandler.JSONDataHandler;

public abstract class NPC {
	private JSONObject json;
	private String name;
	private String currentLocation;

	private String getUserChoice() {
		InputHandler inputHandler = new InputHandler();
		Output.printf(">> ");
		String[] inputArray = inputHandler.parseInput(inputHandler.getInput(), 1);
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
		Output.println((String) dialogResponses.get(idx));
	}

	public void update() {
		//RoomController.moveActor(this, "room2"); TODO: Should moveActor be part of this class or RoomLoader?
	}
	
	public void printDialog() {
		JSONArray dialogOptions = (JSONArray) json.get("dialogOptions");
		int i = 1;
		for(Object option : dialogOptions) {
			option = (String) option;
			Output.println(Integer.toString(i) + ". " + option);
			i++;
		}
		Output.printSeperator();
	}
	

	public void onInvestigate() {
		Output.println("You see " + name); //TODO: Could add a more in-depth description of people.
	}

	public String getCurrentLocation() {
		return currentLocation;
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
		this.currentLocation = currentLocation;
	}



}
