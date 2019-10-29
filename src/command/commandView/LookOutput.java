package command.commandView;

import IO.IOHandler;
import command.ICommandOutput;
import command.commandController.LookController;

public class LookOutput extends LookController implements ICommandOutput {
	public void init(String[] inputArray) {
		String error = validateUserInput(inputArray);
		if (error != null) {
			IOHandler.output.printError(error);
			return;
		}
		/*
		 * execute() called by GameController at the start of each game loop with
		 * command.game.eventOutput.LookOutput. To avoid outputting LookOutput
		 * twice when the user inputs 'look' this class simply returns and allows the
		 * beginning of the game loop to call 'LookOutput' instead.
		 * 
		 */
	}

}
