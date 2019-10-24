package commandhandler.consoleOutputLayer;

import IO.IOHandler;
import commandhandler.CommandInstantiator;
import commandhandler.CommandOutputLayer;
import commandhandler.commandLogic.GoCmd;

public class GoCmdOutput extends GoCmd implements CommandOutputLayer {
	public void init(String[] args) {
		String error = super.validateUserInput(args);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(super.execute(args)) {
			onSuccess();
		} else {
			onFail();
		}
	}
	// TODO: Instantiate this elsewhere...
	private void onSuccess() {
		CommandInstantiator instantiator = new CommandInstantiator();
		CommandOutputLayer look = instantiator.createInstance("look");
		String[] args = {};
		((CommandOutputLayer) look).init(args);
	}
	private void onFail() {
		IOHandler.output.println("You can't go that way.");
//		printSeperator();
	}
}
