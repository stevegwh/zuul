package commandhandler.commandBases;

import java.util.ArrayList;

import commandhandler.CommandBase;
import zuul.GameController;
import zuul.TakeableItem;

public class InventoryCmd implements CommandBase {
	protected ArrayList<TakeableItem> inventory = new ArrayList<>();
	public boolean execute(String[] args) {
		inventory = GameController.getCurrentPlayer().getInvController().getInventory();
		if(inventory.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}
