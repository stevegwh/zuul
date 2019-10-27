package command.commandController;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import command.CommandController;
import zuul.GameController;

public class LookController extends CommandController {
	protected String description;
	protected JsonArray actors;
	protected JsonArray items;
	protected JsonObject exits;

	public boolean execute(String[] inputArray) {
		description = GameController.getRoomModel().getDescription();
		actors = GameController.getRoomModel().getActorsInRoom(GameController.getCurrentPlayer().getLocation());
		items = GameController.getRoomModel().getTakeableItems();
		exits = (JsonObject) GameController.getRoomModel().getAllExits();
		return true;
	}

	@Override
	public String validateUserInput(String[] inputArray) {
		return null;
	}
}
