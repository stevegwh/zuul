package commandhandler.consoleCommandOutput;

import IO.IOHandler;
import commandhandler.CommandOutput;
import commandhandler.commandBase.DropCmd;

public class DropCmdOutput extends DropCmd implements CommandOutput {
	public void init(String[] args) {
		if(execute(args)) {
			IOHandler.output.println("You dropped " + toDrop);
		} else {
			IOHandler.output.println("You do not have a " + toDrop + " in your inventory");
		}
	}

}
