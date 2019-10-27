package command.consoleCommandView;

import com.github.cliftonlabs.json_simple.JsonObject;

import IO.IOHandler;
import command.CommandOutput;
import command.commandController.LookController;
import zuulutils.ZuulTools;

public class LookOutput extends LookController implements CommandOutput {
	public void init(String[] inputArray) {
		if (super.execute(inputArray)) {
			IOHandler.output.println("---------");
			IOHandler.output.println(description);
			IOHandler.output.printf("People in room: ");
			actors.forEach((e) -> IOHandler.output.printf((String) e + ", "));
			IOHandler.output.println(" ");
			if (items != null) {
				IOHandler.output.printf("Items in room: ");
				items.forEach(e -> IOHandler.output.printf(ZuulTools.capitalize(((JsonObject) e).get("name").toString())
						+ "(" + ((JsonObject) e).get("weight").toString() + "), "));
			} else {
				IOHandler.output.printf("No items in room");
			}
			IOHandler.output.println(" ");
			IOHandler.output.printf("Exits: ");
			exits.forEach((k, v) -> IOHandler.output.printf(ZuulTools.capitalize((String) k) + ", "));
			IOHandler.output.println(" ");
			IOHandler.output.println("---------");
		}
	}

}
