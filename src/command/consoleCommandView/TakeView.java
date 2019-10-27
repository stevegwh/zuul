package command.consoleCommandView;

import IO.IOHandler;
import command.CommandView;
import command.commandController.TakeController;

public class TakeView extends TakeController implements CommandView {
	public void init(String[] inputArray) {
		String error = super.validateUserInput(inputArray);
		if (error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if (super.execute(inputArray)) {
			IOHandler.output.println("You picked up " + toTake);
		}
	}
}
