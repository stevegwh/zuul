package commandhandler.commandBases;

import commandhandler.CommandBase;
import npc.NPC;
import zuul.GameController;
import zuul.TakeableItem;
import zuulutils.ZuulTools;

public class GiveCmd implements CommandBase{
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
		System.out.println(inputArray.length);
		final int COMMAND_LENGTH = 4;
		if(inputArray.length > COMMAND_LENGTH) {
			return "Invalid Command";
		}
		String preposition = inputArray[2];
		if(!preposition.equals("to")) {
			return "Invalid Command";
		}
		String actorName = inputArray[3];
		actorName = ZuulTools.capitalize(actorName);
		NPC npc = GameController.getActor(actorName);
		if(npc == null) {
			return "Can't find " + actorName;
		} else {
			this.npc = npc;
		}
		itemName = inputArray[1];
		takeableItem = GameController.getCurrentPlayer().getInvController().getItem(itemName);
		if(!GameController.getCurrentPlayer().getInvController().checkIfExists(itemName)) { 
			return itemName + " not in inventory";
		}

		return null;
	}

}
