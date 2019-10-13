package commands;

import org.json.simple.JSONObject;

import zuul.Inventory;
import zuul.Output;
import zuul.Room;
import zuul.TakeableItem;

// TODO: Could be cleaner
public class Take implements Command {
	@Override
	public void execute(String[] args) {
		String toTake = args[1];
		if(Room.hasTakeableItems()) {
			JSONObject obj = Room.ifExistsInArrayReturnObj(toTake, "takeableItems");
			if (obj != null) {
				String name = (String) obj.get("name");
				int weight = Integer.parseInt((String) obj.get("weight"));
				boolean perishable = obj.containsKey("perishable");
				TakeableItem item = null;
				if (!Inventory.overWeightLimit(weight)) {
					Output.println("You picked up " + toTake);
					if(perishable) {
						item = new TakeableItem(name, weight, true);
					} else {
						item = new TakeableItem(name, weight);
					}
					Inventory.addItem(item);
					Inventory.setWeight(weight);
					Room.removeTakeableItem(obj);
				} else {
					Output.println("Sorry, this item is too heavy for you to carry. Try dropping something first");
				}
				return;
			}
		}

		Output.println("Couldn't find that.");
	}

	public Take() {
		super();
	}

}
