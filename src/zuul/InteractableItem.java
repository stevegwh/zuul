package zuul;

// Class for any item in the game the player can interact with
// These items get acted upon by the TakeableItem class
// InteractableItems accept USE and INVESTIGATE commands
public class InteractableItem extends Item{
	private String name;
	private String validItem;
	private String descriptionOnInvestigate;
	
	// itemToCheck is the name of the item the user wants to use on the object.
	// checks if this is the correct item and then executes the necessary action
	// Example: InteractableItem is a door. validItem could be 'key'. executeAction() could be "unlock()"
	public void onUse(String itemToCheck, String method) {
		if(itemToCheck.equals(validItem)) {
			executeAction(method);
		} else {
			Output.println("That didn't seem to work.");
		}
	}
	
	public void onInvestigate() {
		Output.println(descriptionOnInvestigate);
	}

	// Should take the param (e.g. "unlock) and pass it to the necessary method
	void executeAction(String method) {
		unlock();
	}
	
	void unlock() {
		Output.println("Door unlocked! Maybe...");
	}

	public InteractableItem(String descriptionOnInvestigate) {
		this.descriptionOnInvestigate = descriptionOnInvestigate;
	}
	
	public InteractableItem(String name, String validItem) {
		this.name = name;
		this.validItem = validItem;
	}

}
