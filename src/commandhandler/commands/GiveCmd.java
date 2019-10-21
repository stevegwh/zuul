package commandhandler.commands;

import commandhandler.Command;
import eventHandler.ZuulEventHandler;
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
			ZuulEventHandler.output.invalidCommand();
			return;
		}
		String itemName = args[1];
		String actorName = args[3];
		actorName = ZuulTools.capitalize(actorName);
		if(InventoryController.checkIfExists(itemName)) {
			TakeableItem takeableItem = InventoryController.getItem(itemName);
			NPC npc = GameController.getActor(actorName);
			if(!npc.onGive(takeableItem.getName())) {
				ZuulEventHandler.output.onGiveFail(npc.getName(), itemName);
			} else {
				if(takeableItem.isPerishable()) {
					InventoryController.setWeight(takeableItem.getWeight());
					InventoryController.removeItem(takeableItem);
				}
			}
		} else {
			ZuulEventHandler.output.notInInventory(itemName);
		}
			
	}

}
