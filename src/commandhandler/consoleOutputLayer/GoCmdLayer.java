package commandhandler.consoleOutputLayer;

import IO.IOHandler;
import commandhandler.CommandOutputLayer;
import commandhandler.commandBases.GoCmd;
import zuul.GameController;

public class GoCmdLayer extends GoCmd implements CommandOutputLayer {
	public void init(String[] args) {
		String error = super.validateUserInput(args);
		if(error != null) {
			IOHandler.output.println(error);
			return;
		}
		if(super.execute(args)) {
			onSuccess();
		} else {
			onFail();
		}
	}
	// TODO: Game logic outside of base 
	public void onSuccess() {
		IOHandler.output.println("You go " + direction);
		IOHandler.output.println(GameController.getRoomController().getDescription());
	}
	public void onFail() {
		IOHandler.output.println("You can't go that way.");
//		printSeperator();
	}
}
