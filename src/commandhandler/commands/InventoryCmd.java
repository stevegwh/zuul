package commandhandler.commands;

import java.util.ArrayList;

import commandhandler.Command;
import eventHandler.ZuulEventHandler;
import zuul.InventoryController;
import zuul.TakeableItem;

public class InventoryCmd implements Command {
	public void execute(String[] args) {
		ArrayList<TakeableItem> inventory = InventoryController.getInventory();
		if(inventory.size() == 0) {
			ZuulEventHandler.output.onInventoryFail();
		} else {
			ZuulEventHandler.output.renderInventory(inventory);
		}
	}
}
