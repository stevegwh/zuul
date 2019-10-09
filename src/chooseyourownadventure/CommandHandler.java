//responsible for validating and executing commands 
package chooseyourownadventure;

import java.util.ArrayList;

//TODO: Make command class with the commands as subclasses. Put them in a separate package and all as static, import only here

public class CommandHandler { //TODO: Make command class
	private Room room;
	private String[] commands = {"printItems", "use", "quit", "investigate", "take",  "go", "help", "inventory", "drop", "give", "look"}; //TODO: should be look not investigate
	private String[] directions = {"north", "south", "west", "east"};

	public enum Direction {
		NORTH, SOUTH, EAST, WEST
	}

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
		room.take(toTake);
	}
	
	private void showHelp() {
		Output.printf("Valid commands are: ");
		for(int i = 0; i < commands.length; i++) {
			String command = commands[i];
			if (i + 1 == commands.length) {
				Output.println(command + ".");
			} else {
				Output.printf(command + ", ");
			}
		}
	}
	
	private void investigate(String toInvestigate) {
		room.investigate(toInvestigate);
	}
	
	private void look() {
		room.look();
	}
	
	private void useItem(String itemToUse, String interactableItem) {
		room.use(itemToUse, interactableItem);
	}

	private void listInventory() {
		ArrayList<TakeableItem> inventory = Inventory.getInventory();
		if(inventory.size() == 0) {
			Output.println("You do not currently have anything in your inventory");
		} else {
			Output.println("You are currently carrying: ");
			for(TakeableItem item: inventory) {
				Output.println(item.getName()); //TODO: could be capitalized
			}
		}
//		room.printItems();
	}

	private void executeCommand(String command, String command2, String command3) { // Doesn't check for preposition
		switch(command) {
		case "use":
			useItem(command2, command3);
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
			Game.quit();
			break;
		case "inventory":
			listInventory();
			break;
		case "look":
			look();
			break;
		case "printItems":
			room.printItems();
			break;
		default:
			Output.println("Something went wrong");
		}
	}
	public void handleCommand(String input, String[] inputArray) {
		if(existsInArray(inputArray[0], commands)) {
			if(inputArray.length == 4) {
				executeCommand(inputArray[0], inputArray[1], inputArray[3]); //you should change this
			}else if(inputArray.length == 2) {
				executeCommand(inputArray[0], inputArray[1]);
			} else {
				executeCommand(inputArray[0]);
			}
		} else {
			Output.println("Invalid command. Type 'help'.");
		}
	}
	CommandHandler(Room room) {
		this.room = room;
	}
}
