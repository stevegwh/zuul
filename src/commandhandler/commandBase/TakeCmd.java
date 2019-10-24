package commandhandler.commandBase;

import com.github.cliftonlabs.json_simple.JsonObject;

import commandhandler.Command;
import zuul.GameController;
import zuul.TakeableItem;

// TODO: Could be cleaner
public abstract class TakeCmd extends Command {
	protected String toTake;
	@Override
	public boolean execute(String[] args) {
		toTake = args[1];
		if(GameController.getRoomController().hasTakeableItems()) {
			JsonObject obj = GameController.getRoomController().ifItemExistsReturnIt(toTake);
			if (obj != null) {
				String name = (String) obj.get("name");
				int weight = Integer.parseInt((String) obj.get("weight"));
				boolean perishable = obj.containsKey("perishable");
				TakeableItem item = null;
				if (!GameController.getCurrentPlayer().getInvController().overWeightLimit(weight)) {
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
		}
		return false;

		// TODO: Not been covered by subclass
//		ZuulEventRouter.output.cantFind(args[1]);
	}
	@Override
	protected String validateUserInput(String[] inputArray) {
		if(inputArray.length == 1) {
			return "Take what?";
		}
		return null;
	}
}
