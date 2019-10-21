package IO;

import java.lang.reflect.InvocationTargetException;

/**
 * Class that routes I/O in the game. Defaults to 'Console' mode (System.out/in). Assigns the input/output variables to the
 * specified implementation of Input/Output found in this package.
 * I/O route is specified by using the '--io' flag while executing the game. E.g. --io console or --io gui.
 * 
 * @author Steve
 *
 */
public class IOHandler {
	public static Output output = null;
	public static Input input = null;
	/**
	 * Sets input/output to the mode specified. If "console" is passed in then this class will attempt to assign the input/output variables to
	 * a class called ConsoleInput and ConsoleOutput. If "gui" is passed in then this class will attempt to find "GuiInput" and "GuiOutput" in the package etc.
	 * All I/O in the game is routed to the implementation assigned to IOHandler.input and IOHandler.output.
	 * 
	 * @param mode the name of the Input/Output implementation to request. e.g. console, gui etc.
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
