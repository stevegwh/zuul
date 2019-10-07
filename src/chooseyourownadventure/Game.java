package chooseyourownadventure;

import java.util.ArrayList;
//import java.util.Arrays;

public class Game {
	private InputHandler inputHandler = new InputHandler();
	private String[] commands = {"use", "quit", "investigate", "take",  "go", "help", "inventory", "drop", "give", "look"}; //TODO: should be look not investigate
	private String[] directions = {"north", "south", "west", "east"};
	private ArrayList<String> inventory = new ArrayList<String>();
//	private JSONDataHandler jsonData = new JSONDataHandler();
//	private Room room = new Room(jsonData.getData()); //pull in JSON data from roomData.json
	private Room room = new Room();
	private boolean isRunning = true;

	private boolean existsInArray(String strToCheck, String[] arr) {
		for(String item : arr) {
			if(strToCheck.contentEquals(item)) {
				return true;
			}
		}
		return false;
	}

	private void changeRoom(String direction) {
		if(existsInArray(direction, directions)) {
			room.changeRoom(direction);
		}
	}
	
	private void take(String toTake) {
		boolean success = room.take(toTake);
		if (success) {
			inventory.add(toTake);
		} else {
			Output.println("I can't find that");
		}
	}
	
	private void showHelp() {
		Output.printf("Valid commands are: ");
		for(String command : commands) {
			Output.printf(command + ", "); //TODO: if i = length don't put comma
		}
		Output.printf("\n");
	}
	
	private void quitGame() {
		Output.println("Thanks for playing!");
		isRunning = false;
	}
	
	private void investigate(String toInvestigate) {
		room.investigate(toInvestigate);
	}
	
	private void look() {
		room.look();
	}

	private void printItems() {
		if(inventory.size() == 0) {
			Output.println("You do not currently have anything in your inventory");
		} else {
			Output.println("You are currently carrying: ");
			for(String item : inventory) {
				Output.println(item); //TODO: could be capitalized
			}
		}
//		room.printItems();
	}

	private void executeCommand(String command, String command2, String command3) {
		switch(command) {
		case "use":
			break;
		default:
			Output.println("Something went wrong");
		}
	}

	private void executeCommand(String command, String command2) {
		switch(command) {
		case "go":
			changeRoom(command2);
			break;
		case "investigate":
			investigate(command2);
			break;
		case "take":
			take(command2);
			break;
		default:
			Output.println("Something went wrong");
		}
	}
	
	private void executeCommand(String command) {
		switch(command) {
		case "help":
			showHelp();
			break;
		case "quit":
			quitGame();
			break;
		case "inventory":
			printItems(); //TODO: This is referring to the takeable items in the room, not the player's inventory
			break;
		case "look":
			look();
			break;
		default:
			Output.println("Something went wrong");
		}
	}


	public void start() {
		while(isRunning) {
			room.printDescription();
			Output.printf(">> ");
			String data = inputHandler.getInput();
			handleCommand(data);
		}
	}

	public void handleCommand(String input) {
		String[] inputArray = inputHandler.parseInput(input);
		if(existsInArray(inputArray[0], commands)) {
			if(inputArray.length > 4) {
				executeCommand(inputArray[0], inputArray[1], inputArray[3]);
			}else if(inputArray.length > 1) {
				executeCommand(inputArray[0], inputArray[1]);
			} else {
				executeCommand(inputArray[0]);
			}
		} else {
			Output.println("Invalid command. Type 'help'.");
		}
	}
}
