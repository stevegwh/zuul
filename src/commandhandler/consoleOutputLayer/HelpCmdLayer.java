package commandhandler.consoleOutputLayer;

import IO.IOHandler;
import commandhandler.CommandOutputLayer;
import commandhandler.commandBases.HelpCmd;

public class HelpCmdLayer extends HelpCmd implements CommandOutputLayer {

	@Override
	public void init(String[] args) {
		if(super.execute(args)) {
			IOHandler.output.println("Valid commands are: ");
			commands.forEach(e->IOHandler.output.println(e));
		}
	}

}
