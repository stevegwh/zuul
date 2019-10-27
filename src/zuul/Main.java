package zuul;

import IO.IOHandler;
//import jsonDataHandler.ErrorCheckGameJSON;

public class Main {
	private static void printUsage() {
		System.out.println("Help:");
		System.out.println("--io set the input/output mode. E.g. --io Console");
		System.out.println("--dev see developer warnings");
	}

	public static void main(String[] args) {
		String ioMode = null;
		boolean defaultUsed = false;
		boolean DEVELOPER_MODE = false;
		if (args.length != 0) {
			for (int i = 0, len = args.length; i < len; i++) {
				if (args[i].equals("--io")) {
					if (i != len - 1) {
						ioMode = args[i + 1];
						IOHandler.setIOMode(ioMode);
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
			ioMode = "console";
			defaultUsed = true;
		}

		if (DEVELOPER_MODE) {
			if (defaultUsed) {
				System.out.println("No output mode specified. Using the default 'Console'");
			}
//			ErrorCheckGameJSON errorChecker = new ErrorCheckGameJSON();
//			errorChecker.startCheck();
		}
		GameController game = new GameController();
		game.start(ioMode);
	}
}