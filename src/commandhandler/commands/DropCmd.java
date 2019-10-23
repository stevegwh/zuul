package commandhandler.commands;

import commandhandler.Command;
import eventHandler.ZuulEventHandler;
import zuul.GameController;
import zuul.InventoryController;
import zuul.RoomController;
import zuul.TakeableItem;

public class DropCmd implements Command {
	@Override
	public void execute(String[] args) {
		String toDrop = args[1];
		if(!GameController.getCurrentPlayer().inventory.checkIfExists(toDrop)) {
			ZuulEventHandler.output.notInInventory(toDrop);
			return;
		}
		TakeableItem item = GameController.getCurrentPlayer().inventory.getItem(toDrop);
		ZuulEventHandler.output.onDrop(toDrop);
		GameController.getCurrentPlayer().inventory.setWeight(-item.getWeight());
		GameController.getCurrentPlayer().inventory.removeItem(item);
		RoomController.addTakeableItem(item);
	}

}
