package commands;

import java.util.ArrayList;

import IO.IOHandler;
import zuul.InventoryController;
import zuul.TakeableItem;

public class InventoryCmd implements Command {

	public void execute(String[] args) {
		ArrayList<TakeableItem> inventory = InventoryController.getInventory();
		if(inventory.size() == 0) {
			IOHandler.output.println("You do not currently have anything in your inventory");
		} else {
			IOHandler.output.println("You are currently carrying: ");
			for(TakeableItem item: inventory) {
				String name = item.getName();
				// TODO: Capitalize
				name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
				IOHandler.output.println(name); //TODO: could be capitalized
			}
		}
		
	}

	public InventoryCmd() {
		super();
	}
}
