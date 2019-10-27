package command.consoleCommandView;

import IO.IOHandler;
import command.CommandView;
import command.commandController.DropController;

public class DropView extends DropController implements CommandView {
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
