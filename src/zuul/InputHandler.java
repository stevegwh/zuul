package zuul;

import java.util.Scanner;

public class InputHandler {
	private final int MAX_COMMAND_LENGTH = 4;
	public String[] parseInput(String input) {
		input = input.trim().replaceAll(" +", " ");
		String[] arr = input.split(" ");
		if(arr.length > MAX_COMMAND_LENGTH) { //Maybe just say it's invalid and return empty array
			String[] truncatedArr = new String[MAX_COMMAND_LENGTH];
			System.arraycopy(arr, 0, truncatedArr, 0, MAX_COMMAND_LENGTH);
			return truncatedArr;
		} else {
			return arr;
		}
	}

	public String getInput() { 
		String data = "";
		@SuppressWarnings("resource")
		Scanner scanInput = new Scanner(System.in);
		data = scanInput.nextLine();
		scanInput.reset();
		return data;		
	}
}
