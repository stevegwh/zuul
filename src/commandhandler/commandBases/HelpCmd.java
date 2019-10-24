package commandhandler.commandBases;

import java.util.ArrayList;
import java.util.List;

import commandhandler.CommandBase;
import commandhandler.CommandInstantiator;

public class HelpCmd implements CommandBase{
	protected List<String> commands = new ArrayList<>();

	@Override
	public boolean execute(String[] inputArray) {
		CommandInstantiator instantiator = new CommandInstantiator();
		commands = instantiator.getCommands();
		return true;
	}
}
