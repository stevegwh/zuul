package npc;

import zuul.InputHandler;
import zuul.Output;
import zuul.RoomController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class NPC {
	private String name;
	private HashMap<String, String> dialogOptions = new HashMap<>();
	private ArrayList<String> dialogResponses = new ArrayList<>();
	private String currentLocation;
	private List<String> movementPath = new ArrayList<String>();

	private String getUserChoice() {
		InputHandler inputHandler = new InputHandler();
		Output.printf(">> ");
		String[] inputArray = inputHandler.parseInput(inputHandler.getInput(), 1);
		return inputArray[0];
	}

	// parse input method should take a MAX_LENGTH variable. This should pass in the length of the dialogOptions
	// TODO: Validate input
	public void onTalk() {
		printDialog();
		String userChoice = getUserChoice();
		int idx = Integer.parseInt(userChoice) - 1;
		Output.println(dialogResponses.get(idx));
	}

	public void update() {
		RoomController.moveActor(this, "room2");
	}
	
	public void printDialog() {
		for(String key : dialogOptions.keySet()) {
			Output.println(key + ". " + dialogOptions.get(key));
		}
		Output.printSeperator();
	}
	
    protected void setMovementPath(String[] rooms) {
    	for(String room : rooms) {
    		movementPath.add(room);
    	}
    }
	public void onInvestigate() {
		Output.println("You see " + name);
	}

	public String getCurrentLocation() {
		return currentLocation;
	}
	
	public HashMap<String, String> getDialogOptions() {
		return dialogOptions;
	}

	public void setDialogOptions(String[] options) {
		for(int i = 0, length = options.length; i < length; i++) {
			dialogOptions.put(Integer.toString(i + 1), options[i]);
		}
	}

	public void setDialogResponses(String[] responses) {
		for(String response: responses) {
			dialogResponses.add(response);
		}
	}
	public String getName() {
		return name;
	}
	public NPC(String name, String currentLocation, String[] options, String[] responses) {
		this.name = name;
		this.currentLocation = currentLocation;
		setDialogOptions(options);
		setDialogResponses(responses);
	}

}
