package commands;

import zuul.GameController;
import zuul.RoomController;

import java.lang.reflect.InvocationTargetException;

import npc.NPC;

public class TalkCmd implements Command {

	@Override
	public void execute(String[] args) {
		// TODO: Check preposition of args[1]
		String actorName = args[2]; // talk[0] to[1] Barry[2]
		if(RoomController.hasActor(actorName)) {
			NPC actor = GameController.getActor(actorName);
			actor.onTalk();
		}
	}
}
