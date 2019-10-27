package command.consoleCommandView;

import IO.IOHandler;
import command.CommandView;
import command.commandController.GoCmd;

public class GoCmdView extends GoCmd implements CommandView {
	public void init(String[] inputArray) {
		String error = super.validateUserInput(inputArray);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		super.execute(inputArray);
	}
}
