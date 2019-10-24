package commandhandler.commandLogic;

import commandhandler.CommandLogic;

public class QuitCmd implements CommandLogic {
	@Override
	public boolean execute(String[] inputArray) {
		return true;
	}

	@Override
	public String validateUserInput(String[] inputArray) {
		if(inputArray.length > 1) {
			return "Quit what?";
		}
		return null;
	}
}
