package command.commandController;

import command.CommandController;
import zuul.GameController;

public class GoController extends CommandController {
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
		if (!isValidDirection(direction.toUpperCase())) {
			return "Invalid Direction";
		}
		nextRoom = GameController.getRoomModel().getExit(direction, GameController.getCurrentPlayer().getLocation());
		if (nextRoom == null) {
			return "You can't go that way.";
		}
		return null;
	}

	public boolean execute(String[] inputArray) {
		GameController.getRoomModel().setNewRoom(nextRoom);
		GameController.getCurrentPlayer().setLocation(nextRoom);
		return true;
	}
}