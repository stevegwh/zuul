package commands;

import zuul.ZuulErrorHandler;
import zuul.GameController;
import zuul.Output;
import zuul.RoomController;


import npc.NPC;

public class TalkCmd implements Command {

	@Override
	public void execute(String[] args) {
		String preposition = args[1];
		if(preposition.equals("to") || preposition.equals("with")) {
			String actorName = args[2]; // talk[0] to[1] Barry[2]
			actorName = actorName.substring(0, 1).toUpperCase() + actorName.substring(1).toLowerCase();
			if(RoomController.hasActor(actorName)) {
				NPC actor = GameController.getActor(actorName);
				actor.onTalk();
			} else {
				ZuulErrorHandler.cantFind(actorName);
			}
		} else {
			ZuulErrorHandler.invalidCommand();
			return;
		}
	}
}
