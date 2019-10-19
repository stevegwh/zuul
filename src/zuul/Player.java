package zuul;

import java.util.ArrayList;

public class Player {
	private ArrayList<String> inventory = new ArrayList<>();
	private static String currentLocation;
	
	public static void setLocation(String location) {
		currentLocation = location;
	}
	public static String getLocation() {
		return currentLocation;
	}

}
