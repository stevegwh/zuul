package commandhandler.commands;

import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import commandhandler.Command;
import eventHandler.ZuulEventRouter;
import zuul.GameController;
import zuul.RoomController;

// TODO: Print interactableItems and takeableItems.
// TODO: Need to print takeableItems too.
// TODO: Remove look description if not necessary for game.
public class LookCmd implements Command {
	public void execute(String[] args) {
		String lookDescription = RoomController.getLookDescription();
		if (lookDescription != null) {
			ZuulEventRouter.output.onLook(lookDescription);
		} else {
			ZuulEventRouter.output.onLookFail();
		}
		JsonArray actors = RoomController.getActorsInRoom(GameController.getCurrentPlayer().getLocation());
		ZuulEventRouter.output.renderActors(actors);
		ArrayList<String> items = new ArrayList<>();
		ArrayList<String> items2 = new ArrayList<>();
		if(RoomController.hasInteractableItems()) {
			items = RoomController.getInteractableItems();
		}
		if(RoomController.hasTakeableItems()) {
			items2 = RoomController.getTakeableItems();
		}
		items.addAll(items2);
		ZuulEventRouter.output.renderItems(items);
		JsonObject exits = (JsonObject) RoomController.getAllExits();
		ZuulEventRouter.output.renderExits(exits);
	}
}
