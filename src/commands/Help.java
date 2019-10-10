package commands;

import zuul.Output;

public class Help implements Command{

	public Help() {
	}

	public void printCommands() {
	    for (CommandWords c : CommandWords.values()) {
	    	Output.println(c.name());
	    }
	}

	@Override
	public void execute(String[] inputArray) {
		Output.println("Valid commands are: ");
		printCommands();
	}
}
