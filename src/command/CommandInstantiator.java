package command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import IO.IOHandler;
import zuul.GameController;
//import eventHandler.ZuulEventRouter;
import zuulutils.ZuulTools;

import java.io.File;

public class CommandInstantiator {
	private ArrayList<String> commands = new ArrayList<>();
	private String dir = "CommandView";
	private final String fileSuffix = "View";

	/**
	 * @return A list of the command names
	 */
	public List<String> getCommands() {
		return commands.stream().map(e -> e.replace(fileSuffix, "")).collect(Collectors.toList());
	}

	private String buildFileName(String commandName) {
		if (commandName.isEmpty()) {
			return null;
		}
		commandName = ZuulTools.capitalize(commandName);
		commandName += fileSuffix;
		return commandName;
	}

	/**
	 * Checks the array of commands that populateArray() pushed for the command
	 * entered
	 * 
	 * @param commandName command requested
	 * @return
	 */
	private boolean checkCommandValidity(String commandName) {
		return commands.indexOf(commandName) >= 0;
	}

	/**
	 * Attempts to create an instance of the class passed in as parameter.
	 * 
	 * @param commandName The command to be instantiated.
	 * @return A CommandView object or null.
	 */
	public CommandView createInstance(String commandName) {
		commandName = buildFileName(commandName);
		if (!checkCommandValidity(commandName)) {
			IOHandler.output.printError("Invalid CommandController");
			return null;
		}
		Object command = null;
		try {
			command = Class.forName(CommandInstantiator.class.getPackageName() + "." + dir + "." + commandName)
					.getConstructor().newInstance();
			return (CommandView) command;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | java.lang.ClassNotFoundException
				| java.lang.ClassCastException e) {
			System.err.println("Cannot instantiate " + commandName + " as a command.");
			System.err.println(
					"Please check " + commandName + " for errors and that it implements the correct interface");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Scans the OutputLayer directory and stores all file names in the commands
	 * ArrayList
	 */
	private void populateCommandArr() {
		dir = GameController.getIoMode() + dir;
		File file = new File("src/" + CommandInstantiator.class.getPackageName() + "/" + dir + "/");
		String[] list = file.list();
		for (String item : list) {
			String[] tmp = item.split(".java");
			item = tmp[0];
			commands.add(item);
		}
	}

	public CommandInstantiator() {
		populateCommandArr();
	}
}
