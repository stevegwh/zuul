package command.consoleCommandView;

import IO.IOHandler;
import command.CommandView;
import command.commandController.QuitController;
import zuul.GameController;

public class QuitView extends QuitController implements CommandView {
	public void init(String[] inputArray) {
		String error = super.validateUserInput(inputArray);
		if (error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if (super.execute(inputArray)) {
			IOHandler.output.println("Thanks for playing!");
			GameController.quit();
		}
	}

}
