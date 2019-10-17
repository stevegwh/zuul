package commands;

import npc.NPC;
import zuul.GameController;
import zuul.InventoryController;
import zuul.Output;
import zuul.TakeableItem;

public class GiveCmd implements Command{

	public GiveCmd() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(String[] args) {
		String preposition = args[2];
		if(!preposition.equals("to")) {
			Output.println("Invalid command");
			return;
		}
		String itemName = args[1];
		String actorName = args[3];
		if(InventoryController.checkIfExists(itemName)) {
			TakeableItem takeableItem = InventoryController.getItem(itemName);
			NPC npc = GameController.getActor(actorName);
			if(!npc.onGive(takeableItem.getName())) {
				Output.println(npc.getName() + " didn't seem to want the " + itemName);
			} else {
				if(takeableItem.isPerishable()) {
					InventoryController.setWeight(takeableItem.getWeight());
					InventoryController.removeItem(takeableItem);
				}
			}
		} else {
			Output.println("You do not have " + itemName + " in your inventory"); // TODO: You've written this before
		}
			
	}

}
