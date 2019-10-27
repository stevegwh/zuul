package command.commandController;

import command.CommandController;
import zuul.GameController;
import zuul.TakeableItem;

public class DropController extends CommandController {
	protected String toDrop;

	protected String validateUserInput(String[] inputArray) {
		if (inputArray.length < 2) {
			return "Drop what?";
		}
		toDrop = inputArray[1];
		if (!GameController.getCurrentPlayer().getInvModel().checkIfExists(toDrop)) {
			return "You do not have a " + toDrop + " in your inventory";
		}
		return null;
	}

	protected boolean execute(String[] inputArray) {
		TakeableItem item = GameController.getCurrentPlayer().getInvModel().getItem(toDrop);
		GameController.getCurrentPlayer().getInvModel().setWeight(-item.getWeight());
		GameController.getCurrentPlayer().getInvModel().removeItem(item);
		GameController.getRoomModel().addTakeableItem(item);
		return true;
	}
}
