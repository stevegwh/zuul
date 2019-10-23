package commandhandler.fullCommands;

import IO.IOHandler;
import commandhandler.FullCommand;
import commandhandler.commandBases.TalkCmd;

public class TalkCmdComplete extends TalkCmd implements FullCommand {
	public void init(String[] args) {
		if(super.execute(args)) {
			onSuccess();
		} else {
			onFail();
		}
	}
	public void onSuccess() {
		actor.onTalk();
	}
	public void onFail() {
		IOHandler.output.println("Can't find " + toTalk);
	}
}
