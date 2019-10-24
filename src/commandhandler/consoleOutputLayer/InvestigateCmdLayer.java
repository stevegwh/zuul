package commandhandler.consoleOutputLayer;

import IO.IOHandler;
import commandhandler.CommandOutputLayer;
import commandhandler.commandBases.InvestigateCmd;

public class InvestigateCmdLayer extends InvestigateCmd implements CommandOutputLayer {
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
