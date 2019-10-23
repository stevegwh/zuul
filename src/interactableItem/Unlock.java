package interactableItem;

import eventHandler.ZuulEventHandler;
import zuul.RoomController;

public class Unlock implements ItemMethod {

	@Override
	public void execute(String[] args) {
		// TODO: Check if these are valid directions and if the room exists
		RoomController.addExit(args[1], args[2]);
		ZuulEventHandler.output.onDoorUnlock();
	}
}
