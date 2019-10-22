package eventHandler;

import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import IO.IOHandler;
import zuulutils.ZuulTools;

public class ConsoleEventHandler implements IEventHandler {
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
	// TODO: Should this be in the NPC class directly?
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
	public void onTake(String toTake) {
		IOHandler.output.println("You picked up " + toTake);
		
	}
	@Override
	public void onDrop(String toDrop) {
		IOHandler.output.println("You dropped " + toDrop);
		
	}
	@Override
	public void itemTooHeavy() {
		IOHandler.output.println("Sorry, this item is too heavy for you to carry. Try dropping something first");
		
	}
	@Override
	public void onRoomEnter(JsonObject roomData) {
		printSeperator();
		IOHandler.output.println((String) roomData.get("description"));
		printSeperator();
		
	}
	@Override
	public void renderActors(JsonArray actors) {
		if(actors.size() > 0) {
		// TODO: Coupled with console output... could print the people in room etc onRoomEnter in ConsoleEventHandler?
			IOHandler.output.println("People in room: ");
			actors.forEach((e) -> IOHandler.output.println((String) e));
		} else {
			IOHandler.output.println("Nobody in the room.");
		}
		printSeperator();
	}
	@Override
	public void renderItems(ArrayList<String> items) {
		if(items != null) {
			IOHandler.output.println("Items in room: ");
			items.forEach(e -> IOHandler.output.println(ZuulTools.capitalize(e)));
		} else {
			IOHandler.output.println("No items in room");
		}
		printSeperator();
	}
	@Override
	public void renderExits(JsonObject exits) {
		IOHandler.output.println("Exits: ");
		exits.forEach((k, v) -> IOHandler.output.println(ZuulTools.capitalize((String) k) + ": " + v));
		printSeperator();
	}
	@Override
	public void onGo(String direction) {
		IOHandler.output.println("You go " + direction);
	}
	@Override
	public void onGoFail() {
		IOHandler.output.println("You can't go that way.");
		printSeperator();
	}
}
