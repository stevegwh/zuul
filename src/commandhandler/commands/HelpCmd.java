package commandhandler.commands;

import IO.IOHandler;
import commandhandler.Command;

public class HelpCmd implements Command{

	@Override
	public void execute(String[] inputArray) {
		IOHandler.output.println("Valid commands are: ");
//		CommandWords.printAll();
	}
}
