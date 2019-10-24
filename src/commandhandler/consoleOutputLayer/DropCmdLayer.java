package commandhandler.consoleOutputLayer;

import IO.IOHandler;
import commandhandler.CommandOutputLayer;
import commandhandler.commandBases.DropCmd;

public class DropCmdLayer extends DropCmd implements CommandOutputLayer {
	
	public void init(String[] args) {
		if(super.execute(args)) {
			onSuccess();
		} else {
			onFail();
		}
	}
	
	private void onSuccess() {
		IOHandler.output.println("You dropped " + toDrop);
		
	}
	
	private void onFail() {
		IOHandler.output.println("You do not have a " + toDrop + " in your inventory");
	}


}
