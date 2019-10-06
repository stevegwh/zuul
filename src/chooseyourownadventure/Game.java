package chooseyourownadventure;

import java.util.Scanner;
//import java.util.Arrays;

public class Game {
	
	private String[] commands = {"quit", "look", "go", "help"};
	private String[] directions = {"north", "south", "west", "east"};
	private Room room = new Room();
	private boolean isRunning = true;

	private boolean existsInArray(String strToCheck, String[] arr) {
		for(String item : arr) {
			if(strToCheck.contentEquals(item)) {
				return true;
			}
		}
		return false;
	}

	public void start() {
		while(isRunning) {
			room.printDescription();
//			System.out.printf(">> ");
			Output.print(">> ");
			String data = getInput();
			handleCommand(data);
		}
	}
	
	private String getInput() { //TODO: broken atm
		String data = "";
		Scanner scanInput = new Scanner(System.in);
		if(scanInput.hasNextLine()) {
			data = scanInput.nextLine();
		} else {
			data = scanInput.next();
		}
		scanInput.close();
		return data;		
	}

	private void changeRoom(String direction) {
		if(existsInArray(direction, directions)) {
			room.changeRoom(direction);
		}
	}
	
	private void showHelp() {
		System.out.printf("Valid commands are: ");
		for(String command : commands) {
			System.out.printf(command + ", "); //TODO: if i = length don't put comma
		}
		System.out.printf("\n");
	}
	
	private void quitGame() {
		Output.print("Thanks for playing!");
		isRunning = false;
	}
	
	private void look() {
		Output.print("You looked.");
	}
	
	private void executeCommand(String command, String command2) {
		switch(command) {
		case "go":
			changeRoom(command2);
			break;
		default:
			Output.print("Something went wrong");
		}
	}
	
	private void executeCommand(String command) {
		switch(command) {
		case "help":
			showHelp();
			break;
		case "quit":
			quitGame();
			break;
		case "look":
			look();
			break;
		default:
			Output.print("Something went wrong");
		}
	}
	
	private String[] parseInput(String input) {
		input = input.toLowerCase();
		input = input.trim().replaceAll(" +", " ");
		String[] arr = input.split(" ");
		return arr;
	}

	public void handleCommand(String input) {
		String[] inputArray = parseInput(input);
		if(existsInArray(inputArray[0], commands)) {
			if(inputArray.length > 1) {
				executeCommand(inputArray[0], inputArray[1]);
			} else {
				executeCommand(inputArray[0]);
			}
		} else {
			Output.print("Invalid command. Type 'help'.");
		}
	}
}
