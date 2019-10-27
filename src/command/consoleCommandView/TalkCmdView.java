package command.consoleCommandView;

import IO.IOHandler;
import command.CommandView;
import command.commandController.TalkCmd;

public class TalkCmdView extends TalkCmd implements CommandView {
	public void init(String[] inputArray) {
		String error = validateUserInput(inputArray);
		if(error != null) {
			IOHandler.output.printError(error);
			return;
		}
		if(super.execute(inputArray)) {
			actor.onTalk();
		}
	}
}
