package npc.npcs;

import IO.IOHandler;
import npc.NPC;

public class Barry extends NPC {
//    private static Barry SINGLE_INSTANCE = null;
//
//    public static Barry getInstance() {
//        if (SINGLE_INSTANCE == null) {
//            synchronized (Barry.class) {
//                if (SINGLE_INSTANCE == null) {
//                    SINGLE_INSTANCE = new Barry();
//                }
//            }
//        }
//        return SINGLE_INSTANCE;
//    }
    
	public Barry() {
		// TODO: Would be nice to read the json file for the location of the NPC, rather than setting it
		// here and also in the roomData json
		super("Barry", "room2");
	}


	@Override
	public boolean onGive(String toCompare) {
		String validItem = super.getValidItem();
		if(toCompare.contentEquals(validItem)) {
			IOHandler.output.println("Oh, thank you for the " + validItem);
			return true;
		}
		return false;
	}

}
