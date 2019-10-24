package commandhandler.consoleOutputLayer;

import IO.IOHandler;
import commandhandler.CommandOutputLayer;
import commandhandler.commandLogic.LookCmd;
import zuulutils.ZuulTools;

public class LookCmdOutput extends LookCmd implements CommandOutputLayer {
	public void init(String[] args) {
		if(super.execute(args)) {
			onSuccess();
		}
	}

	// TODO: Finish list in full stop not comma
	private void onSuccess() {
		IOHandler.output.println(description);
		IOHandler.output.printf("People in room: ");
		actors.forEach((e) -> IOHandler.output.printf((String) e + ", "));
		IOHandler.output.println(" ");
		if(items != null) {
			IOHandler.output.printf("Items in room: ");
			items.forEach(e -> IOHandler.output.printf(ZuulTools.capitalize(e) + ", "));
		} else {
			IOHandler.output.printf("No items in room");
		}
		IOHandler.output.println(" ");
		IOHandler.output.printf("Exits: ");
		exits.forEach((k, v) -> IOHandler.output.printf(ZuulTools.capitalize((String) k) + ", "));
		IOHandler.output.println(" ");
	}
	

}
