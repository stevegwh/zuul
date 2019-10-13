package commands;

import zuul.Inventory;
import zuul.Output;
import zuul.Room;
import zuul.TakeableItem;

public class Drop implements Command {

	public Drop() {
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
		Inventory.removeItem(item);
		Room.addTakeableItem(item);
		
	}

}
