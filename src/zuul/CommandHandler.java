//responsible for validating and executing commands 
package zuul;

import commands.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import IO.OutputHandler;

public class CommandHandler {
	
	HashMap<String, Command> commands = new HashMap<>();

	public void handleCommand(String[] inputArray) {
		String userCommand = inputArray[0];
		// Capitalise the user's command to make it match the class names
		String commandName = userCommand.substring(0, 1).toUpperCase() + userCommand.substring(1).toLowerCase();
		if(CommandWords.commandExists(userCommand.toUpperCase())) {
			// To avoid instantiating a new object every time we only call newInstance() if the command has never been called.
			// It is then pushed into a HashMap in order to be reused later.
			if(!commands.containsKey(commandName)) {
				Object command = CommandInstantiator.createInstance(commandName);
				commands.put(commandName, (Command) command);
				((Command) command).execute(inputArray);
			} else {
				commands.get(commandName).execute(inputArray);
			}
		} else {
			ZuulMessageHandler.invalidCommand();
		}
	}
	CommandHandler() {
	}
}
