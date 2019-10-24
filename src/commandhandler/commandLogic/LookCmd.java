package commandhandler.commandLogic;

import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import commandhandler.CommandLogic;
import zuul.GameController;

public class LookCmd implements CommandLogic {
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
		// TODO Auto-generated method stub
		return null;
	}
}
