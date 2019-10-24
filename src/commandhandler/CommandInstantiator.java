package commandhandler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import IO.IOHandler;
//import eventHandler.ZuulEventRouter;
import zuulutils.ZuulTools;

import java.io.File;
public class CommandInstantiator {
	private ArrayList<String> commands = new ArrayList<>();
	// TODO: Make dynamic depending on IO mode
	private String dir = "consoleOutputLayer";
	
	private String buildFileName(String commandName) {
		commandName = ZuulTools.capitalize(commandName);
		commandName += "CmdLayer";
		return commandName;
	}
	
	private boolean isValid(String commandName) {
		return commands.indexOf(commandName) < 0;
	}
	

	/**
	 * Attempts to create an instance of the class that the user has specified as a command.
	 * 
	 * @param commandName The command the user has entered
	 * @return A CommandOutputLayer object or Null.
	 */
	public CommandOutputLayer createInstance(String commandName) {
		commandName = buildFileName(commandName);
		if(isValid(commandName)) {
			IOHandler.output.printError("Invalid Command");
			return null;
		}
		Object command = null;
		try {
			command = Class.forName(CommandInstantiator.class.getPackageName() + "." + dir + "." + commandName).getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (CommandOutputLayer) command;
	}

	/**
	 * Scans the OutputLayer directory for your specified output for all command files and stores them in the commands ArrayList
	 * 
	 */
	private void populateCommandArr() {
		File file = new File("src/" + CommandInstantiator.class.getPackageName() + "/" + dir + "/");
		String[] list = file.list();
		for(String item : list) {
			String[] tmp = item.split(".java");
			item = tmp[0];
			commands.add(item);
		}
	}
	
	// TODO: Maybe pass in IO mode here?
	public CommandInstantiator() {
		populateCommandArr();
	}
}
