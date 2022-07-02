package pack;

public class Util {
	
	/* Ensures that we will not create an Instance of this Class */
	private Util() {
	}
	
	/* Print a Message regarding the memory status before and after gc() */
	public static synchronized void printMemoryStatus(final String message) {
		System.out.println(message);
		System.out.println(" - BEFOR-gc(): " + getMemoryStatus());
		
		// This method strictly used on a Desktop Application for testing purposes
		Runtime.getRuntime().gc();
		
		System.out.println(" - AFTER-gc(): "  + getMemoryStatus());
		System.out.println();
	}
	
	/* @return A String with the current memory status */
	public static synchronized String getMemoryStatus() {
		
		// Max Memory / Maximum Heap Size
		final long maxmemory = Runtime.getRuntime().maxMemory();
		final int maxmemMB = (int) (maxmemory / ( 1024 * 1024));
		
		// Total Memory / Current Heap Size
		final long totalmemory = Runtime.getRuntime().totalMemory();
		final int totmemMB = (int) (totalmemory / ( 1024 * 1024));
		
		// Free Memory & Memory Used
		final long freememory = Runtime.getRuntime().freeMemory();
		final int freememMB = (int) (freememory / ( 1024 * 1024));
		final int usememMB = totmemMB - freememMB;
		
		// Compile Data in a String
		return "Memory:: Maximum: " + maxmemMB + " MB , Current: " + totmemMB + " MB , Used: " + usememMB + " MB ";
	}
}