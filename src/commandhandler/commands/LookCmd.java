package commandhandler.commands;

import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import IO.IOHandler;
import commandhandler.Command;
import eventHandler.ZuulEventHandler;
import zuul.Player;
import zuul.RoomController;

// TODO: Print interactableItems and takeableItems.
// TODO: Need to print takeableItems too.
// TODO: Remove look description if not necessary for game.
// TODO: Events being handled outside of handler
public class LookCmd implements Command {
	public void execute(String[] args) {
		String lookDescription = RoomController.getLookDescription();
		if (lookDescription != null) {
			IOHandler.output.println(lookDescription);
		} else {
			IOHandler.output.println("Nothing interesting to report");
		}
		JsonArray actors = RoomController.getActorsInRoom(Player.getLocation());
		ZuulEventHandler.output.renderActors(actors);
		ArrayList<String> items = RoomController.getInteractableItems();
		ZuulEventHandler.output.renderItems(items);
		JsonObject exits = (JsonObject) RoomController.getAllExits();
		ZuulEventHandler.output.renderExits(exits);
	}
}
