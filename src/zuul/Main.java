package zuul;

import IO.IOHandler;
//import jsonDataHandler.ErrorCheckGameJSON;

public class Main {
	private static void printUsage() {
		System.out.println("Usage: --io <IO Mode>");
		
	}
	private enum flags {
		IO, DEV;
	}
	public static void main(String[] args) {		
		boolean defaultUsed = false;
		final boolean DEVELOPER_MODE = false;
		if(args.length != 0) {
			// TODO: loop through all possible flags, not just the hardcoded 'io' flag
			for(int i = 0, len = args.length; i < len ; i++) {
				if(args[i].equals("--io")) {
					if(i != len - 1) {
						IOHandler.setIOMode(args[i+1]);
					} else {
						printUsage();
						return;
					}
				}
			}
		}
		
		// Set default output mode if none specified.
		if(IOHandler.output == null) {
			IOHandler.setIOMode("Console");
			defaultUsed = true;
		}


		if(DEVELOPER_MODE) {
			System.out.println("No output mode specified. Using the default 'Console'");
//			ErrorCheckGameJSON errorChecker = new ErrorCheckGameJSON();
//			errorChecker.startCheck();
		} else {
			GameController game = new GameController();
			game.start();
		}
	}
}