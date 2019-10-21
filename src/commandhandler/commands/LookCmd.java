package commandhandler.commands;

import com.github.cliftonlabs.json_simple.JsonArray;

import IO.IOHandler;
import commandhandler.Command;
import zuul.Player;
import zuul.RoomController;

// TODO: Print interactableItems and takeableItems.
public class LookCmd implements Command {
	public void execute(String[] args) {
		String lookDescription = RoomController.getLookDescription();
		if (lookDescription != null) {
			IOHandler.output.println(lookDescription);
		} else {
			IOHandler.output.println("Nothing interesting to report");
		}
		JsonArray actors = RoomController.getActorsInRoom(Player.getLocation());
		if(actors.size() > 0) {
		// TODO: Coupled with console output... could print the people in room etc onRoomEnter in ConsoleEventHandler?
			IOHandler.output.println("People in room: ");
			actors.forEach((e) -> IOHandler.output.println((String) e));
		} else {
			IOHandler.output.println("Nobody in the room.");
		}
	}
}
