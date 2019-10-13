package npc;

import zuul.Output;

public class Barry extends NPC {
    private static Barry SINGLE_INSTANCE = null;
	private static String name = "Barry";
	private static String currentLocation = "room3";
//	@Override
//	public void beginDialog() {
//		Output.println("A student says hello");
//	}
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
    
    public void beginDialog() {
    	Output.println(name + " says hello");
    }
    
	private Barry() {
		super(name, currentLocation);
		String[] rooms = {"room2", "room3", "room4"};
		super.setMovementPath(rooms);
    	
	}
}
