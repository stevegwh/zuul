package chooseyourownadventure;

public class Output {
	public static void print(String toOutput) {
		System.out.println(toOutput);
		
	}
	
	public static void print(int toOutput) {
		System.out.println(toOutput);
	}

	public static void print(String toOutput, boolean noNewLine) {
		System.out.printf(toOutput);
	}
}
