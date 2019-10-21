package commands;

import zuul.GameController;
import zuul.RoomController;
import zuulutils.ZuulEventHandler;
import zuulutils.ZuulTools;
import npc.NPC;

public class TalkCmd implements Command {

	@Override
	public void execute(String[] args) {
		String preposition = args[1];
		if(preposition.equals("to") || preposition.equals("with")) {
			String actorName = args[2]; // talk[0] to[1] Barry[2]
			actorName = ZuulTools.capitalize(actorName);
			if(RoomController.hasActor(actorName)) {
				NPC actor = GameController.getActor(actorName);
				actor.onTalk();
			} else {
				ZuulEventHandler.cantFind(actorName);
			}
		} else {
			ZuulEventHandler.invalidCommand();
			return;
		}
	}
}
