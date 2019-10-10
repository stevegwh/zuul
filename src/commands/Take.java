package commands;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import zuul.Inventory;
import zuul.Output;
import zuul.Room;
import zuul.TakeableItem;

//TODO check weight. Make this a method in the Inventory class
public class Take implements Command {
	@Override
	public void execute(String[] args) {
		String toTake = args[1];
		JSONArray takeableItems = Room.getTakeableItems();

		JSONObject obj = Room.ifExistsInArrayReturnObj(toTake, takeableItems);
		if (takeableItems != null && obj != null) {
			Output.println("You picked up " + toTake);
			String name = (String) obj.get("name");
			int weight = Integer.parseInt((String) obj.get("weight"));
			TakeableItem item = new TakeableItem(name, weight);
			Inventory.addItem(item);
			takeableItems.remove(obj);
			return;
		}
		Output.println("Couldn't find that.");
	}

	public Take() {
		super();
	}

}
