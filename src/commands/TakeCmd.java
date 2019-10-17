package commands;

import org.json.simple.JSONObject;

import zuul.InventoryController;
import zuul.Output;
import zuul.RoomController;
import zuul.TakeableItem;
import zuul.ZuulErrorHandler;

// TODO: Could be cleaner
public class TakeCmd implements Command {
	@Override
	public void execute(String[] args) {
		String toTake = args[1];
		if(RoomController.hasTakeableItems()) {
			JSONObject obj = RoomController.ifExistsInArrayReturnObj(toTake, "takeableItems");
			if (obj != null) {
				String name = (String) obj.get("name");
				int weight = Integer.parseInt((String) obj.get("weight"));
				boolean perishable = obj.containsKey("perishable");
				TakeableItem item = null;
				if (!InventoryController.overWeightLimit(weight)) {
					Output.println("You picked up " + toTake);
					if(perishable) {
						item = new TakeableItem(name, weight, true);
					} else {
						item = new TakeableItem(name, weight);
					}
					InventoryController.addItem(item);
					InventoryController.setWeight(weight);
					RoomController.removeTakeableItem(obj);
				} else {
					Output.println("Sorry, this item is too heavy for you to carry. Try dropping something first");
				}
				return;
			}
		}

		ZuulErrorHandler.cantFind(args[1]);
	}

	public TakeCmd() {
		super();
	}

}
