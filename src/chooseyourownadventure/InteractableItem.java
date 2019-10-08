package chooseyourownadventure;

// Class for any item in the game the player can interact with
// These items get acted upon by the TakeableItem class
// InteractableItems accept USE and INVESTIGATE commands
public class InteractableItem extends Item{
	private String validItem;
	private String descriptionOnTake;
	private String descriptionOnInvestigate;
	
	// itemToCheck is the name of the item the user wants to use on the object.
	// checks if this is the correct item and then executes the necessary action
	// Example: InteractableItem is a door. validItem could be 'key'. executeAction() could be "unlock()"
	void onUse(String itemToCheck) {
		if(itemToCheck.equals(validItem)) {
			executeAction();
		} else {
			Output.println("That didn't seem to work.");
		}
	}
	
	void onInvestigate() {
		Output.println(descriptionOnInvestigate);
	}
	
	void executeAction() {
		Output.println(descriptionOnTake);
	}
	
	InteractableItem(String validItem) {
		this.validItem = validItem;
	}

	InteractableItem() {
	}
}
