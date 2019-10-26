package commandhandler.consoleCommandOutput;

import IO.IOHandler;
import commandhandler.CommandOutput;
import commandhandler.commandBase.GoCmd;

public class GoCmdOutput extends GoCmd implements CommandOutput {
	public void init(String[] inputArray) {
		String error = super.validateUserInput(inputArray);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(super.execute(inputArray)) {
//			GameController.callCommand("look");
		}
	}
}
