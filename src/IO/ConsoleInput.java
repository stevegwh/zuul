package IO;

import java.util.Scanner;


// TODO: Currently this allows you to write things like 'go north lol lol' because it still fits under the MAX_LENGTH even tho it's invalid.
public class ConsoleInput implements Input {
	private String[] parseInput(String input) {
		input = input.trim().replaceAll(" +", " ");
		String[] arr = input.split(" ");
		return arr;
	}

	@Override
	public String[] getUserInput() { 
		String data = "";
		IOHandler.output.printf(">> ");
		@SuppressWarnings("resource")
		Scanner scanInput = new Scanner(System.in);
		data = scanInput.nextLine();
		scanInput.reset();
		return parseInput(data);
	}


}
