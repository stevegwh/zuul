package zuul;

import java.lang.reflect.InvocationTargetException;

import itemMethods.ItemMethod;

// Class for any item in the game the player can interact with
// These items get acted upon by the TakeableItem class
// InteractableItems accept USE and INVESTIGATE commands
public class InteractableItem extends Item{
	private String validItem;
	private String descriptionOnInvestigate;
	
	// itemToCheck is the name of the item the user wants to use on the object.
	// Checks if this is the correct item and then executes the necessary action
	// Example: InteractableItem is a door. validItem could be 'key'. executeAction() could be "unlock()"
	public boolean onUse(String itemToCheck, String[] args) {
		if(itemToCheck.equals(validItem)) {
			executeAction(args);
			return true;
		} else {
			Output.println("That didn't seem to work.");
			return false;
		}
	}
	
	public void onInvestigate() {
		Output.println(descriptionOnInvestigate);
	}

	void executeAction(String[] args) {
		String methodName = args[0];
		Object command = null;
		try {
			command = Class.forName("itemMethods." + methodName).getConstructor().newInstance();
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
