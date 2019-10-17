package zuul;

import java.util.HashMap;

import npc.Barry;
import npc.NPC;

public class GameController {
	private static HashMap<String, NPC> actors = new HashMap<>();
	private static GameController SINGLE_INSTANCE = null;
	private InputHandler inputHandler;
	private CommandHandler commandHandler;
	private int MAX_COMMAND_LENGTH = 4;
	private static boolean isRunning = true;

	public static void quit() {
		Output.println("Thanks for playing!");
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
		for(String actor : actors.keySet()) {
			actors.get(actor).update();
		}
    }
    
    public static NPC getActor(String actorName) {
    	return actors.get(actorName);
    }

	public void start() {
		while(isRunning) {
			Output.printSeperator();
			RoomController.printDescription();
			Output.printf(">> ");
			String[] inputArray = inputHandler.parseInput(inputHandler.getInput(), MAX_COMMAND_LENGTH);
			Output.printSeperator();
			commandHandler.handleCommand(inputArray);
			updateActors();
		}
	}

	GameController() {
		inputHandler = new InputHandler();
		commandHandler = new CommandHandler();
		actors.put("Barry", Barry.getInstance());
	}

}