package commandhandler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

//import eventHandler.ZuulEventRouter;
import zuulutils.ZuulTools;

import java.io.File;

public class CommandInstantiator {
	private static ArrayList<String> commands = new ArrayList<>();
	

	/**
	 * Attempts to create an instance of the class that the user has specified as a command.
	 * 
	 * @param commandName The command the user has entered
	 * @return A CommandBase object or Null.
	 */
	public static CommandBase createInstance(String commandName) {
		commandName = ZuulTools.capitalize(commandName);
		commandName += "CmdComplete";
		if(commands.indexOf(commandName) < 0) {
			// TODO: Replace this
//			ZuulEventRouter.output.invalidCommand();
			return null;
		}
		Object command = null;
		try {
			//TODO: Hard coded path
			command = Class.forName(CommandInstantiator.class.getPackageName() + ".fullCommands." + commandName).getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (CommandBase) command;
	}

	/**
	 * Scans the commandhandler.commandBases package for all command files and stores them in the commands ArrayList
	 * All commands should be capitalized correctly and have the suffix "Cmd". E.g. "GoCmd".
	 * 
	 */
	private static void populateCommandArr() {
		//TODO: Hard coded path
		File file = new File("src/" + CommandInstantiator.class.getPackageName() + "/fullCommands/");
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
