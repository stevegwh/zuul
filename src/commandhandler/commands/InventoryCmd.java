package commandhandler.commands;

import java.util.ArrayList;

import commandhandler.Command;
import eventHandler.ZuulEventRouter;
import zuul.GameController;
import zuul.InventoryController;
import zuul.TakeableItem;

public class InventoryCmd implements Command {
	public void execute(String[] args) {
		ArrayList<TakeableItem> inventory = GameController.getCurrentPlayer().getInvController().getInventory();
		if(inventory.size() == 0) {
			ZuulEventRouter.output.onInventoryFail();
		} else {
			ZuulEventRouter.output.onInventory(inventory);
		}
	}
}
