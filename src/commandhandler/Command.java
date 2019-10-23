package commandhandler;
/**
 * Interface for all Commands in the game.
 * Naming convention: command + Cmd to avoid namespace issues (InventoryController class vs InventoryController command, for example).
 * New command implementation must be placed in the commands folder of this package to be recognised.
 * Resolution for commands (e.g. outputting something to the player) should be handled in the eventHandler package.
 * Commands should only edit the game's state and/or data and then resolve to a method in the eventHandler package.
 * 
 * @author Steve
 *
 */
public interface Command {
	void execute(String[] inputArray);
}