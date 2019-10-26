package commandhandler.consoleCommandOutput;

import IO.IOHandler;
import commandhandler.CommandOutput;
import commandhandler.commandBase.QuitCmd;
import zuul.GameController;

public class QuitCmdOutput extends QuitCmd implements CommandOutput {
	public void init(String[] inputArray) {
		String error = super.validateUserInput(inputArray);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(super.execute(inputArray)) {
			IOHandler.output.println("Thanks for playing!");
			GameController.quit();
		} else {
		}
	}

}
