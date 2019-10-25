package commandhandler.commandBase;

import java.util.ArrayList;

import commandhandler.Command;
import zuul.GameController;
import zuul.TakeableItem;

public class InventoryCmd extends Command {
	protected ArrayList<TakeableItem> inventory = new ArrayList<>();
	protected String validateUserInput(String[] inputArray) {
		inventory = GameController.getCurrentPlayer().getInvController().getInventory();
		if(inventory.size() == 0) {
			return "You do not currently have anything in your inventory";
		}
		return null;
	}
	@Override
	protected boolean execute(String[] inputArray) {
		// TODO Auto-generated method stub
		return false;
	}
}
