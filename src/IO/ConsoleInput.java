package IO;

import java.util.Scanner;

import zuulutils.ZuulEventHandler;

// TODO: Currently this allows you to write things like 'go north lol lol' because it still fits under the MAX_LENGTH even tho it's invalid.
public class ConsoleInput implements Input {
	private String[] parseInput(String input, int MAX_LENGTH) {
		input = input.trim().replaceAll(" +", " ");
		String[] arr = input.split(" ");
		if(arr.length > MAX_LENGTH) { //Maybe just say it's invalid and return empty array
			String[] truncatedArr = new String[MAX_LENGTH];
			System.arraycopy(arr, 0, truncatedArr, 0, MAX_LENGTH);
			return truncatedArr;
		} else {
			return arr;
		}
	}

	@Override
	public String[] getUserInput(int MAX_COMMAND_LENGTH) { 
		String data = "";
		IOHandler.output.printf(">> ");
		@SuppressWarnings("resource")
		Scanner scanInput = new Scanner(System.in);
		data = scanInput.nextLine();
		scanInput.reset();
		ZuulEventHandler.printSeperator();
		return parseInput(data, MAX_COMMAND_LENGTH);
	}


}
