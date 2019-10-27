package command.commandController;

import command.CommandController;
import zuul.GameController;
import zuul.TakeableItem;

public class DropCmd extends CommandController {
	protected String toDrop;
	protected String validateUserInput(String[] inputArray) {
		if(inputArray.length < 2) {
			return "Drop what?";
		}
		toDrop = inputArray[1];
		if(!GameController.getCurrentPlayer().getInvController().checkIfExists(toDrop)) {
			return "You do not have a " + toDrop + " in your inventory";
		}
		return null;
	}
	protected boolean execute(String[] inputArray) {
		TakeableItem item = GameController.getCurrentPlayer().getInvController().getItem(toDrop);
		GameController.getCurrentPlayer().getInvController().setWeight(-item.getWeight());
		GameController.getCurrentPlayer().getInvController().removeItem(item);
		GameController.getRoomController().addTakeableItem(item);
		return true;
	}
}