package commands;

import IO.OutputHandler;
import zuul.InventoryController;
import zuul.RoomController;
import zuul.TakeableItem;

public class DropCmd implements Command {

	public DropCmd() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(String[] args) {
		String toDrop = args[1];
		if(!InventoryController.checkIfExists(toDrop)) {
			OutputHandler.println("You aren't carrying a " + toDrop);
			return;
		}
		TakeableItem item = InventoryController.getItem(toDrop);
		OutputHandler.println("You dropped " + toDrop);
		InventoryController.setWeight(-item.getWeight());
		InventoryController.removeItem(item);
		RoomController.addTakeableItem(item);
		
	}

}
