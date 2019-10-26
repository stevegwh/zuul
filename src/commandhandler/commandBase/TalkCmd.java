package commandhandler.commandBase;

import zuul.GameController;
import zuulutils.ZuulTools;
import commandhandler.Command;
import npc.NPC;


public class TalkCmd extends Command {
	protected int COMMAND_LENGTH = 2;
	protected NPC actor;
	protected String toTalk;
	@Override
	public boolean execute(String[] args) {
		actor = GameController.getActor(toTalk);
		return true;
	}
	@Override
	protected String validateUserInput(String[] args) {
		if(args.length > COMMAND_LENGTH) {
			return "To talk to someone please type 'talk' followed by a name";
			
		}
		if(args.length < 2) {
			return "Talk to who?";
		}
		toTalk = args[1]; // talk[0] to[1] Barry[2]
		toTalk = ZuulTools.capitalize(toTalk);
		if(!GameController.getRoomController().hasActor(toTalk)) {
			return toTalk + " not in room.";
		}
		return null;
	}
}
