package commandhandler.fullCommands;

import IO.IOHandler;
import commandhandler.FullCommand;
import commandhandler.commandBases.HelpCmd;

public class HelpCmdComplete extends HelpCmd implements FullCommand {

	public void init(String[] args) {
		IOHandler.output.println("Valid commands are: ");
		// TODO: implement
	}

	@Override
	public void onSuccess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFail() {
		// TODO Auto-generated method stub
		
	}

}
