package command.commandController;

import java.util.ArrayList;

import command.CommandController;
import zuul.GameController;
import zuul.TakeableItem;

public class InventoryController extends CommandController {
	protected ArrayList<TakeableItem> inventory = new ArrayList<>();

	protected String validateUserInput(String[] inputArray) {
		inventory = GameController.getCurrentPlayer().getInvModel().getInventory();
		if (inventory.size() == 0) {
			return "You do not currently have anything in your inventory";
		}
		return null;
	}

	@Override
	protected boolean execute(String[] inputArray) {
		return false;
	}
}
