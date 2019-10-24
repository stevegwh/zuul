package commandhandler;
/**
 * Abstract base class for all Commands in the game.
 * 
 * @author Steve
 *
 */
public abstract class Command {
	// Implemented by Command's logic stub
	abstract protected String validateUserInput(String[] inputArray);
	abstract protected boolean execute(String[] inputArray);
	// Implemented by Command's Output class
//	abstract public void init(String[] inputArray);
//	abstract protected void onSuccess();
//	abstract protected void onFail();
}