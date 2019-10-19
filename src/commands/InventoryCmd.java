package commands;

import java.util.ArrayList;

import IO.OutputHandler;
import zuul.InventoryController;
import zuul.TakeableItem;

public class InventoryCmd implements Command {

	public void execute(String[] args) {
		ArrayList<TakeableItem> inventory = InventoryController.getInventory();
		if(inventory.size() == 0) {
			OutputHandler.println("You do not currently have anything in your inventory");
		} else {
			OutputHandler.println("You are currently carrying: ");
			for(TakeableItem item: inventory) {
				String name = item.getName();
				name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
				OutputHandler.println(name); //TODO: could be capitalized
			}
		}
		
	}

	public InventoryCmd() {
		super();
	}
}
