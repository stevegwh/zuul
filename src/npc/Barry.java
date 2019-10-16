package npc;

import zuul.Output;

public class Barry extends NPC {
    private static Barry SINGLE_INSTANCE = null;

    public static Barry getInstance() {
        if (SINGLE_INSTANCE == null) {
            synchronized (Barry.class) {
                if (SINGLE_INSTANCE == null) {
                    SINGLE_INSTANCE = new Barry();
                }
            }
        }
        return SINGLE_INSTANCE;
    }
    
	private Barry() {
		super("Barry", "room2");
	}

	@Override
	public boolean onGive(String toCompare) {
		String validItem = super.getValidItem();
		if(toCompare.contentEquals(validItem)) {
			Output.println("Oh, thank you for the " + validItem);
			return true;
		}
		return false;
	}

}
