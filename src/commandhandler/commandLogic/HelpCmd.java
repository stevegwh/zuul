package commandhandler.commandLogic;

import java.util.ArrayList;
import java.util.List;

import commandhandler.CommandLogic;
import commandhandler.CommandInstantiator;

public class HelpCmd implements CommandLogic{
	protected List<String> commands = new ArrayList<>();

	@Override
	public boolean execute(String[] inputArray) {
		CommandInstantiator instantiator = new CommandInstantiator();
		commands = instantiator.getCommands();
		return true;
	}
}
