package pack;

//Static Import
import static pack.Util.printMemoryStatus;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemoryCheckMain{
	
	//Simple Date Format
	private static final String datetimePattern = "yyyy-MM-dd HH:mm:ss.SSS";
	private static final SimpleDateFormat df= new SimpleDateFormat(datetimePattern);
	private static int N=10000000;
	
	public static void main(String[] args) {
		System.out.println(" >> " + MemoryCheckMain.class.getSimpleName() + " :Start" + df.format(new Date()));
		System.out.println();
	
		// Let's talk about Java Memory
		// -----------------------------------------

		// Java Memory
		printMemoryStatus("START");
			
		System.out.println("CREATE A LIST WITH " + " STRINGS ...\n");
		final List<String> myList = new ArrayList<String>();
		for (int i = 0; i < N; i++) {
			myList.add(new String("Message-" + i));
		}
		
		//Java Memory
		printMemoryStatus("END 1 - BEFORE myList.clear()");
		
		// Release Memory
		System.out.println("EMPTY LIST\n");
		myList.clear();
		
		// Java Memory
		printMemoryStatus("END 2 - AFTER myList.clear()");
		
		System.out.println();
		System.out.println(" >> " + MemoryCheckMain.class.getSimpleName() + ": END" + df.format(new Date()));
	}
}