package command.commandView;

import IO.IOHandler;
import command.CommandOutput;
import command.commandController.DropController;

public class DropOutput extends DropController implements CommandOutput {
	public void init(String[] inputArray) {
		String error = validateUserInput(inputArray);
		if (error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if (execute(inputArray)) {
			IOHandler.output.println("You dropped " + toDrop);
		}
	}

}
