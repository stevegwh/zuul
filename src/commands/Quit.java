package commands;

import zuul.Game;

public class Quit implements Command {
	@Override
	public void execute(String[] inputArray) {
		Game.quit();
	}
}
