package commandhandler.fullCommands;

import IO.IOHandler;
import commandhandler.FullCommand;
import commandhandler.commandBases.DropCmd;

public class DropCmdComplete extends DropCmd implements FullCommand {
	
	public void init(String[] args) {
		if(super.execute(args)) {
			onSuccess();
		} else {
			onFail();
		}
	}
	
	public void onSuccess() {
		IOHandler.output.println("You dropped " + toDrop);
		
	}
	
	public void onFail() {
		IOHandler.output.println("You do not have a " + toDrop + " in your inventory");
	}


}
