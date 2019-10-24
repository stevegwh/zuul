package commandhandler.consoleOutputLayer;

import IO.IOHandler;
import commandhandler.CommandOutputLayer;
import commandhandler.commandLogic.QuitCmd;
import zuul.GameController;

public class QuitCmdOutput extends QuitCmd implements CommandOutputLayer {
	public void init(String[] args) {
		String error = super.validateUserInput(args);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(super.execute(args)) {
			onSuccess();
		} else {
			onFail();
		}
	}
	
	private void onSuccess() {
		IOHandler.output.println("Thanks for playing!");
		GameController.quit();
	}
	
	private void onFail() {
	}

}
