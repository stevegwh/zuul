package npc;

import zuul.Output;
import zuul.TakeableItem;

public class Barry extends NPC {
//    private static Barry SINGLE_INSTANCE = null;
	private static String name = "Barry";
	private static String currentLocation = "room3";
	private static String[] options = {"Hi Barry, how are you?", "Would you like some gum?", "What's the meaning of life?"};
	private static String[] responses = {"I'm great, thanks for asking!", "But you don't have any gum!", "42"};
	private static String validItem = "gum";
//	@Override
//	public void beginDialog() {
//		Output.println("A student says hello");
//	}
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
		super(name, currentLocation, options, responses, validItem);
		String[] rooms = {"room2", "room3", "room4"};
		super.setMovementPath(rooms);
	}

	@Override
	public boolean onGive(String toCompare) {
		if(toCompare.contentEquals(validItem)) {
			Output.println("Oh, thank you for the " + validItem);
			return true;
		}
		return false;
	}

}
