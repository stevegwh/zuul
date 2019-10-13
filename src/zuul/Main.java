package zuul;

public class Main {
	public static void main(String[] args) {		
		final boolean DEVELOPER_MODE = false;

		if(DEVELOPER_MODE) {
			ErrorCheckGameJSON.startCheck();
		} else {
			Game game = new Game();
			game.start();
		}
	}
}