package commands;

import zuul.InventoryController;
import zuul.Output;
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
			Output.println("You aren't carrying a " + toDrop);
			return;
		}
		TakeableItem item = InventoryController.getItem(toDrop);
		Output.println("You dropped " + toDrop);
		InventoryController.setWeight(-item.getWeight());
		InventoryController.removeItem(item);
		RoomController.addTakeableItem(item);
		
	}

}
