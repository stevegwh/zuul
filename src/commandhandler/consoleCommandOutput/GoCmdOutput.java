package commandhandler.consoleCommandOutput;

import IO.IOHandler;
import commandhandler.CommandInstantiator;
import commandhandler.CommandOutput;
import commandhandler.commandBase.GoCmd;

public class GoCmdOutput extends GoCmd implements CommandOutput {
	public void init(String[] args) {
		String error = super.validateUserInput(args);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(super.execute(args)) {
			CommandInstantiator instantiator = new CommandInstantiator();
			CommandOutput look = instantiator.createInstance("look");
			String[] argz = {};
			((CommandOutput) look).init(argz);
		} else {
			IOHandler.output.println("You can't go that way.");
		}
	}
}
