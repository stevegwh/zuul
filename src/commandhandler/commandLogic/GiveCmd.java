package commandhandler.commandLogic;

import commandhandler.CommandLogic;
import npc.NPC;
import zuul.GameController;
import zuul.TakeableItem;
import zuulutils.ZuulTools;

public class GiveCmd implements CommandLogic{
	protected String itemName;
	protected String actorName;
	protected TakeableItem takeableItem;
	protected NPC npc;

	@Override
	public boolean execute(String[] inputArray) {
		if(!npc.onGive(takeableItem.getName())) {
			return false;
		} else {
			if(takeableItem.isPerishable()) {
				GameController.getCurrentPlayer().getInvController().setWeight(takeableItem.getWeight());
				GameController.getCurrentPlayer().getInvController().removeItem(takeableItem);
			}
			return true;
		}
	}

	@Override
	public String validateUserInput(String[] inputArray) {
		final int COMMAND_LENGTH = 4;
		if(inputArray.length > COMMAND_LENGTH) {
			return "Invalid Command";
		}
		if(inputArray.length == 1) {
			return "Give what?";
		}
		itemName = inputArray[1];
		takeableItem = GameController.getCurrentPlayer().getInvController().getItem(itemName);
		boolean itemInInv = GameController.getCurrentPlayer().getInvController().checkIfExists(itemName); 
		if(!itemInInv) {
			return "You don't have the " + itemName;
		}
		if(itemInInv && inputArray.length <= 3) {
			return "Give to who?";
		}
		String preposition = inputArray[2];
		if(!preposition.equals("to")) {
			return "Do you mean 'to'?";
		}
		String actorName = inputArray[3];
		actorName = ZuulTools.capitalize(actorName);
		NPC npc = GameController.getActor(actorName);
		if(npc == null) {
			return actorName + " is not in the room";
		} else {
			this.npc = npc;
		}
		if(!npc.getCurrentLocation().equals(GameController.getCurrentPlayer().getLocation())) {
			return actorName + " is not in the room";
		}

		return null;
	}

}
