package chooseyourownadventure;

import java.util.HashMap;
import java.util.Scanner;
//import java.util.Arrays;

public class Game {
	private HashMap<String, Item> items = new HashMap<String, Item>();
	private String[] commands = {"use", "quit", "investigate", "take",  "go", "help", "inventory", "drop", "give", "look"}; //TODO: should be look not investigate
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

	private void changeRoom(String direction) {
		if(existsInArray(direction, directions)) {
			room.changeRoom(direction);
		}
	}
	
	private void showHelp() {
		Output.printf("Valid commands are: ");
		for(String command : commands) {
			Output.printf(command + ", "); //TODO: if i = length don't put comma
		}
		Output.printf("\n");
	}
	
	private void quitGame() {
		Output.println("Thanks for playing!");
		isRunning = false;
	}
	
	private void investigate(String toInvestigate) {
		room.investigate(toInvestigate);
	}
	
	private void look() {
		room.look();
	}

	private void printItems() {
		room.printItems();
	}

	private void executeCommand(String command, String command2, String command3) {
		switch(command) {
		case "use":
			break;
		default:
			Output.println("Something went wrong");
		}
	}

	private void executeCommand(String command, String command2) {
		switch(command) {
		case "go":
			changeRoom(command2);
			break;
		case "investigate":
			investigate(command2);
			break;
		default:
			Output.println("Something went wrong");
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
		case "inventory":
			printItems();
			break;
		case "look":
			look();
			break;
		default:
			Output.println("Something went wrong");
		}
	}

	private String[] parseInput(String input) {
		input = input.toLowerCase();
		input = input.trim().replaceAll(" +", " ");
		String[] arr = input.split(" ");
		return arr;
	}

	private String getInput() { 
		String data = "";
		@SuppressWarnings("resource")
		Scanner scanInput = new Scanner(System.in);
		data = scanInput.nextLine();
		scanInput.reset();
		return data;		
	}

	public void start() {
		while(isRunning) {
			room.printDescription();
//			System.out.printf(">> ");
			Output.printf(">> ");
			String data = getInput();
			handleCommand(data);
		}
	}

	public void handleCommand(String input) {
		String[] inputArray = parseInput(input);
		if(existsInArray(inputArray[0], commands)) {
			if(inputArray.length > 4) {
				executeCommand(inputArray[0], inputArray[1], inputArray[3]);
			}else if(inputArray.length > 1) {
				executeCommand(inputArray[0], inputArray[1]);
			} else {
				executeCommand(inputArray[0]);
			}
		} else {
			Output.println("Invalid command. Type 'help'.");
		}
	}
}
