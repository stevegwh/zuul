package zuul;

// General library of helpful methods used within the game
public class ZuulTools {
	public static String capitalize(String toCapitalize) {
		return toCapitalize.substring(0,1).toUpperCase() + toCapitalize.substring(1, toCapitalize.length()).toLowerCase();
	}

}
