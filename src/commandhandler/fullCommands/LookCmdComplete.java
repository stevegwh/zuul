package commandhandler.fullCommands;

import IO.IOHandler;
import commandhandler.FullCommand;
import commandhandler.commandBases.LookCmd;
import zuulutils.ZuulTools;

public class LookCmdComplete extends LookCmd implements FullCommand {
	public void init(String[] args) {
		if(super.execute(args)) {
			onSuccess();
		} else {
			onFail();
		}
	}
	private void printSeperator() {
		IOHandler.output.println("=============");
	}

	public void onSuccess() {
		if (lookDescription != null) {
			IOHandler.output.println(lookDescription);
		} else {
			IOHandler.output.println("Nothing interesting to report");
		}
		printSeperator();
		IOHandler.output.println("People in room: ");
		actors.forEach((e) -> IOHandler.output.println((String) e));
		printSeperator();
		if(items != null) {
			IOHandler.output.println("Items in room: ");
			items.forEach(e -> IOHandler.output.println(ZuulTools.capitalize(e)));
		} else {
			IOHandler.output.println("No items in room");
		}
		printSeperator();
		IOHandler.output.println("Exits: ");
		exits.forEach((k, v) -> IOHandler.output.println(ZuulTools.capitalize((String) k) + ": " + v));
		printSeperator();
	}
	
	public void onFail() {}

}
