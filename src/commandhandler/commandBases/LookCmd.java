package commandhandler.commandBases;

import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import commandhandler.CommandBase;
import zuul.GameController;
import zuul.RoomController;

public class LookCmd implements CommandBase {
	protected String lookDescription;
	protected JsonArray actors;
	protected ArrayList<String> items = new ArrayList<>();
	protected JsonObject exits;
	public boolean execute(String[] args) {
		lookDescription = RoomController.getLookDescription();
		actors = RoomController.getActorsInRoom(GameController.getCurrentPlayer().getLocation());
		if(RoomController.hasTakeableItems()) {
			items = RoomController.getTakeableItems();
		}
		exits = (JsonObject) RoomController.getAllExits();
		return true;
	}
	@Override
	public String validateUserInput(String[] inputArray) {
		// TODO Auto-generated method stub
		return null;
	}
}
