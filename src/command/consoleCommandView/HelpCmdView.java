package command.consoleCommandView;

import IO.IOHandler;
import command.CommandView;
import command.commandController.HelpCmd;

public class HelpCmdView extends HelpCmd implements CommandView {
	public void init(String[] inputArray) {
		String error = super.validateUserInput(inputArray);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(super.execute(inputArray)) {
			IOHandler.output.println("You are lost. You are alone. You wander around at the university");
			IOHandler.output.println(" ");
			IOHandler.output.println("Your command words are: ");
			String end = commands.get(commands.size() - 1);
			commands.remove(end);
			commands.forEach(e->IOHandler.output.printf(e + ", "));
			IOHandler.output.printf(end + ".");
			IOHandler.output.println(" ");
		}
	}
}
