package commandhandler.commandOutputLayers;

import IO.IOHandler;
import commandhandler.CommandOutputLayer;
import commandhandler.commandBases.QuitCmd;
import zuul.GameController;

public class QuitCmdLayer extends QuitCmd implements CommandOutputLayer {
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
