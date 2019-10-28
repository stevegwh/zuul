package command.commandController;

import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.JsonArray;

import command.CommandController;
import zuul.GameController;

public class LookController extends CommandController {
	protected String description;
	protected JsonArray actors;
	protected JsonArray items;
	protected ArrayList<String> exits;

	public boolean execute(String[] inputArray) {
		description = GameController.getRoomModel().getDescription();
		actors = GameController.getRoomModel().getActorsInRoom(GameController.getCurrentPlayer().getLocation());
		items = GameController.getRoomModel().getTakeableItems();
		exits = GameController.getRoomModel().getAllDirections(GameController.getCurrentPlayer().getLocation());
		return true;
	}

	@Override
	public String validateUserInput(String[] inputArray) {
		if(inputArray.length > 1) {
			return "Look what?";
		}
		return null;
	}
}
