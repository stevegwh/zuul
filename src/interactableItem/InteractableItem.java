package interactableItem;

import java.lang.reflect.InvocationTargetException;

import IO.IOHandler;
import zuul.ZuulTools;

// Class for any item in the game the player can interact with
// These items get acted upon by the TakeableItem class
// InteractableItems accept USE and INVESTIGATE commands
public class InteractableItem {
	private String validItem;
	private String descriptionOnInvestigate;
	
	/**
	 * Checks itemToCheck against validItem and then executes the method specified in args.
	 * Example: InteractableItem is a door. validItem could be 'key'. executeAction() could be "unlock()"
	 * 
	 * @param itemToCheck the name of the item the user wants to use on the object.
	 * @param args the name and arguments of the method that will be called on the InteractableItem
	 * @return
	 */
	public boolean onUse(String itemToCheck, String[] args) {
		if(itemToCheck.equals(validItem)) {
			executeAction(args);
			return true;
		} else {
			IOHandler.output.println("That didn't seem to work.");
			return false;
		}
	}
	
	public void onInvestigate() {
		IOHandler.output.println(descriptionOnInvestigate);
	}

	/** 
	 * Example: args could contain ["Unlock", "north", "room3"] with index 0 being the method's name and
	 * the indexes after being the parameters for that method.
	 * Note: 'args' should be defined in the room's JsonObject under 'onUse', not passed in directly to this method.
	 * Example: "onUse" : ["Unlock", "north", "room3"]
	 * 
	 * @param args method name at index 0, params after.
	 */
	void executeAction(String[] args) {
		String methodName = ZuulTools.capitalize(args[0]);
		Object command = null;
		try {
			command = Class.forName(this.getClass().getPackageName() + "." + methodName).getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		((ItemMethod) command).execute(args);
	}

	public InteractableItem(String descriptionOnInvestigate) {
		this.descriptionOnInvestigate = descriptionOnInvestigate;
	}
	
	// TODO: name is not needed, removing it causes a clash with the other constructor you use for investigating
	public InteractableItem(String name, String validItem) {
		this.validItem = validItem;
	}

}
