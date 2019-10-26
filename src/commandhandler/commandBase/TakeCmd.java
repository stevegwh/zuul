package commandhandler.commandBase;

import com.github.cliftonlabs.json_simple.JsonObject;

import commandhandler.Command;
import zuul.GameController;
import zuul.TakeableItem;

// TODO: Could be cleaner
public class TakeCmd extends Command {
	protected String toTake;
	protected JsonObject obj;
	@Override
	public boolean execute(String[] inputArray) {
		String name = (String) obj.get("name");
		int weight = Integer.parseInt((String) obj.get("weight"));
		boolean perishable = obj.containsKey("perishable");
		if (!GameController.getCurrentPlayer().getInvController().overWeightLimit(weight)) {
			TakeableItem item = null;
			if(perishable) {
				item = new TakeableItem(name, weight, true);
			} else {
				item = new TakeableItem(name, weight);
			}
			GameController.getCurrentPlayer().getInvController().addItem(item);
			GameController.getCurrentPlayer().getInvController().setWeight(weight);
			GameController.getRoomController().removeTakeableItem(obj);
			return true;
		} else {
			return false;
		}

	}
	@Override
	protected String validateUserInput(String[] inputArray) {
		if(inputArray.length == 1) {
			return "Take what?";
		}
		toTake = inputArray[1];
		if(!GameController.getRoomController().hasTakeableItems()) {
			return toTake + " not in room";
		}
		obj = GameController.getRoomController().ifItemExistsReturnIt(toTake);
		if (obj == null) {
			return toTake + " not in room";
		}
		return null;
	}
}
