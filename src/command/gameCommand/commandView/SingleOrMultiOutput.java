package command.gameCommand.commandView;

import IO.IOHandler;
import command.CommandOutput;
import command.gameCommand.commandController.SingleOrMultiController;

public class SingleOrMultiOutput extends SingleOrMultiController implements CommandOutput {


	@Override
	public void init(String[] inputArray) {
		IOHandler.output.println("Please choose an option.");
		IOHandler.output.println("1. Singleplayer");
		IOHandler.output.println("2. Multiplayer");
		String[] choiceArr = IOHandler.input.getUserInput();
		String error = validateUserInput(choiceArr);
		if(error != null) {
			IOHandler.output.println(error);
			init(new String[] {});
		} else {
			execute(new String[] {});
		}
	}

}
