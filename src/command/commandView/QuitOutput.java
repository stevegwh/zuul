package command.commandView;

import IO.IOHandler;
import command.CommandOutput;
import command.commandController.QuitController;
import zuul.GameController;

public class QuitOutput extends QuitController implements CommandOutput {
	public void init(String[] inputArray) {
		String error = super.validateUserInput(inputArray);
		if (error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if (super.execute(inputArray)) {
			IOHandler.output.println("Thank you for playing. Good bye");
			GameController.quit();
		}
	}

}
