package commands;
// Naming convention: command + Cmd to avoid namespace issues (Inventory class vs Inventory command, for example)
public interface Command {
	void execute(String[] inputArray);
}