package commandhandler.consoleOutputLayer;

import IO.IOHandler;
import commandhandler.CommandOutputLayer;
import commandhandler.commandBases.HelpCmd;

public class HelpCmdLayer extends HelpCmd implements CommandOutputLayer {

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
