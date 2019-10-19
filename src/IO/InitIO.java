package IO;

import java.lang.reflect.InvocationTargetException;

public class InitIO {
	public static void setMode(String mode) {
		Input input = null;
		Output output = null;
		try {
			input = (Input) Class.forName(InitIO.class.getPackageName() + "." + mode + "Input").getConstructor().newInstance();
			output = (Output) Class.forName(InitIO.class.getPackageName() + "." + mode + "Output").getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IOHandler.setIOMode(input, output);
	}
	static {
		
	}
}
