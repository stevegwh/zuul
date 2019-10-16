//responsible for validating and executing commands 
package zuul;

import commands.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class CommandHandler {
	
	HashMap<String, Command> commands = new HashMap<>();

	public boolean contains(String userInput) {
	    for (CommandWords c : CommandWords.values()) {
	        if (c.name().equals(userInput)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public void handleCommand(String[] inputArray) {
		String userCommand = inputArray[0];
		// Capitalise the user's command to make it match the class names
		String commandName = userCommand.substring(0, 1).toUpperCase() + userCommand.substring(1).toLowerCase();
		if(contains(userCommand.toUpperCase())) {
			try {
				// To avoid instantiating a new object every time we only call newInstance() if the command has never been called.
				// It is then pushed into a HashMap in order to be reused later.
				if(!commands.containsKey(commandName)) {
					Object command = Class.forName("commands." + commandName + "Cmd").getConstructor().newInstance();
					commands.put(commandName, (Command) command);
					((Command) command).execute(inputArray);
				} else {
					commands.get(commandName).execute(inputArray);
				}
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
