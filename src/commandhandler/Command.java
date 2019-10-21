package commandhandler;
// Naming convention: command + Cmd to avoid namespace issues (InventoryController class vs InventoryController command, for example)
public interface Command {
	void execute(String[] inputArray);
}