package IO;

public class InputHandler extends IOHandler {
	// TODO: rethink the MAX_COMMAND_LENGTH
	public String[] getInput(int MAX_COMMAND_LENGTH) { 
		return input.getUserInput(MAX_COMMAND_LENGTH);
	}
}
