package commandhandler.commandBases;

import com.github.cliftonlabs.json_simple.JsonObject;

import commandhandler.CommandBase;
import interactableItem.InteractableItem;
import zuul.RoomController;

// TODO: Doesn't investigate NPCs
public class InvestigateCmd implements CommandBase {
	protected InteractableItem item;
	protected String toInvestigate;
	@Override
	public boolean execute(String[] args) {
		toInvestigate = args[1];
		JsonObject obj = RoomController.ifExistsInArrayReturnObj(toInvestigate, "interactableItems");
		if (RoomController.hasInteractableItems() && obj != null) {
			String descriptionOnInvestigate = (String) obj.get("onInvestigate");
			item = new InteractableItem(descriptionOnInvestigate);
			return true;
		}
		return false;
	}
	// TODO: Validate if the NPC is in the room
	// TODO: Validate if InteractableItem is in room
	@Override
	public String validateUserInput(String[] inputArray) {
		// TODO Auto-generated method stub
		return null;
	}
}
