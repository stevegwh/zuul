package zuul;

import java.util.HashMap;

import IO.IOHandler;
import npc.NPC;
import npc.NPCFactory;

public class GameController {
	private static String viewName;
	private static HashMap<String, NPC> actors = new HashMap<>();
	private static GameController SINGLE_INSTANCE = null;
	private static CommandHandler commandHandler;
	private static RoomModel roomModel;
	private static boolean isRunning = true;
	private static Player currentPlayer;
	private final String START_LOCATION = "entrance";

	public static RoomModel getRoomModel() {
		return roomModel;
	}

	public static String getView() {
		return viewName;
	}

	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	public static void setCurrentPlayer(Player player) {
		currentPlayer = player;
	}

	public static void quit() {
		isRunning = false;
	}

	public static void restart() {
		SINGLE_INSTANCE = new GameController();
	}

	public static GameController getInstance() {
		if (SINGLE_INSTANCE == null) {
			synchronized (GameController.class) {
				if (SINGLE_INSTANCE == null) {
					SINGLE_INSTANCE = new GameController();
				}
			}
		}
		return SINGLE_INSTANCE;
	}

	private void updateActors() {
		actors.keySet().forEach(a -> actors.get(a).update());
	}

	public static NPC getActor(String actorName) {
		return actors.get(actorName);
	}
	
	public void start(String viewName) {
		GameController.viewName = viewName.toLowerCase();
		while (isRunning) {
			String[] wrapper = { "look" };
			commandHandler.handleCommand(wrapper);
			updateActors();
			String[] inputArray = IOHandler.input.getUserInput();
			commandHandler.handleCommand(inputArray);
		}
	}

	GameController() {
		commandHandler = new CommandHandler();
		roomModel = new RoomModel(START_LOCATION);
		currentPlayer = new Player(START_LOCATION);
		NPCFactory npcFactory = new NPCFactory();
		actors = npcFactory.getNPCCollection();
	}
}
