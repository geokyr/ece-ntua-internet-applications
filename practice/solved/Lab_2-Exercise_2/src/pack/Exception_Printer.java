package pack;

public class Exception_Printer {
	public static void main (String[] args) {
		
		try {
			
			int a = 1 / 0;
			
		} 
		catch (Exception e) {
			
			System.out.println(e.toString());
			
		}
	}
}