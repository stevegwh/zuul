package commandhandler.commandBase;

import commandhandler.Command;
import zuul.GameController;
import zuul.TakeableItem;

public abstract class DropCmd extends Command {
	protected String toDrop;
	@Override
	protected boolean execute(String[] args) {
		toDrop = args[1];
		if(!GameController.getCurrentPlayer().getInvController().checkIfExists(toDrop)) {
			return false;
		}
		TakeableItem item = GameController.getCurrentPlayer().getInvController().getItem(toDrop);
		GameController.getCurrentPlayer().getInvController().setWeight(-item.getWeight());
		GameController.getCurrentPlayer().getInvController().removeItem(item);
		GameController.getRoomController().addTakeableItem(item);
		return true;
	}
	@Override
	protected String validateUserInput(String[] inputArray) {
		// TODO Auto-generated method stub
		return null;
	}
}
