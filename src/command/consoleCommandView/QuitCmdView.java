package command.consoleCommandView;

import IO.IOHandler;
import command.CommandView;
import command.commandController.QuitCmd;
import zuul.GameController;

public class QuitCmdView extends QuitCmd implements CommandView {
	public void init(String[] inputArray) {
		String error = super.validateUserInput(inputArray);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(super.execute(inputArray)) {
			IOHandler.output.println("Thanks for playing!");
			GameController.quit();
		}
	}

}
