package npc;

import zuul.Output;
import java.util.ArrayList;
import java.util.List;

public abstract class NPC {
	private String name;
	private String currentLocation;
	private List<String> movementPath = new ArrayList<String>();

	public abstract void beginDialog();

	public void update() {
		
	}
	
    protected void setMovementPath(String[] rooms) {
    	for(String room : rooms) {
    		movementPath.add(room);
    	}
    }
	public void onInvestigate() {
		Output.println("You see " + name);
	}

	public String getCurrentLocation() {
		return currentLocation;
	}
	
	public NPC(String name, String currentLocation) {
		this.name = name;
		this.currentLocation = currentLocation;
	}
}
