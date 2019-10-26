package commandhandler.commandBase;

import commandhandler.Command;
import zuul.GameController;

public class GoCmd extends Command {
	protected String direction;
	protected String nextRoom;
	
	private enum Directions {
		NORTH, SOUTH, EAST, WEST
	}

	private boolean isValidDirection(String userInput) {
	    for (Directions c : Directions.values()) {
	        if (c.name().equals(userInput)) {
	            return true;
	        }
	    }
	    return false;
	}
	@Override
	public String validateUserInput(String[] inputArray) {
		direction = inputArray[1].toLowerCase();
		if(!isValidDirection(direction.toUpperCase())) {
			return "Invalid Direction";
		}
		nextRoom = GameController.getRoomController().getExit(direction);
		if(nextRoom == null) {
			return "You can't go that way.";
		}
		return null;
	}

	public boolean execute(String[] inputArray) {
		GameController.getRoomController().setNewRoom(nextRoom);
		GameController.getCurrentPlayer().setLocation(nextRoom);
		return true;
	}
}