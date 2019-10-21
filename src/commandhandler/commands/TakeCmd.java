package commandhandler.commands;

import com.github.cliftonlabs.json_simple.JsonObject;

import commandhandler.Command;
import eventHandler.ZuulEventHandler;
import zuul.InventoryController;
import zuul.RoomController;
import zuul.TakeableItem;

// TODO: Could be cleaner
public class TakeCmd implements Command {
	@Override
	public void execute(String[] args) {
		String toTake = args[1];
		if(RoomController.hasTakeableItems()) {
			JsonObject obj = RoomController.ifExistsInArrayReturnObj(toTake, "takeableItems");
			if (obj != null) {
				String name = (String) obj.get("name");
				int weight = Integer.parseInt((String) obj.get("weight"));
				boolean perishable = obj.containsKey("perishable");
				TakeableItem item = null;
				if (!InventoryController.overWeightLimit(weight)) {
					ZuulEventHandler.output.onTake(toTake);
					if(perishable) {
						item = new TakeableItem(name, weight, true);
					} else {
						item = new TakeableItem(name, weight);
					}
					InventoryController.addItem(item);
					InventoryController.setWeight(weight);
					RoomController.removeTakeableItem(obj);
				} else {
					ZuulEventHandler.output.itemTooHeavy();
				}
				return;
			}
		}

		ZuulEventHandler.output.cantFind(args[1]);
	}
}
