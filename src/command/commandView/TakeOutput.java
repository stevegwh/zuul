package command.commandView;

import IO.IOHandler;
import command.CommandOutput;
import command.commandController.TakeController;

public class TakeOutput extends TakeController implements CommandOutput {
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
