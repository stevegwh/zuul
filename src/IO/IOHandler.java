package IO;

public class IOHandler {
	public static Output output = null;
	public static Input input = null;
	public static void setIOMode(Input inputClass, Output outputClass) {
		input = inputClass;
		output = outputClass;
	}
}
