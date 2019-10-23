package commandhandler;

public interface FullCommand {
	public void init(String[] args);
	public void onSuccess();
	public void onFail();
}
