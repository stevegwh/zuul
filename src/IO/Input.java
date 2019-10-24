package IO;

public interface Input {
	/**
	 * Receives user input via the channel specified in the IOHandler class
	 * 
	 * @param MAX_COMMAND_LENGTH Maximum length of all commands in the game.
	 * @return The user's input as an array.
	 */
	public String[] getUserInput(); // TODO: the MAX_COMMAND_LENGTH couples the code with text commands only

}
