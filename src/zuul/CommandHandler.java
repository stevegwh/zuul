package zuul;

import java.util.HashMap;

import commandhandler.*;

/**
 * Responsible for passing the command forward to the CommandInstatiator and executing it.
 * If the class has already been instantiated then it is stored in the commands HashMap for later use.
 * 
 * @author Steve
 *
 */
public class CommandHandler {
	
	HashMap<String, Command> commands = new HashMap<>();

	/**
	 * Takes the first element of the inputArray and attempts to instantiate it. 
	 * @param inputArray the input produced by the user
	 */
	public void handleCommand(String[] inputArray) {
		String commandName = inputArray[0];
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
