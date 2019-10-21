package eventHandler;

import com.github.cliftonlabs.json_simple.JsonObject;

import IO.IOHandler;
import zuulutils.ZuulTools;

public class ConsoleEventHandler implements EventHandler {
	@Override
	public void invalidCommand() {
		IOHandler.output.println("Invalid command");
	}
	@Override
	public void printCantDoThat() {
		IOHandler.output.println("Can't do that");
	}
	@Override
	public void cantFind(String e) {
		IOHandler.output.println("Can't find " + e);
	}
	@Override
	public void notInInventory(String itemName) {
		IOHandler.output.println("You do not have a " + itemName + " in your inventory");
	}
	@Override
	public void onGiveFail(String name, String itemName) {
		IOHandler.output.println(name + " didn't seem to want the " + itemName);
	}
	@Override
	public void onRemoveFromInventory(String itemName) {
		IOHandler.output.println(itemName + " was removed from your inventory");
	}
	// TODO: This is a console only command but it's tied into your game right now
	@Override
	public void printSeperator() {
		IOHandler.output.println("=============");
	}
	@Override
	public void quitGame() {
		IOHandler.output.println("Thanks for playing!");
	}
	@Override
	public void renderExits(JsonObject exits) {
		IOHandler.output.println("Exits: ");
		exits.forEach((k, v) -> IOHandler.output.println(ZuulTools.capitalize((String) k) + ": " + v));
	}
	@Override
	public void onTake(String toTake) {
		IOHandler.output.println("You picked up " + toTake);
		
	}
}
