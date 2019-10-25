package commandhandler.commandBase;

import commandhandler.Command;

public class QuitCmd extends Command {
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
