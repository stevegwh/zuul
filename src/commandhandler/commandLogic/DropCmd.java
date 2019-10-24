package commandhandler.commandLogic;

import commandhandler.CommandLogic;
import zuul.GameController;
import zuul.TakeableItem;

public class DropCmd implements CommandLogic {
	protected String toDrop;
	@Override
	public boolean execute(String[] args) {
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
}
