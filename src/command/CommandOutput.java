package command;

/**
 * Interface for handling the output to the user when a command succeeds or not.
 * Could output text, play an animation, play a sound etc.
 * Any new command output must be placed in a sub-directory of command called "viewName + CommandView"
 * The default view is 'Console' (std out) therefore: consoleCommandView.
 * The class must have the suffix "Output", e.g. LookOutput.
 * Specify the desired view with the --view flag when executing game.
 * 
 * @author Steve
 *
 */
public interface CommandOutput {
	/**
	 * Generally should call the super's validateUserInput() method and after
	 * checking no error has been returned should call the super's execute() method.
	 * After this the desired result can be output to the user.
	 * 
	 * @param inputArray
	 */
	public void init(String[] inputArray);
}
