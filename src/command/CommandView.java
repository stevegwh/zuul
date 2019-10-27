package command;

/**
 * Interface for handling the output to the user when a command succeeds or not.
 * Could output text, play an animation, play a sound etc.
 * 
 * @author Steve
 *
 */
public interface CommandView {
	/**
	 * Generally should call the super's validateUserInput() method and after checking no error has been returned
	 * should call the super's execute() method. After this the desired result can be output to the user.
	 * @param inputArray
	 */
	public void init(String[] inputArray);
}
