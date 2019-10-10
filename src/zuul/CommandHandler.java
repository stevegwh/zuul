//responsible for validating and executing commands 
package zuul;

import commands.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

//TODO: Make command class with the commands as subclasses. Put them in a separate package and all as static, import only here

public class CommandHandler { //TODO: Make command class
	public enum CommandWords {
		USE, QUIT, INVESTIGATE, TAKE, GO, HELP, ITEMS, DROP, GIVE, LOOK;
	}


//	private void take(String toTake) {
//		room.take(toTake);
//	}
	
//	private void showHelp() {
//		Output.printf("Valid commands are: ");
//		for(int i = 0; i < commands.length; i++) {
//			String command = commands[i];
//			if (i + 1 == commands.length) {
//				Output.println(command + ".");
//			} else {
//				Output.printf(command + ", ");
//			}
//		}
//	}
//	
//	private void useItem(String itemToUse, String interactableItem) {
//		room.use(itemToUse, interactableItem);
//	}
//
//		room.printItems();
//	}
//
//	private void executeCommand(String command, String command2, String command3) { // Doesn't check for preposition
//		switch(command) {
//		case "use":
//			useItem(command2, command3);
//			break;
//		default:
//			Output.println("Something went wrong");
//		}
//	}

//	private void executeCommand(String command, String command2) {
//		switch(command) {
//		case "go":
//			changeRoom(command2);
//			break;
//		case "investigate":
//			investigate(command2);
//			break;
//		case "take":
//			take(command2);
//			break;
//		default:
//			Output.println("Something went wrong");
//		}
//	}
//	
//	private void executeCommand(String command) {
//		switch(command) {
//		case "help":
//			showHelp();
//			break;
//		case "quit":
//			Game.quit();
//			break;
//		case "inventory":
//			listInventory();
//			break;
//		case "look":
//			look();
//			break;
//		case "printItems":
//			room.printItems();
//			break;
//		default:
//			Output.println("Something went wrong");
//		}
//	}

	public boolean contains(String userInput) {
	    for (CommandWords c : CommandWords.values()) {
	        if (c.name().equals(userInput)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public void handleCommand(String input, String[] inputArray) {
		String userCommand = inputArray[0];
		String commandName = userCommand.substring(0, 1).toUpperCase() + userCommand.substring(1).toLowerCase();
		if(contains(userCommand.toUpperCase())) {
			try {
				Object command = Class.forName("commands." + commandName).getConstructor().newInstance();
				((Command) command).execute(inputArray);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			Output.println("Invalid command");
		}
	}
	CommandHandler() {
	}
}
