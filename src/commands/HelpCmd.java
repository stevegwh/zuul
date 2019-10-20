package commands;

import IO.IOHandler;

public class HelpCmd implements Command{

	@Override
	public void execute(String[] inputArray) {
		IOHandler.output.println("Valid commands are: ");
		CommandWords.printAll();
	}
}
