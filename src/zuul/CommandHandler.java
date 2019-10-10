//responsible for validating and executing commands 
package zuul;

import commands.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class CommandHandler { 

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
