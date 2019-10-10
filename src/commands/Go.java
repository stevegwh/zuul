package commands;

import zuul.Output;
import zuul.Room;

public class Go implements Command {

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
	
	public void execute(String[] args) {
		String direction = args[1];
		String nextRoom = Room.getExit(direction);
		if(nextRoom != null && isValidDirection(direction.toUpperCase())) {
			Room.getNewRoom(nextRoom);
		} else {
			Output.println("Can't go that way");
		}
	}

	public Go() {
		super();
	}
}