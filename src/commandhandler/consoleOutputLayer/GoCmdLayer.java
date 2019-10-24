package commandhandler.consoleOutputLayer;

import IO.IOHandler;
import commandhandler.CommandOutputLayer;
import commandhandler.commandBases.GoCmd;
import zuul.RoomController;

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
	public void onSuccess() {
		IOHandler.output.println("You go " + direction);
		RoomController.getNewRoom(nextRoom);
	}
	public void onFail() {
		IOHandler.output.println("You can't go that way.");
//		printSeperator();
	}
}
