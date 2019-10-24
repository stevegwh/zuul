package commandhandler.consoleCommandOutput;

import IO.IOHandler;
import commandhandler.CommandOutput;
import commandhandler.commandBase.InventoryCmd;
import zuulutils.ZuulTools;

public class InventoryCmdOutput extends InventoryCmd implements CommandOutput {

	public void init(String[] args) {
		if(super.execute(args)) {
			IOHandler.output.println("You are currently carrying: ");
			inventory.forEach(s-> IOHandler.output.println(ZuulTools.capitalize(s.getName())));
		} else {
			IOHandler.output.println("You do not currently have anything in your inventory");
		}
		
	}
}
