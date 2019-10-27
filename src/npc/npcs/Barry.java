package npc.npcs;

import IO.IOHandler;
import npc.NPC;

public class Barry extends NPC {
	public Barry() {
		super(new Object() {
		}.getClass().getEnclosingClass().getSimpleName());
	}

	@Override
	public boolean onGive(String toCompare) {
		String validItem = super.getValidItem();
		if (toCompare.contentEquals(validItem)) {
			IOHandler.output.printCharDialog("Oh, thank you for the " + validItem);
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		getRandomRoom();
	}

}
