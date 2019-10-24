package commandhandler.consoleCommandOutput;

import IO.IOHandler;
import commandhandler.CommandOutput;
import commandhandler.commandBase.HelpCmd;

// TODO: End list in full stop, not comma
public class HelpCmdOutput extends HelpCmd implements CommandOutput {
	public void init(String[] args) {
		if(super.execute(args)) {
			IOHandler.output.println("You are lost. You are alone. You wander around at the university");
			IOHandler.output.println(" ");
			IOHandler.output.println("Your command words are: ");
			commands.forEach(e->IOHandler.output.printf(e + ", "));
			IOHandler.output.println(" ");
		}
	}
}
