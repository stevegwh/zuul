package commandhandler.commands;

import IO.IOHandler;
import commandhandler.Command;
import zuul.InventoryController;
import zuul.RoomController;
import zuul.TakeableItem;

public class DropCmd implements Command {

	@Override
	public void execute(String[] args) {
		String toDrop = args[1];
		if(!InventoryController.checkIfExists(toDrop)) {
			IOHandler.output.println("You aren't carrying a " + toDrop);
			return;
		}
		TakeableItem item = InventoryController.getItem(toDrop);
		IOHandler.output.println("You dropped " + toDrop);
		InventoryController.setWeight(-item.getWeight());
		InventoryController.removeItem(item);
		RoomController.addTakeableItem(item);
		
	}

}
