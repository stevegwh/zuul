package commandhandler.commands;

import com.github.cliftonlabs.json_simple.JsonObject;

import commandhandler.Command;
import eventHandler.ZuulEventHandler;
import interactableItem.InteractableItem;
import zuul.RoomController;

public class InvestigateCmd implements Command {
	@Override
	public void execute(String[] args) {
		String toInvestigate = args[1];
		JsonObject obj = RoomController.ifExistsInArrayReturnObj(toInvestigate, "interactableItems");
		if (RoomController.hasInteractableItems() && obj != null) {
			//You don't really need to even create a new object here, you could just print the investigate description
			//Maybe for clarity this approach is nicer...
			String descriptionOnInvestigate = (String) obj.get("onInvestigate");
			InteractableItem item = new InteractableItem(descriptionOnInvestigate);
			item.onInvestigate();
			return;
		}
		ZuulEventHandler.output.cantFind(args[1]);
	}
}
