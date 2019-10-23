package commandhandler.fullCommands;

import IO.IOHandler;
import commandhandler.commandBases.QuitCmd;
import zuul.GameController;

public class QuitCmdComplete extends QuitCmd {
	public void init(String[] args) {
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
