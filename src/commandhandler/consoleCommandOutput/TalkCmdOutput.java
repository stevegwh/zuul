package commandhandler.consoleCommandOutput;

import IO.IOHandler;
import commandhandler.CommandOutput;
import commandhandler.commandBase.TalkCmd;

public class TalkCmdOutput extends TalkCmd implements CommandOutput {
	public void init(String[] args) {
		if(super.execute(args)) {
			actor.onTalk();
		} else {
			IOHandler.output.println("Can't find " + toTalk);
		}
	}
}
