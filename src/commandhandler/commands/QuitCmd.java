package commandhandler.commands;

import commandhandler.Command;
import eventHandler.ZuulEventRouter;

public class QuitCmd implements Command {
	@Override
	public void execute(String[] inputArray) {
		ZuulEventRouter.output.quitGame();
	}
}
