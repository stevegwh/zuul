package command.gameCommand.commandController;

import command.CommandController;
import zuul.GameController;

/**
 * Validates user's choice and sets single or multi-player game.
 * 
 * @author Steve
 *
 */
public class SingleOrMultiController extends CommandController {
	protected String choice;
	
	@Override
	protected String validateUserInput(String[] choiceArr) {
		choice = choiceArr[0].toString();
		if(choice.equals("1") && choiceArr.length <= 1) {
			choice = "1";
			return null;
		} else if(choice.equals("2") && choiceArr.length <= 1) {
			choice = "2";
			return null;
		}
		return "Invalid option. Please try again.";
	}

	@Override
	protected boolean execute(String[] inputArray) {
		if(choice.equals("1")) {
			GameController.setSingleplayer(true);
			return true;
		} else { 
			GameController.setSingleplayer(false);
			return true;
		}
	}

}
