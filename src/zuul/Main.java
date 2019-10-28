package zuul;

import IO.IOHandler;

public class Main {
	private static void printUsage() {
		System.out.println("Help:");
		System.out.println("--view sets the view (output) of the game. E.g. --view Console");
		System.out.println("--dev see developer warnings");
	}

	public static void main(String[] args) {
		boolean defaultUsed = false;
		boolean DEVELOPER_MODE = false;
		if (args.length != 0) {
			for (int i = 0, len = args.length; i < len; i++) {
				if (args[i].equals("--view")) {
					if (i != len - 1) {
						IOHandler.setIOMode(args[i + 1]);
					} else {
						printUsage();
						return;
					}
				} else if (args[i].equals("--dev")) {
					DEVELOPER_MODE = true;
				}
			}
		}

		// Set default output mode if none specified.
		if (IOHandler.output == null) {
			IOHandler.setIOMode("Console");
			defaultUsed = true;
		}

		if (DEVELOPER_MODE) {
			if (defaultUsed) {
				System.out.println("No output mode specified. Using the default 'Console'");
			}
		}
		GameController game = new GameController();
		game.start();
	}
}