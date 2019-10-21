package commandhandler.commands;

import commandhandler.Command;
import eventHandler.ZuulEventHandler;
import zuul.InventoryController;
import zuul.RoomController;
import zuul.TakeableItem;

public class DropCmd implements Command {
	@Override
	public void execute(String[] args) {
		String toDrop = args[1];
		if(!InventoryController.checkIfExists(toDrop)) {
			ZuulEventHandler.output.notInInventory(toDrop);
			return;
		}
		TakeableItem item = InventoryController.getItem(toDrop);
		ZuulEventHandler.output.onDrop(toDrop);
		InventoryController.setWeight(-item.getWeight());
		InventoryController.removeItem(item);
		RoomController.addTakeableItem(item);
	}

}
