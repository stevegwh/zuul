package command.gameCommand.commandView;

import IO.IOHandler;
import command.CommandOutput;

public class GameStartOutput implements CommandOutput {

	@Override
	public void init(String[] inputArray) {
        IOHandler.output.println("");
        IOHandler.output.println("Welcome to the World of Zuul!");
        IOHandler.output.println("World of Zuul is a new, incredibly boring adventure game.");
        IOHandler.output.println("Type 'help' if you need help.");
        IOHandler.output.println("");
	}

}
