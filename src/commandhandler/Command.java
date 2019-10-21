package commandhandler;
/**
 * Interface for all Commands in the game.
 * Naming convention: command + Cmd to avoid namespace issues (InventoryController class vs InventoryController command, for example).
 * New command implementation must be placed in the commands folder of this package to be recognised.
 * 
 * @author Steve
 *
 */
public interface Command {
	void execute(String[] inputArray);
}