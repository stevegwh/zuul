package commands;

import IO.OutputHandler;

public class HelpCmd implements Command{

	public HelpCmd() {
	}

	public void printCommands() {
	    for (CommandWords c : CommandWords.values()) {
	    	OutputHandler.println(c.name());
	    }
	}

	@Override
	public void execute(String[] inputArray) {
		OutputHandler.println("Valid commands are: ");
		printCommands();
	}
}
