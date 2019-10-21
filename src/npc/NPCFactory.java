package npc;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import zuulutils.ZuulTools;

// TODO: Find a better solution than an ignore list.
// TODO: Change this from static.
// TODO: Javadocs.
public class NPCFactory {
	private static HashMap<String, NPC> npcs = new HashMap<String, NPC>();
	

	/**
	 * Attempts to create an instance of the class found in the NPC package.
	 * @param className The current class' file name.
	 * @return An NPC object or null.
	 */
	private static NPC attemptInstantiate(String className) {
		Object npc = null;
		try {
			// TODO: Hard coded path
			npc = Class.forName(NPCFactory.class.getPackageName() + ".npcs." + className).getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (NPC) npc;
	}

	/**
	 * Scans the npc package for all npc files and stores them in the npcs HashMap
	 * All files specified in the ignoreList array above are ignored.
	 */
	private static void populateNPCArr() {
		// TODO: Hard coded path
		File file = new File("src/" + NPCFactory.class.getPackageName() + "/npcs/");
		String[] list = file.list();
		for(String item : list) {
			String[] tmp = item.split(".java");
			item = tmp[0];
			NPC npc = attemptInstantiate(item);
			if(npc != null) {
				if(npcs.containsKey(npc.getName())) {
					System.out.println("Failed to instantiate " + item + " class. Duplicate class");
					return;
				}
				
				npcs.put(npc.getName(), npc);
			} else {
				System.out.println("Failed to instantiate " + item + " class. Please check file.");
				return;
			}
		}
	}
	
	public static HashMap<String, NPC> getNPCCollection() {
		return npcs;
	}
	
	static {
		populateNPCArr();
	}
}
