package commandhandler.commandBase;

import zuul.GameController;
import zuulutils.ZuulTools;
import commandhandler.Command;
import npc.NPC;


public abstract class TalkCmd extends Command {
	protected NPC actor;
	protected String toTalk;
	// TODO: Validate prepositions etc
	@Override
	public boolean execute(String[] args) {
		String toTalk = args[2]; // talk[0] to[1] Barry[2]
		toTalk = ZuulTools.capitalize(toTalk);
		if(GameController.getRoomController().hasActor(toTalk)) {
			actor = GameController.getActor(toTalk);
			return true;
		} else {
			return false;
		}
	}
	@Override
	protected String validateUserInput(String[] inputArray) {
		// TODO Auto-generated method stub
		return null;
	}
}
