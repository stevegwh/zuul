package npc.npcs;

import IO.IOHandler;
import npc.NPC;

public class Barry extends NPC {
	public Barry() {
		// TODO: Would be nice to read the json file for the location of the NPC, rather than setting it
		// here and also in the roomData json
		// TODO: If this name doesn't match the class name it causes the game to crash.
		super(new Object(){}.getClass().getEnclosingClass().getSimpleName());
	}


	@Override
	public boolean onGive(String toCompare) {
		String validItem = super.getValidItem();
		if(toCompare.contentEquals(validItem)) {
			IOHandler.output.printCharDialog("Oh, thank you for the " + validItem);
			return true;
		}
		return false;
	}

}
