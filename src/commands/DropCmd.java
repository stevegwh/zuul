package commands;

import zuul.Inventory;
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
		if(!Inventory.checkIfExists(toDrop)) {
			Output.println("You aren't carrying a " + toDrop);
			return;
		}
		TakeableItem item = Inventory.getItem(toDrop);
		Output.println("You dropped " + toDrop);
		Inventory.setWeight(-item.getWeight());
		Inventory.removeItem(item);
		RoomController.addTakeableItem(item);
		
	}

}
