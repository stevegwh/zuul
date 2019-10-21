package commandhandler.commands;

import commandhandler.Command;
import zuul.GameController;

public class QuitCmd implements Command {
	@Override
	public void execute(String[] inputArray) {
		GameController.quit();
	}
}
