package commands;

import IO.OutputHandler;
import zuul.RoomController;

public class LookCmd implements Command {
	
	public void execute(String[] args) {
		String lookDescription = RoomController.getLookDescription();
		if (lookDescription != null) {
			OutputHandler.println(lookDescription);
		} else {
			OutputHandler.println("Nothing interesting to report");
		}
	}

	public LookCmd() {
		super();
	}
}
