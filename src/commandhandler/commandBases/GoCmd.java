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
		nextRoom = RoomController.getExit(direction);
		if(nextRoom != null) {
			return true;
		} else {
			return false;
		}
	}

	public GoCmd() {
		super();
	}

	@Override
	public String validateUserInput(String[] inputArray) {
		direction = inputArray[1].toLowerCase();
		if(!isValidDirection(direction.toUpperCase())) {
			return "Invalid Direction";
		}
		return null;
	}
}