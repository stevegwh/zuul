package commandhandler.commandBases;

import commandhandler.CommandBase;
import zuul.RoomController;

public class GoCmd implements CommandBase {
	protected String direction;
	protected String nextRoom;
	
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
	
	public boolean execute(String[] args) {
		direction = args[1].toLowerCase();
		nextRoom = RoomController.getExit(direction);
		if(nextRoom != null && isValidDirection(direction.toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}

	public GoCmd() {
		super();
	}
}