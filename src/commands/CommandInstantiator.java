package commands;

import java.lang.reflect.InvocationTargetException;

public class CommandInstantiator {

	public static Command createInstance(String commandName) {
		Object command = null;
		try {
			command = Class.forName(CommandInstantiator.class.getPackageName() + "." + commandName + "Cmd").getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Command) command;
	}
}
