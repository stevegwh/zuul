package zuul;

//import npc.Barry;

public class Game {
	private InputHandler inputHandler;
	private CommandHandler commandHandler;
	private static boolean isRunning = true;

	public static void quit() {
		Output.println("Thanks for playing!");
		isRunning = false;
	}

	public void start() {
		while(isRunning) {
//			Barry barry = Barry.getInstance();
//			barry.beginDialog();
//			barry.onInvestigate();
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
