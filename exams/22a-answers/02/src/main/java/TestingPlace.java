
public class TestingPlace {

	public static void main(String[] args) {
		
		System.out.println(getSth("Test Main"));

	}

	public static String getSth(String input) {
		return TestingPlace.class.getName() + ": " + input;
	}
}
