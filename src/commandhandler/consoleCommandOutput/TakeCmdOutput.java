package commandhandler.consoleCommandOutput;

import IO.IOHandler;
import commandhandler.CommandOutput;
import commandhandler.commandBase.TakeCmd;

public class TakeCmdOutput extends TakeCmd implements CommandOutput {
	public void init(String[] args) {
		String error = super.validateUserInput(args);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(super.execute(args)) {
			IOHandler.output.println("You picked up " + toTake);
		} else {
			IOHandler.output.println("Sorry, this item is too heavy for you to carry. Try dropping something first");
		}
	}
}
