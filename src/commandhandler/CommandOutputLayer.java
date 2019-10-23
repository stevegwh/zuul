package commandhandler;

public interface CommandOutputLayer {
	void init(String[] args);
	void onSuccess();
	default void onFail() { return; };
}
