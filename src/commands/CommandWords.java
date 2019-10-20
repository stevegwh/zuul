//package commands;
//
//import java.util.ArrayList;
//
//import IO.IOHandler;;
//
//public class CommandWords {
//	private static ArrayList<String> commands = new ArrayList<String>();
//
//	public static void addCommand(String command) {
//		commands.add(command);
//	}
//	public static void removeCommand(String command) {
//		commands.remove(command);
//	}
//	public static boolean commandExists(String command) {
//		return commands.indexOf(command) >= 0;
//	}
//	public static void printAll() {
//		commands.forEach((e) -> IOHandler.output.println(e));
//	}
//	static {
//		commands.add("USE");
//		commands.add("INVESTIGATE");
//		commands.add("QUIT");
//		commands.add("TAKE");
//		commands.add("GO");
//		commands.add("HELP");
//		commands.add("INVENTORY");
//		commands.add("DROP");
//		commands.add("GIVE");
//		commands.add("LOOK");
//		commands.add("TALK");
//	}
//}
