package commands;

import org.json.simple.JSONObject;

import zuul.InteractableItem;
import zuul.Output;
import zuul.RoomController;

public class InvestigateCmd implements Command {
	@Override
	public void execute(String[] args) {
		String toInvestigate = args[1];
		JSONObject obj = RoomController.ifExistsInArrayReturnObj(toInvestigate, "interactableItems");
		if (RoomController.hasInteractableItems() && obj != null) {
			//You don't really need to even create a new object here, you could just print the investigate description
			//Maybe for clarity this approach is nicer...
			String descriptionOnInvestigate = (String) obj.get("onInvestigate");
			InteractableItem item = new InteractableItem(descriptionOnInvestigate);
			item.onInvestigate();
			return;
		}
		Output.println("Couldn't find that.");
	}

	public InvestigateCmd() {
		super();
		// TODO Auto-generated constructor stub
	}

}
