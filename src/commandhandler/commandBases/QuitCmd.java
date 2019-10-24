package commandhandler.commandBases;

import commandhandler.CommandBase;

public class QuitCmd implements CommandBase {
	@Override
	public boolean execute(String[] inputArray) {
		return true;
	}

	@Override
	public String validateUserInput(String[] inputArray) {
		// TODO Auto-generated method stub
		return null;
	}
}
