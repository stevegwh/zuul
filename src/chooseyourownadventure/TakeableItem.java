package chooseyourownadventure;

public class TakeableItem extends Item{
	private String name;
	private int weight;

	TakeableItem(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}
	
	public String getName() {
		return name;
	}
}
