package commandhandler.commandBase;

import java.util.ArrayList;

import commandhandler.Command;
import zuul.GameController;
import zuul.TakeableItem;

public abstract class InventoryCmd extends Command {
	protected ArrayList<TakeableItem> inventory = new ArrayList<>();
	public boolean execute(String[] args) {
		inventory = GameController.getCurrentPlayer().getInvController().getInventory();
		if(inventory.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	protected String validateUserInput(String[] inputArray) {
		// TODO Auto-generated method stub
		return null;
	}
}
