package zuul;

public class Main {
	public static void main(String[] args) {		
		final boolean DEVELOPER_MODE = true;

		if(DEVELOPER_MODE) {
			ErrorCheckGameJSON.startCheck();
		}
		Game game = new Game();
		game.start();
	}
}