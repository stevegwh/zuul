package commandhandler.commands;

import commandhandler.Command;
import zuul.GameController;
// TODO: Event being resolved outside of event handler 
public class QuitCmd implements Command {
	@Override
	public void execute(String[] inputArray) {
		GameController.quit();
	}
}
