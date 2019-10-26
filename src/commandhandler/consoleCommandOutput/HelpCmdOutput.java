package commandhandler.consoleCommandOutput;

import IO.IOHandler;
import commandhandler.CommandOutput;
import commandhandler.commandBase.HelpCmd;

// TODO: End list in full stop, not comma
public class HelpCmdOutput extends HelpCmd implements CommandOutput {
	public void init(String[] inputArray) {
		String error = super.validateUserInput(inputArray);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(super.execute(inputArray)) {
			IOHandler.output.println("You are lost. You are alone. You wander around at the university");
			IOHandler.output.println(" ");
			IOHandler.output.println("Your command words are: ");
			commands.forEach(e->IOHandler.output.printf(e + ", "));
			IOHandler.output.println(" ");
		}
	}
}
