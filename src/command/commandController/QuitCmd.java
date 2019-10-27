package command.commandController;

import command.CommandController;

public class QuitCmd extends CommandController {
	@Override
	public boolean execute(String[] inputArray) {
		return true;
	}

	@Override
	protected String validateUserInput(String[] inputArray) {
		if(inputArray.length > 1) {
			return "Quit what?";
		}
		return null;
	}
}
