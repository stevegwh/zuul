package zuul;

import jsonDataHandler.ErrorCheckGameJSON;

public class Main {
	public static void main(String[] args) {		
		final boolean DEVELOPER_MODE = false;

		if(DEVELOPER_MODE) {
			ErrorCheckGameJSON errorChecker = new ErrorCheckGameJSON();
			errorChecker.startCheck();
		} else {
			GameController game = new GameController();
			game.start();
		}
	}
}