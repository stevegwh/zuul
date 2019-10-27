package command.commandView;

import IO.IOHandler;
import command.CommandOutput;
import command.commandController.GoController;

public class GoOutput extends GoController implements CommandOutput {
	public void init(String[] inputArray) {
		String error = super.validateUserInput(inputArray);
		if (error != null) {
			IOHandler.output.printError(error);
			return;
		}
		super.execute(inputArray);
	}
}
