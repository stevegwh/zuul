package chooseyourownadventure;

public class InteractableObject {
	private String validItem;
	private String descriptionOnTake;
	private String descriptionOnInvestigate;
	
	void onUse(String itemToCheck) {
		if(itemToCheck.equals(validItem)) {
			executeAction();
		}
	}
	
	void onInvestigate() {
		Output.println(descriptionOnInvestigate);
	}
	
	void executeAction() {
		Output.println(descriptionOnTake);
	}
	
	InteractableObject(String validItem) {
		this.validItem = validItem;
	}
}
