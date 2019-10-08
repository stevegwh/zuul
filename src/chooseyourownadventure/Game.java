package chooseyourownadventure;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
	private InputHandler inputHandler;
	private GameLoader gameLoader;
	private Room room;
	private CommandHandler commandHandler;
	private static ArrayList<String> inventory;
	private static HashMap<String, InteractableItem> interactableItems;
	private static HashMap<String, TakeableItem> takeableItems;

	private static boolean isRunning = true;

	
	public static ArrayList<String> getInventory() {
		return inventory;
	}

	public static HashMap<String, InteractableItem> getInteractableItems() {
		return interactableItems;
	}

	public static HashMap<String, TakeableItem> getTakeableItems() {
		return takeableItems;
	}

	static void quit() {
		Output.println("Thanks for playing!");
		isRunning = false;
	}

	public void start() {
		while(isRunning) {
			room.printDescription();
			Output.printf(">> ");
			String input = inputHandler.getInput();
			String[] inputArray = inputHandler.parseInput(input);
			commandHandler.handleCommand(input, inputArray);
		}
	}

	Game() {
		inputHandler = new InputHandler();
		gameLoader = new GameLoader();
		room = gameLoader.getRoom();
		commandHandler = new CommandHandler(room);
		inventory = new ArrayList<String>();
		//TEMPORARY
		interactableItems = gameLoader.getInteractableItems();
		InteractableItem tmp1 = new InteractableItem();
		interactableItems.put("bush", tmp1);
		takeableItems = gameLoader.getTakeableItems();
		TakeableItem tmp2 = new TakeableItem("chair", 10);
		takeableItems.put("chair", tmp2);
		//END
//		gameLoader.getHashMap("interactableObjects");
		
	}

}
