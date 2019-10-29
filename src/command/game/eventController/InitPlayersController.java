package command.game.eventController;

import java.util.ArrayList;

import command.CommandController;
import zuul.GameController;
import zuul.Player;

public class InitPlayersController extends CommandController {
	protected int idx;

	@Override
	protected String validateUserInput(String[] inputArray) {
		return null;
	}

	@Override
	protected boolean execute(String[] inputArray) {
		String START_LOCATION = inputArray[0];
		ArrayList<Player> playerArr = GameController.getPlayerArr();
		for (int i = 0; i < 2; i++) {
			playerArr.add(new Player(START_LOCATION));
		}
		GameController.setCurrentPlayer(playerArr.get(0));
		idx = playerArr.indexOf(GameController.getCurrentPlayer());
		return false;
	}

}
