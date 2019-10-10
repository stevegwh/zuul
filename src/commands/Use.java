package commands;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import zuul.InteractableItem;
import zuul.Inventory;
import zuul.Output;
import zuul.Room;

public class Use implements Command {
	@Override
	public void execute(String[] args) {
		// Check if itemToUse is in inventory O
		// Check if interactableItem exists O
		// Pass itemToUse into checkIfValidItem method
		String itemToUse = args[1];
		String interactableItem = args[3];
		if (!Inventory.checkIfExists(itemToUse)) {
			Output.println("You do not have '" + itemToUse + "' in your inventory");
			return;
		}
		JSONArray interactableItems = Room.getInteractableItems();
		JSONObject obj = Room.ifExistsInArrayReturnObj(interactableItem, interactableItems);
		// Make sure room has interactableItems and the item the player using exists
		// TODO: Check if you should check ifExistsInArray... after checking interactableItems is null. Might cause error.
		if (interactableItems != null && obj != null) {
			String name = (String) obj.get("name");
			String validItem = (String) obj.get("validItem");
			InteractableItem item = new InteractableItem(name, validItem);
			item.onUse(itemToUse, "unlock");
			// TODO: Overwrite/Erase the item and just the old onInvestigate with onInvestigateAfteruse
			return;
		}
		Output.println("Couldn't find " + interactableItem);
	}
}
