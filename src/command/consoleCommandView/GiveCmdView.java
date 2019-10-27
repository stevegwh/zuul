package command.consoleCommandView;

import IO.IOHandler;
import command.CommandView;
import command.commandController.GiveCmd;

public class GiveCmdView extends GiveCmd implements CommandView {
	
	public void init(String[] inputArray) {
		String error = super.validateUserInput(inputArray);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(super.execute(inputArray)) {
			return;
		} else {
			IOHandler.output.println(npc.getName() + " didn't seem to want the " + itemName);
		}
	}
}
