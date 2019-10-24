package commandhandler.consoleOutputLayer;

import IO.IOHandler;
import commandhandler.CommandOutputLayer;
import commandhandler.commandLogic.TalkCmd;

public class TalkCmdOutput extends TalkCmd implements CommandOutputLayer {
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
