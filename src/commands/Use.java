package commands;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import zuul.InteractableItem;
import zuul.Inventory;
import zuul.Output;
import zuul.Room;
import zuul.TakeableItem;

public class Use implements Command {
	
	// Updates the interactableItem's JSONObject to disable interactivity and give a different description after use
	private void updateJSON(String interactableItem, JSONObject interactableItemObj) {
		// Create a new object to erase the old one
		JSONObject toPush = new JSONObject();
		String onInvestigateAfterUse = (String) interactableItemObj.get("onInvestigateAfterUse");
		toPush.put("name", interactableItem);
		toPush.put("onInvestigate", onInvestigateAfterUse);
		// Remove old object data and add new
		Room.removeInteractableItem(interactableItemObj);
		Room.addInteractableItem(toPush);
		// TODO: remove item from user's inventory if it is perishable on use or not
	}
	
	private void updateInventory(String itemName) {
		TakeableItem item = Inventory.getItem(itemName);
		if(item.isPerishable()) {
			Inventory.removeItem(item);
		}
		
	}

	@Override
	public void execute(String[] args) {
		String itemToUse = args[1];
		String interactableItem = args[3];
		if (!Inventory.checkIfExists(itemToUse)) {
			Output.println("You do not have '" + itemToUse + "' in your inventory");
			return;
		}
		if (Room.hasInteractableItems()) {
			// Get the JSONObject for the interactableItem the user has requested if it exists
			JSONObject obj = Room.ifExistsInArrayReturnObj(interactableItem, "interactableItems");
			if (obj != null && obj.get("onUse") != null) { // Make sure it exists *and* that it can be acted upon with onUse
				String name = (String) obj.get("name");
				String validItem = (String) obj.get("validItem"); //TODO: poor variable name
				// Get the array that specifies what method to call when InteractableItem is used
				JSONArray onUse = (JSONArray) obj.get("onUse");
				//convert JSONArray to String[] to make it more conventional to work with
				String[] itemMethodArgs = new String[onUse.size()];
				itemMethodArgs = (String[]) onUse.toArray(itemMethodArgs);
				// get method name for InteractableItem onUse method
				InteractableItem item = new InteractableItem(name, validItem);
				// Attempt to execute onUse() of InteractableItem
				if (item.onUse(itemToUse, itemMethodArgs)) {
					// If true overwrite the JSONObject for InteractableItem
					updateJSON(interactableItem, obj);
					updateInventory(itemToUse);
				}
				return;
			} else {
				Output.println("Sorry, you can't do that");
				return;
			}
		}
		Output.println("Couldn't find " + interactableItem);
	}
}

