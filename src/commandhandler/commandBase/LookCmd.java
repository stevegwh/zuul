package commandhandler.commandBase;

import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import commandhandler.Command;
import zuul.GameController;

public abstract class LookCmd extends Command {
	protected String description;
	protected JsonArray actors;
	protected ArrayList<String> items = new ArrayList<>();
	protected JsonObject exits;
	public boolean execute(String[] args) {
		description = GameController.getRoomController().getDescription();
		actors = GameController.getRoomController().getActorsInRoom(GameController.getCurrentPlayer().getLocation());
		items = GameController.getRoomController().getTakeableItems();
		exits = (JsonObject) GameController.getRoomController().getAllExits();
		return true;
	}
	@Override
	public String validateUserInput(String[] inputArray) {
		return null;
	}
}
