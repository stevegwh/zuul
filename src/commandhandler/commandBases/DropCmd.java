package commandhandler.commandBases;

import commandhandler.CommandBase;
import zuul.GameController;
import zuul.RoomController;
import zuul.TakeableItem;

public class DropCmd implements CommandBase {
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
		RoomController.addTakeableItem(item);
		return true;
	}

}
