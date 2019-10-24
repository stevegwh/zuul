package commandhandler.consoleCommandOutput;

import IO.IOHandler;
import commandhandler.CommandOutput;
import commandhandler.commandBase.GiveCmd;

public class GiveCmdOutput extends GiveCmd implements CommandOutput {
	
	public void init(String[] args) {
		String error = super.validateUserInput(args);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(super.execute(args)) {
			return;
		} else {
			IOHandler.output.println(npc.getName() + " didn't seem to want the " + itemName);
		}
	}
}
