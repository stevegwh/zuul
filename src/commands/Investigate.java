package commands;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import zuul.InteractableItem;
import zuul.Output;
import zuul.Room;

public class Investigate implements Command {
	@Override
	public void execute(String[] args) {
		String toInvestigate = args[1];
		JSONArray interactableItems = Room.getInteractableItems();
		JSONObject obj = Room.ifExistsInArrayReturnObj(toInvestigate, interactableItems);
		if (interactableItems != null && obj != null) {
			//You don't really need to even create a new object here, you could just print the investigate description
			//Maybe for clarity this approach is nicer...
			String descriptionOnInvestigate = (String) obj.get("onInvestigate");
			InteractableItem item = new InteractableItem(descriptionOnInvestigate);
			item.onInvestigate();
			return;
		}
		Output.println("Couldn't find that.");
	}

	public Investigate() {
		super();
		// TODO Auto-generated constructor stub
	}

}
