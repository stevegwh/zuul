package chooseyourownadventure;

public class Game {
	private InputHandler inputHandler;
	private GameLoader gameLoader;
	private Room room;
	private CommandHandler commandHandler;
	private static boolean isRunning = true;

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
	}

}
