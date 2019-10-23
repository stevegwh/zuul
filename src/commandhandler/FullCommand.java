package commandhandler;

public interface FullCommand {
	void init(String[] args);
	void onSuccess();
	void onFail();
}
