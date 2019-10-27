package command.commandController;

import com.github.cliftonlabs.json_simple.JsonObject;

import command.CommandController;
import zuul.GameController;
import zuul.TakeableItem;

// TODO: Could be cleaner
public class TakeController extends CommandController {
	protected String toTake;
	protected JsonObject obj;
	int weight;

	@Override
	public boolean execute(String[] inputArray) {
		String name = (String) obj.get("name");
		int weight = Integer.parseInt((String) obj.get("weight"));
		boolean perishable = obj.containsKey("perishable");
		TakeableItem item = null;
		if (perishable) {
			item = new TakeableItem(name, weight, true);
		} else {
			item = new TakeableItem(name, weight);
		}
		GameController.getCurrentPlayer().getInvModel().addItem(item);
		GameController.getCurrentPlayer().getInvModel().setWeight(weight);
		GameController.getRoomModel().removeTakeableItem(obj);
		return true;

	}

	@Override
	protected String validateUserInput(String[] inputArray) {
		if (inputArray.length == 1) {
			return "Take what?";
		}
		toTake = inputArray[1];
		if (!GameController.getRoomModel().hasTakeableItems()) {
			return toTake + " not in room";
		}
		obj = GameController.getRoomModel().ifItemExistsReturnIt(toTake);
		if (obj == null) {
			return toTake + " not in room";
		}
		weight = Integer.parseInt((String) obj.get("weight"));
		if (GameController.getCurrentPlayer().getInvModel().overWeightLimit(weight)) {
			return "Sorry, this item is too heavy for you to carry. Try dropping something first";
		}
		return null;
	}
}
