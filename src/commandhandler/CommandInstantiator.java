package commandhandler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import IO.IOHandler;
//import eventHandler.ZuulEventRouter;
import zuulutils.ZuulTools;

import java.io.File;
public class CommandInstantiator {
	private ArrayList<String> commands = new ArrayList<>();
	// TODO: Make dynamic depending on IO mode
	private String dir = "consoleOutputLayer";
	private String fileSuffix = "CmdOutput";
	
	/**
	 * @return A list of the command names
	 */
	public List<String> getCommands() {
		return commands.stream()
				.map(e->e.replace(fileSuffix, ""))
				.collect(Collectors.toList());
	}
	
	private String buildFileName(String commandName) {
		commandName = ZuulTools.capitalize(commandName);
		commandName += fileSuffix;
		return commandName;
	}
	
	/**
	 * Checks the array of commands that populateArray() pushed for the command entered
	 * 
	 * @param commandName command requested
	 * @return
	 */
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
	 * Scans the OutputLayer directory and stores all file names in the commands ArrayList
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
