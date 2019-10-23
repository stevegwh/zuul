package commandhandler.commandBases;

import zuul.GameController;
import zuul.RoomController;
import zuulutils.ZuulTools;
import commandhandler.CommandBase;
import npc.NPC;


public class TalkCmd implements CommandBase {
	protected NPC actor;
	protected String toTalk;
	// TODO: Validate prepositions etc
	@Override
	public boolean execute(String[] args) {
		String toTalk = args[2]; // talk[0] to[1] Barry[2]
		toTalk = ZuulTools.capitalize(toTalk);
		if(RoomController.hasActor(toTalk)) {
			actor = GameController.getActor(toTalk);
			return true;
		} else {
			return false;
		}
	}
}
