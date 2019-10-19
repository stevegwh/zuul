package zuul;

import IO.OutputHandler;

public class ZuulMessageHandler {
	public static void invalidCommand() {
		OutputHandler.println("Invalid command");
	}
	public static void printCantDoThat() {
		OutputHandler.println("Can't do that");
	}
	public static void cantFind(String e) {
		OutputHandler.println("Can't find " + e);
	}
	public static void printSeperator() {
		System.out.println("=============");
	}
}
