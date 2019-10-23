package eventHandler;

import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import zuul.TakeableItem;
/**
 * Interface for all types of events that can happen in the game. Designed to allow future developers to implement any resolution to the event they wish.
 * For example, you could quite easily resolve the event by playing sounds, starting an animation etc.
 * All implementations must use the same prefix as specified in the --io flag when executing the game.
 * Example: '--io Console' would need a 'ConsoleEventHandler' implementation.
 * 
 * @author Steve
 *
 */
public interface IEventHandler {
	public void invalidCommand();
	public void printCantDoThat();
	public void cantFind(String e);
	public void notInInventory(String itemName);
	public void onGiveFail(String name, String itemName);
	public void printSeperator();
	public void quitGame();
	public void onTake(String toTake);
	public void onTakeFail();
	public void onDrop(String toDrop);
	public void onRoomEnter(JsonObject roomData);
	public void renderExits(JsonObject exits);
	public void renderActors(JsonArray actors);
	public void renderItems(ArrayList<String> items);
	public void onGo(String direction);
	public void onGoFail();
	public void onInventory(ArrayList<TakeableItem> inventory);
	public void onRemoveFromInventory(String itemName);
	public void onInventoryFail();
	public void onDoorUnlock();
	public void onInvestigate(String descriptionOnInvestigate);
	public void renderDialogOptions(JsonArray dialogOptions);
	public void renderDialogResponse(String response);
	public void onLook(String lookDescription);
	public void onLookFail();
}
// TODO: rethink some of these method names to be more inclusive of different outputs