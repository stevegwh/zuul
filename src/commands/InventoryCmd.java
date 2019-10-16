package commands;

import java.util.ArrayList;

import zuul.Inventory;
import zuul.Output;
import zuul.TakeableItem;

public class InventoryCmd implements Command {

	public void execute(String[] args) {
		ArrayList<TakeableItem> inventory = Inventory.getInventory();
		if(inventory.size() == 0) {
			Output.println("You do not currently have anything in your inventory");
		} else {
			Output.println("You are currently carrying: ");
			for(TakeableItem item: inventory) {
				String name = item.getName();
				name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
				Output.println(name); //TODO: could be capitalized
			}
		}
		
	}

	public InventoryCmd() {
		super();
	}
}
