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

	@Override
	public void printCharDialog(String ele) {
		// TODO Auto-generated method stub
		
	}

}
