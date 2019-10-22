package commandhandler.commands;

import IO.IOHandler;
import commandhandler.Command;
import eventHandler.ZuulEventHandler;
import zuul.RoomController;

public class GoCmd implements Command {
	
	public enum Directions {
		NORTH, SOUTH, EAST, WEST
	}

	public boolean isValidDirection(String userInput) {
	    for (Directions c : Directions.values()) {
	        if (c.name().equals(userInput)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	// TODO: Move text to eventHandler
	public void execute(String[] args) {
		String direction = args[1].toLowerCase();
		String nextRoom = RoomController.getExit(direction);
		if(nextRoom != null && isValidDirection(direction.toUpperCase())) {
			ZuulEventHandler.output.onGo(direction);
			RoomController.getNewRoom(nextRoom);
		} else {
			ZuulEventHandler.output.onGoFail();
		}
	}

	public GoCmd() {
		super();
	}
}