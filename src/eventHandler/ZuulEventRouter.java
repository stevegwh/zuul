package eventHandler;

import java.lang.reflect.InvocationTargetException;

/**
 * Routes all the game's events to the necessary implementation of the IEventHandler interface.
 * @author Steve
 *
 */
public class ZuulEventRouter {
	public static IEventHandler output = null;
	/**
	 * Sets output to the mode specified. If "console" is passed in then this class will attempt to assign the output variable to
	 * a class called ConsoleEventHandler. If "gui" is passed in then this class will attempt to find "GuiEventHandler" in the package etc.
	 * 
	 * @param mode the name of the IEventHandler implementation to request. e.g. console, gui etc.
	 */
	public static void setIOMode(String mode) {
		try {
			// TODO: hard coded path
			output = (IEventHandler) Class.forName(ZuulEventRouter.class.getPackageName() + "." + mode + "EventHandler").getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}