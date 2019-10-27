package zuul;

import IO.IOHandler;
import command.gameCommand.commandView.GameStartOutput;
import command.gameCommand.commandView.LookOutput;
import npc.NPCController;

public class GameController {
	private CommandHandler commandHandler;
	private static RoomModel roomModel;
	private static NPCController npcController;
	private static boolean isRunning = true;
	private static Player currentPlayer;
	private final String START_LOCATION = "entrance";

	public static RoomModel getRoomModel() {
		return roomModel;
	}
	
	public static NPCController getNPCContoller() {
		return npcController;
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
	
	public void start() {
		GameStartOutput welcome = new GameStartOutput();
		LookOutput look = new LookOutput();
		welcome.init(new String[] {});
		while (isRunning) {
			look.init(new String[] {"look"});
			String[] inputArray = IOHandler.input.getUserInput();
			commandHandler.handleCommand(inputArray);
		}
	}

	GameController() {
		roomModel = new RoomModel(START_LOCATION);
		currentPlayer = new Player(START_LOCATION);
		commandHandler = new CommandHandler();
		npcController = new NPCController();
	}

	public static boolean isRunning() {
		return isRunning;
	}
}
