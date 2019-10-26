package commandhandler.consoleCommandOutput;

import IO.IOHandler;
import commandhandler.CommandOutput;
import commandhandler.commandBase.DropCmd;

public class DropCmdOutput extends DropCmd implements CommandOutput {
	public void init(String[] inputArray) {
		String error = validateUserInput(inputArray);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(execute(inputArray)) {
			IOHandler.output.println("You dropped " + toDrop);
		}
	}

}
