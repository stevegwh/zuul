package command.commandView;

import IO.IOHandler;
import command.ICommandOutput;
import command.commandController.GiveController;

public class GiveOutput extends GiveController implements ICommandOutput {

	@Override
	public void init(String[] inputArray) {
		String error = super.validateUserInput(inputArray);
		if (error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if (!super.execute(inputArray)) {
			IOHandler.output.printError(npc.getName() + " didn't seem to want the " + itemName);
		}
	}
}
