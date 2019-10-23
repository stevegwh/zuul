package commandhandler.commandBases;

import commandhandler.CommandBase;
import npc.NPC;
import zuul.GameController;
import zuul.InventoryController;
import zuul.TakeableItem;
import zuulutils.ZuulTools;

public class GiveCmd implements CommandBase{
	protected String itemName;
	protected String actorName;
	protected TakeableItem takeableItem;
	protected NPC npc;

	// TODO: implement validation
	@Override
	public boolean execute(String[] args) {
		itemName = args[1];
		actorName = args[3];
		actorName = ZuulTools.capitalize(actorName);
		if(GameController.getCurrentPlayer().getInvController().checkIfExists(itemName)) {
			takeableItem = GameController.getCurrentPlayer().getInvController().getItem(itemName);
			npc = GameController.getActor(actorName);
			if(!npc.onGive(takeableItem.getName())) {
				return false;
			} else {
				if(takeableItem.isPerishable()) {
					GameController.getCurrentPlayer().getInvController().setWeight(takeableItem.getWeight());
					GameController.getCurrentPlayer().getInvController().removeItem(takeableItem);
				}
				return true;
			}
		} else {
			// TODO: This isn't handled in the subclass...
//			ZuulEventRouter.output.notInInventory(itemName);
			return false;
		}
			
	}

}
