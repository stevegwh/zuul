package commandhandler.commandLogic;

import zuul.GameController;
import zuulutils.ZuulTools;
import commandhandler.CommandLogic;
import npc.NPC;


public class TalkCmd implements CommandLogic {
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
	public String validateUserInput(String[] inputArray) {
		// TODO Auto-generated method stub
		return null;
	}
}
