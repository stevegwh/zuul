package IO;

import java.lang.reflect.InvocationTargetException;

public class IOHandler {
	public static Output output = null;
	public static Input input = null;
	/**
	 * Sets input/output to the mode specified. If "console" is passed in then this class will attempt to assign the input/output variables to
	 * a class called ConsoleInput and ConsoleOutput. If "gui" is passed in then this class will attempt to find "GuiInput" and "GuiOutput" in the package etc.
	 * 
	 * @param mode the name of the I/O mode. e.g. console, gui etc.
	 */
	public static void setIOMode(String mode) {
		try {
			input = (Input) Class.forName(IOHandler.class.getPackageName() + "." + mode + "Input").getConstructor().newInstance();
			output = (Output) Class.forName(IOHandler.class.getPackageName() + "." + mode + "Output").getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
