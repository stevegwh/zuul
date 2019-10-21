package eventHandler;

import IO.IOHandler;

// TODO: Think about how these messages could be used in a GUI. Maybe make an interface and depending on Console/GUI etc you'll handle it differently?
public class ZuulEventHandler {
	public static void invalidCommand() {
		IOHandler.output.println("Invalid command");
	}
	public static void printCantDoThat() {
		IOHandler.output.println("Can't do that");
	}
	public static void cantFind(String e) {
		IOHandler.output.println("Can't find " + e);
	}
	public static void notInInventory(String itemName) {
		IOHandler.output.println("You do not have a " + itemName + " in your inventory");
	}
	public static void onGiveFail(String name, String itemName) {
		IOHandler.output.println(name + " didn't seem to want the " + itemName);
	}
	public static void onRemoveFromInventory(String itemName) {
		IOHandler.output.println(itemName + " was removed from your inventory");
	}
	public static void printSeperator() {
		IOHandler.output.println("=============");
	}
	public static void quitGame() {
		IOHandler.output.println("Thanks for playing!");
		
	}
}
