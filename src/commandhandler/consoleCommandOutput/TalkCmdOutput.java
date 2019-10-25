package commandhandler.consoleCommandOutput;

import IO.IOHandler;
import commandhandler.CommandOutput;
import commandhandler.commandBase.TalkCmd;

// TODO: Validate the user input in the appropriate method
public class TalkCmdOutput extends TalkCmd implements CommandOutput {
	public void init(String[] args) {
		String error = validateUserInput(args);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(super.execute(args)) {
			actor.onTalk();
		}
	}
}
