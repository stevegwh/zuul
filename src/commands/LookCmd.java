package commands;

import com.github.cliftonlabs.json_simple.JsonArray;

import IO.OutputHandler;
import zuul.Player;
import zuul.RoomController;

// TODO: Print interactableItems and takeableItems
public class LookCmd implements Command {
	@SuppressWarnings("unchecked")
	public void execute(String[] args) {
		String lookDescription = RoomController.getLookDescription();
		if (lookDescription != null) {
			OutputHandler.println(lookDescription);
		} else {
			OutputHandler.println("Nothing interesting to report");
		}
		JsonArray actors = RoomController.getActorsInRoom(Player.getLocation());
		if(actors.size() > 0) {
			OutputHandler.println("People in room: ");
			actors.forEach((e) -> OutputHandler.println((String) e));
		} else {
			OutputHandler.println("Nobody in the room.");
		}
	}
}
