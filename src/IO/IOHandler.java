package IO;

public class IOHandler {
	protected static Output output = null;
	protected static Input input = null;
	public static void setIOMode(Input inputClass, Output outputClass) {
		input = inputClass;
		output = outputClass;
	}
}
