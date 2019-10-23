package commandhandler.fullCommands;

import IO.IOHandler;
import commandhandler.FullCommand;
import commandhandler.commandBases.InvestigateCmd;

public class InvestigateCmdComplete extends InvestigateCmd implements FullCommand {
	public void init(String[] args) {
		if(super.execute(args)) {
			onSuccess();
		} else {
			onFail();
		}
	}
	
	public void onSuccess() {
		item.onInvestigate();
	}
	
	public void onFail() {
		IOHandler.output.println("Can't find " + toInvestigate);
	}

}
