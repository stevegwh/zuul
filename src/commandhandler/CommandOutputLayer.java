package commandhandler;

// TODO: Change commandHandler.commandOutputLayers to commandHandler + ioMode + OutputLayers
// TODO: Move to Output Layer package to see if visibility is restricted or not.
public interface CommandOutputLayer {
	void init(String[] args);
	void onSuccess();
	default void onFail() { return; };
}
