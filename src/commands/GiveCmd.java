package commands;

import npc.NPC;
import zuul.GameController;
import zuul.Inventory;
import zuul.Output;

public class GiveCmd implements Command{

	public GiveCmd() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(String[] args) {
		// TODO: Verify
		String itemName = args[1]; // give x
		String actorName = args[3]; // to x
		String takeableItem = Inventory.getItem(itemName).getName();
		NPC npc = GameController.getActor(actorName);
		if(!npc.onGive(takeableItem)) {
			Output.println(npc.getName() + " didn't seem to want the " + itemName);
		}
	}

}
