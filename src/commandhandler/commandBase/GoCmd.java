package commandhandler.commandBase;

import commandhandler.Command;
import zuul.GameController;

public abstract class GoCmd extends Command {
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
	
	public boolean execute(String[] args) {
		nextRoom = GameController.getRoomController().getExit(direction);
		if(nextRoom != null) {
			GameController.getRoomController().setNewRoom(nextRoom);
			GameController.getCurrentPlayer().setLocation(nextRoom);
			return true;
		} else {
			return false;
		}
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