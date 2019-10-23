package commandhandler.commands;

import commandhandler.Command;
import eventHandler.ZuulEventRouter;
import zuul.GameController;
import zuul.RoomController;
import zuul.TakeableItem;

public class DropCmd implements Command {
	@Override
	public void execute(String[] args) {
		String toDrop = args[1];
		if(!GameController.getCurrentPlayer().getInvController().checkIfExists(toDrop)) {
			ZuulEventRouter.output.notInInventory(toDrop);
			return;
		}
		TakeableItem item = GameController.getCurrentPlayer().getInvController().getItem(toDrop);
		ZuulEventRouter.output.onDrop(toDrop);
		GameController.getCurrentPlayer().getInvController().setWeight(-item.getWeight());
		GameController.getCurrentPlayer().getInvController().removeItem(item);
		RoomController.addTakeableItem(item);
	}

}
