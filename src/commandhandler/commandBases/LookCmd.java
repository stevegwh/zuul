package commandhandler.commandBases;

import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import commandhandler.CommandBase;
import zuul.GameController;

public class LookCmd implements CommandBase {
	protected String lookDescription;
	protected JsonArray actors;
	protected ArrayList<String> items = new ArrayList<>();
	protected JsonObject exits;
	public boolean execute(String[] args) {
		lookDescription = GameController.getRoomController().getLookDescription();
		actors = GameController.getRoomController().getActorsInRoom(GameController.getCurrentPlayer().getLocation());
		if(GameController.getRoomController().hasTakeableItems()) {
			items = GameController.getRoomController().getTakeableItems();
		}
		exits = (JsonObject) GameController.getRoomController().getAllExits();
		return true;
	}
	@Override
	public String validateUserInput(String[] inputArray) {
		// TODO Auto-generated method stub
		return null;
	}
}
