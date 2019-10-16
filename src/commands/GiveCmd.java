package commands;

import npc.NPC;
import zuul.GameController;
import zuul.Inventory;
import zuul.Output;
import zuul.TakeableItem;

public class GiveCmd implements Command{

	public GiveCmd() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(String[] args) {
		// TODO: Verify
		String itemName = args[1]; // give x
		String actorName = args[3]; // to x
		if(Inventory.checkIfExists(itemName)) {
			TakeableItem takeableItem = Inventory.getItem(itemName);
			NPC npc = GameController.getActor(actorName);
			if(!npc.onGive(takeableItem.getName())) {
				Output.println(npc.getName() + " didn't seem to want the " + itemName);
			} else {
				if(takeableItem.isPerishable()) {
					Inventory.setWeight(takeableItem.getWeight());
					Inventory.removeItem(takeableItem);
					Output.println(itemName + " was removed from your inventory"); // TODO: add this to remove item method in Inventory
				}
			}
		} else {
			Output.println("You do not have " + itemName + " in your inventory"); // TODO: You've written this before
		}
			
	}

}
