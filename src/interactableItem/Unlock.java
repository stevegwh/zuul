package interactableItem;

import zuul.RoomController;

// TODO: Consider removing the item methods. Just make all items consumable or giveable.
public class Unlock implements ItemMethod {

	@Override
	public void execute(String[] args) {
		// TODO: Check if these are valid directions and if the room exists
		RoomController.addExit(args[1], args[2]);
//		ZuulEventRouter.output.onDoorUnlock();
	}
}
