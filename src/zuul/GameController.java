package zuul;

import java.util.HashMap;

import IO.IOHandler;
import eventHandler.ZuulEventHandler;
import npc.NPC;
import npc.NPCFactory;

public class GameController {
	private static HashMap<String, NPC> actors = new HashMap<>();
	private static GameController SINGLE_INSTANCE = null;
	private CommandHandler commandHandler;
	private int MAX_COMMAND_LENGTH = 4;
	private static boolean isRunning = true;
	private static Player currentPlayer;
	
	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	public static void quit() {
		ZuulEventHandler.output.quitGame();
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
//		for(String actor : actors.keySet()) {
//			actors.get(actor).update();
//		}
    	actors.get("Barry").move("room3");
    }
    
    public static NPC getActor(String actorName) {
    	return actors.get(actorName);
    }
    
	public void start() {
		while(isRunning) {
			updateActors();
			String[] inputArray = IOHandler.input.getUserInput(MAX_COMMAND_LENGTH);
			commandHandler.handleCommand(inputArray);
		}
	}

	GameController() {
		commandHandler = new CommandHandler();
		actors = NPCFactory.getNPCCollection();
		currentPlayer = new Player();
	}

}
