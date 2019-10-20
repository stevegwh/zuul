package zuul;

import IO.IOHandler;

public class ZuulMessageHandler {
	public static void invalidCommand() {
		IOHandler.output.println("Invalid command");
	}
	public static void printCantDoThat() {
		IOHandler.output.println("Can't do that");
	}
	public static void cantFind(String e) {
		IOHandler.output.println("Can't find " + e);
	}
	public static void printSeperator() {
		IOHandler.output.println("=============");
	}
}
