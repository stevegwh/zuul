package zuul;

import java.util.HashMap;

import IO.IOHandler;
import command.gameCommand.commandView.GameStartOutput;
import command.gameCommand.commandView.LookOutput;
import npc.NPC;
import npc.NPCFactory;

public class GameController {
	private static HashMap<String, NPC> actors = new HashMap<>();
	private static CommandHandler commandHandler;
	private static RoomModel roomModel;
	private static boolean isRunning = true;
	private static Player currentPlayer;
	private final String START_LOCATION = "entrance";

	public static RoomModel getRoomModel() {
		return roomModel;
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

	private void updateActors() {
		actors.keySet().forEach(a -> actors.get(a).update());
	}

	public static NPC getActor(String actorName) {
		return actors.get(actorName);
	}
	
	public void start() {
		GameStartOutput welcome = new GameStartOutput();
		LookOutput look = new LookOutput();
		welcome.init(new String[] {});
		while (isRunning) {
			look.init(new String[] {"look"});
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
