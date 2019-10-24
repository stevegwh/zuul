package commandhandler.consoleOutputLayer;

import IO.IOHandler;
import commandhandler.CommandOutputLayer;
import commandhandler.commandLogic.HelpCmd;

// TODO: End list in full stop, not comma
public class HelpCmdOutput extends HelpCmd implements CommandOutputLayer {

	@Override
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
