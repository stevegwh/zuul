package commands;

import zuul.Output;
import zuul.RoomController;

public class LookCmd implements Command {
	
	public void execute(String[] args) {
		String lookDescription = RoomController.getLookDescription();
		if (lookDescription != null) {
			Output.println(lookDescription);
		} else {
			Output.println("Nothing interesting to report");
		}
	}

	public LookCmd() {
		super();
	}
}
