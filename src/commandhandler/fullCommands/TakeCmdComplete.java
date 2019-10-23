package commandhandler.fullCommands;

import IO.IOHandler;
import commandhandler.FullCommand;
import commandhandler.commandBases.TakeCmd;

public class TakeCmdComplete extends TakeCmd implements FullCommand {
	public void init(String[] args) {
		if(super.execute(args)) {
			onSuccess();
		} else {
			onFail();
		}
	}
	
	public void onSuccess() {
		IOHandler.output.println("You picked up " + toTake);
	}

	public void onFail() {
		IOHandler.output.println("Sorry, this item is too heavy for you to carry. Try dropping something first");
	}


}
