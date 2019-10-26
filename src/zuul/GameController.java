package zuul;

import java.util.HashMap;

import IO.IOHandler;
//import eventHandler.ZuulEventRouter;
import npc.NPC;
import npc.NPCFactory;

public class GameController {
	private static HashMap<String, NPC> actors = new HashMap<>();
	private static GameController SINGLE_INSTANCE = null;
	private CommandHandler commandHandler;
	private static RoomController roomController;
	private static boolean isRunning = true;
	private static Player currentPlayer;
	private final String START_LOCATION = "lecture";
	
	public static RoomController getRoomController() {
		return roomController;
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
    	actors.keySet().forEach(a->actors.get(a).update());
    }
    public static NPC getActor(String actorName) {
    	return actors.get(actorName);
    }
	public void start() {
		while(isRunning) {
			updateActors();
			String[] inputArray = IOHandler.input.getUserInput();
			commandHandler.handleCommand(inputArray);
		}
	}
	GameController() {
		commandHandler = new CommandHandler();
		roomController = new RoomController(START_LOCATION);
		currentPlayer = new Player(START_LOCATION);
		NPCFactory npcFactory = new NPCFactory();
		actors = npcFactory.getNPCCollection();
		// TODO: Game onStart method
	}
}
