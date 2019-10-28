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

	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	public static void setCurrentPlayer(Player player) {
		currentPlayer = player;
	}

	public static void quit() {
		isRunning = false;
	}

	public static void setSingleplayer(boolean b) {
		singlePlayer = b;
	}

	public static boolean getSinglePlayer() {
		return singlePlayer;
	}

	public static void initPlayers() {
		for (int i = 0; i < 2; i++) {
			playerArr.add(new Player(START_LOCATION));
		}
		currentPlayer = playerArr.get(0);
		int idx = playerArr.indexOf(getCurrentPlayer());
		IOHandler.output.println("Player " + (idx + 1) + "'s turn");
																// 1
	}

	public static void nextPlayerTurn() {
		int idx = playerArr.indexOf(getCurrentPlayer());
		System.out.println(idx);
		idx += 1;
		System.out.println(idx);
		if (idx >= playerArr.size()) {
			idx = 0;
		}
		System.out.println(idx);
		Player player = playerArr.get(idx);
		setCurrentPlayer(player);
		IOHandler.output.println("Player " + (idx + 1) + "'s turn");
	}

	public void start() {
		SingleOrMultiOutput setSingleplayer = new SingleOrMultiOutput();
		setSingleplayer.init(new String[] {});
		if (!singlePlayer) {
			initPlayers();
		} else {
			currentPlayer = new Player(START_LOCATION);
		}
		npcController.init();
		GameStartOutput welcome = new GameStartOutput();
		LookOutput look = new LookOutput();
		welcome.init(new String[] {});
		while (isRunning) {
			roomModel.setNewRoom(getCurrentPlayer().getLocation());
			look.init(new String[] { "look" });
			String[] inputArray = IOHandler.input.getUserInput();
			commandHandler.handleCommand(inputArray);
		}
	}

	GameController() {
		roomModel = new RoomModel(START_LOCATION);
		commandHandler = new CommandHandler();
		npcController = new NPCController();
	}

	public static boolean isRunning() {
		return isRunning;
	}

}