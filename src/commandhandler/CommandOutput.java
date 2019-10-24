package commandhandler;

/**
 * Interface for handling the output to the user when a command succeeds or not.
 * Could output text, play an animation, play a sound etc.
 * 
 * @author Steve
 *
 */
public interface CommandOutput {
	public void init(String[] inputArray);
}
