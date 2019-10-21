package commands;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import IO.IOHandler;
import eventHandler.ZuulEventHandler;
import interactableItem.InteractableItem;
import zuul.InventoryController;
import zuul.RoomController;
import zuul.TakeableItem;

public class UseCmd implements Command {
	
	private int COMMAND_LENGTH = 4;
	
	// Updates the interactableItem's JsonObject to disable interactivity and give a different description after use
	private void updateJSON(String interactableItem, JsonObject interactableItemObj) {
		// Create a new object to erase the old one
		JsonObject toPush = new JsonObject();
		String onInvestigateAfterUse = (String) interactableItemObj.get("onInvestigateAfterUse");
		toPush.put("name", interactableItem);
		toPush.put("onInvestigate", onInvestigateAfterUse);
		// Remove old object data and add new
		RoomController.removeInteractableItem(interactableItemObj);
		RoomController.addInteractableItem(toPush);
	}
	
	private void updateInventory(String itemName) {
		TakeableItem item = InventoryController.getItem(itemName);
		if(item.isPerishable()) {
			InventoryController.removeItem(item);
		}
		
	}

	// TODO: Messy
	@Override
	public void execute(String[] args) {
		// MESSY AND ASSUMES IT WILL ALWAYS BE 4 (What about "use potion" which is len 2?)//////
		if(args.length < COMMAND_LENGTH) {
			ZuulEventHandler.invalidCommand();
			return;
		}
		if(args[1] == null || args[2] == null || args[3] == null ) {
			ZuulEventHandler.invalidCommand();
			return;
		}
		///////////////////////////////////////////////////////////////
		String itemToUse = args[1];
		String interactableItem = args[3];
		if(args[2].equals("on") || args[2].equals("with")) {
			if (!InventoryController.checkIfExists(itemToUse)) {
				// TODO: Replace with ZuulMessageHandler
				IOHandler.output.println("You do not have '" + itemToUse + "' in your inventory");
				return;
			}
			if (RoomController.hasInteractableItems()) {
				// Get the JsonObject for the interactableItem the user has requested if it exists
				JsonObject obj = RoomController.ifExistsInArrayReturnObj(interactableItem, "interactableItems");
				if (obj != null && obj.get("onUse") != null) { // Make sure it exists *and* that it can be acted upon with onUse
					String name = (String) obj.get("name");
					String validItem = (String) obj.get("validItem"); //TODO: poor variable name
					// Get the array that specifies what method to call when InteractableItem is used
					JsonArray onUse = (JsonArray) obj.get("onUse");
					//convert JSONArray to String[] to make it more conventional to work with
					String[] itemMethodArgs = new String[onUse.size()];
					itemMethodArgs = (String[]) onUse.toArray(itemMethodArgs);
					// get method name for InteractableItem onUse method
					InteractableItem item = new InteractableItem(name, validItem);
					// Attempt to execute onUse() of InteractableItem
					if (item.onUse(itemToUse, itemMethodArgs)) {
						// If true overwrite the JsonObject for InteractableItem
						updateJSON(interactableItem, obj);
						updateInventory(itemToUse);
					}
					return;
				} else {
					ZuulEventHandler.invalidCommand();
					return;
				}
			}
		} else {
			ZuulEventHandler.invalidCommand();
			
		}
		ZuulEventHandler.cantFind(interactableItem);
	}
}

