package zuul;

import IO.IOHandler;
import eventHandler.ZuulEventHandler;
//import jsonDataHandler.ErrorCheckGameJSON;

public class Main {
	private static void printUsage() {
		System.out.println("Usage: --io <IO Mode>");
		
	}

	public static void main(String[] args) {		
		boolean defaultUsed = false;
		boolean DEVELOPER_MODE = false;
		// TODO: Tidy
		if(args.length != 0) {
			for(int i = 0, len = args.length; i < len ; i++) {
				if(args[i].equals("--io")) {
					if(i != len - 1) {
						IOHandler.setIOMode(args[i+1]);
						ZuulEventHandler.setIOMode(args[i+1]);
					} else {
						printUsage();
						return;
					}
				} else if(args[i].equals("--dev")) {
					DEVELOPER_MODE = true;
				}
			}
		}
		
		// Set default output mode if none specified.
		if(IOHandler.output == null) {
			IOHandler.setIOMode("Console");
			ZuulEventHandler.setIOMode("Console");
			defaultUsed = true;
		}


		if(DEVELOPER_MODE) {
			if(defaultUsed) {
				System.out.println("No output mode specified. Using the default 'Console'");
			}
//			ErrorCheckGameJSON errorChecker = new ErrorCheckGameJSON();
//			errorChecker.startCheck();
		}
		GameController game = new GameController();
		game.start();
	}
}