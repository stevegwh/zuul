package commandhandler.commandBases;

import commandhandler.CommandBase;

public class HelpCmd implements CommandBase{

	@Override
	public boolean execute(String[] inputArray) {
		return true;
//		CommandWords.printAll();
	}
}
