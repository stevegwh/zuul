package commandhandler.consoleCommandOutput;

import IO.IOHandler;
import commandhandler.CommandOutput;
import commandhandler.commandBase.DropCmd;

public class DropCmdOutput extends DropCmd implements CommandOutput {
	public void init(String[] args) {
		String error = validateUserInput(args);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(execute(args)) {
			IOHandler.output.println("You dropped " + toDrop);
		}
	}

}
