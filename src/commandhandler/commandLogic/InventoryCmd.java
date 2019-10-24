package commandhandler.commandLogic;

import java.util.ArrayList;

import commandhandler.CommandLogic;
import zuul.GameController;
import zuul.TakeableItem;

public class InventoryCmd implements CommandLogic {
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
