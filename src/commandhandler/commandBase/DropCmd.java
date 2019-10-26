package commandhandler.commandBase;

import commandhandler.Command;
import zuul.GameController;
import zuul.TakeableItem;

public class DropCmd extends Command {
	protected String toDrop;
	protected String validateUserInput(String[] inputArray) {
		toDrop = inputArray[1].toLowerCase();
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
