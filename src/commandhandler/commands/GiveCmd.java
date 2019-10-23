package commandhandler.commands;

import commandhandler.Command;
import eventHandler.ZuulEventRouter;
import npc.NPC;
import zuul.GameController;
import zuul.InventoryController;
import zuul.TakeableItem;
import zuulutils.ZuulTools;

public class GiveCmd implements Command{

	// TODO: error check arguments
	@Override
	public void execute(String[] args) {
		String preposition = args[2];
		if(!preposition.equals("to")) {
			ZuulEventRouter.output.invalidCommand();
			return;
		}
		String itemName = args[1];
		String actorName = args[3];
		actorName = ZuulTools.capitalize(actorName);
		if(GameController.getCurrentPlayer().getInvController().checkIfExists(itemName)) {
			TakeableItem takeableItem = GameController.getCurrentPlayer().getInvController().getItem(itemName);
			NPC npc = GameController.getActor(actorName);
			if(!npc.onGive(takeableItem.getName())) {
				ZuulEventRouter.output.onGiveFail(npc.getName(), itemName);
			} else {
				if(takeableItem.isPerishable()) {
					GameController.getCurrentPlayer().getInvController().setWeight(takeableItem.getWeight());
					GameController.getCurrentPlayer().getInvController().removeItem(takeableItem);
				}
			}
		} else {
			ZuulEventRouter.output.notInInventory(itemName);
		}
			
	}

}
