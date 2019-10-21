//responsible for validating and executing commands 
package zuul;

import java.util.HashMap;

import commandhandler.*;


public class CommandHandler {
	
	HashMap<String, Command> commands = new HashMap<>();

	public void handleCommand(String[] inputArray) {
		String commandName = inputArray[0];
		// To avoid instantiating a new object every time we only call newInstance() if the command has never been called.
		// It is then pushed into a HashMap in order to be reused later.
		if(!commands.containsKey(commandName)) {
			Object command = CommandInstantiator.createInstance(commandName);
			if(command != null) {
				commands.put(commandName, (Command) command);
				((Command) command).execute(inputArray);
			}
		} else {
			commands.get(commandName).execute(inputArray);
		}
	}
	CommandHandler() {
	}
}
