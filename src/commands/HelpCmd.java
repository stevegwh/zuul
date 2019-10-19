package commands;

import IO.OutputHandler;

public class HelpCmd implements Command{

	@Override
	public void execute(String[] inputArray) {
		OutputHandler.println("Valid commands are: ");
		CommandWords.printAll();
	}
}
