package IO;

public class ConsoleOutput implements Output {

	@Override
	public void println(String ele) {
		System.out.println(ele);
	}

	@Override
	public void printf(String ele) {
		System.out.printf(ele);
	}

}
