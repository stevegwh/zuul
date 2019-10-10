package commands;

import zuul.Output;
import zuul.Room;

public class Look implements Command {
	
	public void execute(String[] args) {
		String lookDescription = Room.getLookDescription();
		if (lookDescription != null) {
			Output.println(lookDescription);
		} else {
			Output.println("Nothing interesting to report");
		}
	}

	public Look() {
		super();
	}
}
