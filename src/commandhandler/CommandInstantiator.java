package commandhandler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import eventHandler.ZuulEventHandler;
import zuulutils.ZuulTools;

import java.io.File;

public class CommandInstantiator {
	private static ArrayList<String> commands = new ArrayList<>();
	

	/**
	 * Attempts to create an instance of the class that the user has specified as a command.
	 * Looks in the commands array to see if the command is valid.
	 * @param commandName The command the user has entered (after capitalized and having the Cmd suffix added).
	 * @return A Command object or Null.
	 */
	public static Command createInstance(String commandName) {
		commandName = ZuulTools.capitalize(commandName);
		commandName += "Cmd";
		if(commands.indexOf(commandName) < 0) {
			ZuulEventHandler.output.invalidCommand();
			return null;
		}
		Object command = null;
		try {
			//TODO: Hard coded path
			command = Class.forName(CommandInstantiator.class.getPackageName() + ".commands." + commandName).getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Command) command;
	}

	/**
	 * Scans the commands package for all command files and stores them in the commands ArrayList
	 * All commands should be capitalized correctly and have the suffix "Cmd". E.g. "GoCmd".
	 * All files specified in the ignoreList array above are ignored.
	 */
	private static void populateCommandArr() {
		//TODO: Hard coded path
		File file = new File("src/" + CommandInstantiator.class.getPackageName() + "/commands/");
		String[] list = file.list();
		for(String item : list) {
			String[] tmp = item.split(".java");
			item = tmp[0];
			commands.add(item);
		}
	}
	
	static {
		populateCommandArr();
	}
}
