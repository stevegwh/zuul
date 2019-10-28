package zuul;

import java.util.ArrayList;

import IO.IOHandler;
import command.gameCommand.commandView.GameStartOutput;
import command.gameCommand.commandView.LookOutput;
import command.gameCommand.commandView.SingleOrMultiOutput;
import npc.NPCController;

public class GameController {
	private static boolean singlePlayer;
	private CommandHandler commandHandler;
	private static RoomModel roomModel;
	private static NPCController npcController;
	private static boolean isRunning = true;
	private static Player currentPlayer;
	private static ArrayList<Player> playerArr = new ArrayList<>();
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

	/**
	 * Instantiates n number of players for the game.
	 */
	private static void initPlayers() {
		for (int i = 0; i < 2; i++) {
			playerArr.add(new Player(START_LOCATION));
		}
		currentPlayer = playerArr.get(0);
		int idx = playerArr.indexOf(getCurrentPlayer());
		IOHandler.output.println("Player " + (idx + 1) + "'s turn");
	}

	/**
	 * Sets the currentPlayer variable to the next Player object in the playerArr
	 * array.
	 */
	public static void nextPlayerTurn() {
		int idx = playerArr.indexOf(getCurrentPlayer());
		idx += 1;
		if (idx >= playerArr.size()) {
			idx = 0;
		}
		Player player = playerArr.get(idx);
		setCurrentPlayer(player);
		IOHandler.output.println("Player " + (idx + 1) + "'s turn");
	}

	/**
	 * Initialises the game as single or multi-player.
	 */
	private void setGameType() {
		SingleOrMultiOutput setSingleplayer = new SingleOrMultiOutput();
		setSingleplayer.init(new String[] {});
		if (!singlePlayer) {
			initPlayers();
		} else {
			currentPlayer = new Player(START_LOCATION);
		}
	}

	public void start() {
		setGameType();
		npcController.init();
		GameStartOutput welcome = new GameStartOutput();
		LookOutput look = new LookOutput();
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