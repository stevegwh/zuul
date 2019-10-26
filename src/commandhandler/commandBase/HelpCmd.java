package commandhandler.commandBase;

import java.util.ArrayList;
import java.util.List;

import commandhandler.Command;
import commandhandler.CommandInstantiator;

public class HelpCmd extends Command {
	private int COMMAND_LENGTH = 1;
	protected List<String> commands = new ArrayList<>();

	@Override
	public boolean execute(String[] inputArray) {
		CommandInstantiator instantiator = new CommandInstantiator();
		commands = instantiator.getCommands();
		return true;
	}
	@Override
	protected String validateUserInput(String[] inputArray) {
		if(inputArray.length > COMMAND_LENGTH) {
			return "To get help please just type 'help'";
		}
		return null;
	}
}
