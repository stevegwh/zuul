package commandhandler.commandBase;

import java.util.ArrayList;
import java.util.List;

import commandhandler.Command;
import commandhandler.CommandInstantiator;

public abstract class HelpCmd extends Command {
	protected List<String> commands = new ArrayList<>();

	@Override
	public boolean execute(String[] inputArray) {
		CommandInstantiator instantiator = new CommandInstantiator();
		commands = instantiator.getCommands();
		return true;
	}
	@Override
	protected String validateUserInput(String[] inputArray) {
		// TODO Auto-generated method stub
		return null;
	}
}
