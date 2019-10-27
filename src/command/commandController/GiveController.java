package command.commandController;

import command.CommandController;
import npc.NPC;
import zuul.GameController;
import zuul.TakeableItem;
import zuulutils.ZuulTools;

public class GiveController extends CommandController {
	protected String itemName;
	protected String actorName;
	protected TakeableItem takeableItem;
	protected NPC npc;

	@Override
	public String validateUserInput(String[] inputArray) {
		final int COMMAND_LENGTH = 3;
		if (inputArray.length > COMMAND_LENGTH) {
			return "Invalid CommandController";
		}
		if (inputArray.length == 1) {
			return "Give what?";
		}
		itemName = inputArray[1];
		takeableItem = GameController.getCurrentPlayer().getInvModel().getItem(itemName);
		boolean itemInInv = GameController.getCurrentPlayer().getInvModel().checkIfExists(itemName);
		if (!itemInInv) {
			return "You don't have the " + itemName;
		}
		if (inputArray.length <= 2) {
			return "Give to who?";
		}
		String actorName = inputArray[2];
		actorName = ZuulTools.capitalize(actorName);
		NPC npc = GameController.getNPCContoller().getActor(actorName);
		if (npc == null) {
			return actorName + " is not in the room";
		} else {
			this.npc = npc;
		}
		if (!npc.getCurrentLocation().equals(GameController.getCurrentPlayer().getLocation())) {
			return actorName + " is not in the room";
		}

		return null;
	}

	@Override
	public boolean execute(String[] inputArray) {
		if (!npc.onGive(takeableItem.getName())) {
			return false;
		} else {
			if (takeableItem.isPerishable()) {
				GameController.getCurrentPlayer().getInvModel().setWeight(takeableItem.getWeight());
				GameController.getCurrentPlayer().getInvModel().removeItem(takeableItem);
			}
			return true;
		}
	}

}
