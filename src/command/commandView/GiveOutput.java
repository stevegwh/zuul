package command.commandView;

import IO.IOHandler;
import command.CommandOutput;
import command.commandController.GiveController;

public class GiveOutput extends GiveController implements CommandOutput {

	public void init(String[] inputArray) {
		String error = super.validateUserInput(inputArray);
		if (error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if (super.execute(inputArray)) {
			return;
		} else {
			IOHandler.output.println(npc.getName() + " didn't seem to want the " + itemName);
		}
	}
}
