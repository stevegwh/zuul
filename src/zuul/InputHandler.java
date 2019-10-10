package zuul;

import java.util.Scanner;

public class InputHandler {
	public String[] parseInput(String input) {
//		input = input.toUpperCase();
		input = input.trim().replaceAll(" +", " ");
		String[] arr = input.split(" ");
		return arr;
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
