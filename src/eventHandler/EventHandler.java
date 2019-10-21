package eventHandler;

import com.github.cliftonlabs.json_simple.JsonObject;

public interface EventHandler {
	public void invalidCommand();
	public void printCantDoThat();
	public void cantFind(String e);
	public void notInInventory(String itemName);
	public void onGiveFail(String name, String itemName);
	public void onRemoveFromInventory(String itemName);
	public void printSeperator();
	public void quitGame();
	public void renderExits(JsonObject exits);
	public void onTake(String toTake);
}
