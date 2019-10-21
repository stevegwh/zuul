package commandhandler.commands;

import java.util.ArrayList;

import IO.IOHandler;
import commandhandler.Command;
import zuul.RoomController;

public class GoCmd implements Command {
	
	static ArrayList<String> sentences = new ArrayList<>();

	public enum Directions {
		NORTH, SOUTH, EAST, WEST
	}

	public boolean isValidDirection(String userInput) {
	    for (Directions c : Directions.values()) {
	        if (c.name().equals(userInput)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	// Methods for anyone writing a plugin/extension to use to add their own sentences and remove default ones
	@SuppressWarnings("unused")
	private static void addGoSentence(String sentence) {
		sentences.add(sentence);
	}
	
	@SuppressWarnings("unused")
	private static void removeGoSentence(String toRemove) {
		sentences.removeIf((e) -> e.equals(toRemove));
	}
	
	public void execute(String[] args) {
		String direction = args[1].toLowerCase();
		String nextRoom = RoomController.getExit(direction);
		if(nextRoom != null && isValidDirection(direction.toUpperCase())) {
			// Just to add a bit of colour this grabs a random sentence from the array above.
			int randNum = (int) Math.floor(((sentences.size()) * Math.random()));
			IOHandler.output.println(sentences.get(randNum) + direction.toLowerCase());
			RoomController.getNewRoom(nextRoom);
		} else {
			IOHandler.output.println("Can't go that way");
		}
	}

	public GoCmd() {
		super();
		sentences.add("Full of determination you venture ");
		sentences.add("Boldly you go ");
		sentences.add("With hope in your heart you go ");
	}
}