package zuul;

public class Game {
	private InputHandler inputHandler;
	private CommandHandler commandHandler;
	private static boolean isRunning = true;

	static void quit() {
		Output.println("Thanks for playing!");
		isRunning = false;
	}

	public void start() {
		while(isRunning) {
			Room.printDescription();
			Output.printf(">> ");
			String input = inputHandler.getInput(); //could be simplified to one command
			String[] inputArray = inputHandler.parseInput(input);
			commandHandler.handleCommand(inputArray);
		}
	}

	Game() {
		inputHandler = new InputHandler();
		commandHandler = new CommandHandler();
	}

}
