package commandhandler.consoleOutputLayer;

import IO.IOHandler;
import commandhandler.CommandOutputLayer;
import commandhandler.commandLogic.QuitCmd;
import zuul.GameController;

public class QuitCmdOutput extends QuitCmd implements CommandOutputLayer {
	public void init(String[] args) {
		if(super.execute(args)) {
			onSuccess();
		} else {
			onFail();
		}
	}
	
	public void onSuccess() {
		IOHandler.output.println("Thanks for playing!");
		GameController.quit();
	}
	
	public void onFail() {
	}

}
