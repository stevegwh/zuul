package zuul;

public class ZuulErrorHandler {
	public static void invalidCommand() {
		Output.println("Invalid command");
	}
	public static void printCantDoThat() {
		Output.println("Can't do that");
	}
	public static void cantFind(String e) {
		Output.println("Can't find " + e);
	}

}
