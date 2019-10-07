package chooseyourownadventure;

public class Item {
	private String name;
	private String onTake; //text to display when item is picked up
	private String description; //generic description
	private Object[] itemPairings; //what can this item interact with?
	public void use() {
		Output.printf("You used the " + name);
	}
	Item(String name, String onTake, String description) {
		this.name = name;
		this.onTake = onTake;
		this.itemPairings = itemPairings;
		this.description = description;
	}
}
