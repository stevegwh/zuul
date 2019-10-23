package commandhandler.fullCommands;

import IO.IOHandler;
import commandhandler.FullCommand;
import commandhandler.commandBases.GiveCmd;

public class GiveCmdComplete extends GiveCmd implements FullCommand {
	
	public void init(String[] args) {
		String error = super.validateUserInput(args);
		if(error != null) {
			IOHandler.output.println(error);
			return;
		}
		if(super.execute(args)) {
			onSuccess();
		} else {
			onFail();
		}
	}

	public void onSuccess() {}
	public void onFail() {
		IOHandler.output.println(npc.getName() + " didn't seem to want the " + itemName);
	}
}
