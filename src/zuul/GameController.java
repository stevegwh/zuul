package zuul;

import java.util.ArrayList;

import IO.IOHandler;
import command.game.eventOutput.GameStartOutput;
import command.game.eventOutput.LookOutput;
import command.game.eventOutput.SingleOrMultiOutput;
import npc.NPCController;

public class GameController {
	private static boolean singlePlayer;
	private CommandHandler commandHandler;
	private static RoomModel roomModel;
	private static NPCController npcController;
	private static boolean isRunning = true;
	private static Player currentPlayer;
	private static ArrayList<Player> playerArr;
	private final static String START_LOCATION = "entrance";

	public static RoomModel getRoomModel() {
		return roomModel;
	}

	public static NPCController getNPCContoller() {
		return npcController;
	}

	/**
	 * @return the currently active player.
	 */
	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @param player the next player to play.
	 */
	public static void setCurrentPlayer(Player player) {
		currentPlayer = player;
	}

	/**
	 * Breaks the main game loop.
	 */
	public static void quit() {
		isRunning = false;
		System.exit(0);
	}

	/**
	 * Sets whether the game is single player.
	 * 
	 * @param b
	 */
	public static void setSingleplayer(boolean b) {
		singlePlayer = b;
	}

	/**
	 * @return true (single-player)/false (multi-player).
	 */
	public static boolean getSinglePlayer() {
		return singlePlayer;
	}

	public static void initPlayerArr(ArrayList<Player> playerArr) {
		if (GameController.playerArr == null) {
			GameController.playerArr = playerArr;
		} else {
			System.err.println("playerArr has already been set");
		}
	}

	public static ArrayList<Player> getPlayerArr() {
		if(playerArr == null) {
			System.err.println("playerArr has not been initalised yet.");
			return null;
		}
		return playerArr;
	}

	/**
	 * Initialises the game as single or multi-player.
	 */
	private void setGameType() {
		SingleOrMultiOutput setSingleplayer = new SingleOrMultiOutput();
		setSingleplayer.init(new String[] { START_LOCATION });
		if (singlePlayer) {
			currentPlayer = new Player(START_LOCATION);
		}
	}

	public void start() {
		GameStartOutput welcome = new GameStartOutput();
		LookOutput look = new LookOutput();
		setGameType();
		npcController.init();
		welcome.init(new String[] {});
		while (isRunning) {
			roomModel.setNewRoom(getCurrentPlayer().getLocation());
			look.init(new String[] {});
			String[] inputArray = IOHandler.input.getUserInput();
			commandHandler.handleCommand(inputArray);
		}
	}

	GameController() {
		roomModel = new RoomModel(START_LOCATION);
		commandHandler = new CommandHandler();
		npcController = new NPCController();
	}

}