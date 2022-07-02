package pack;

import javax.swing.JFrame;

public class FindReplaceCap {
	
	/** Creates and Presents the Program GUI */
	public static void main(String[] args) {
			
		System.out.println(" >> ProgramWindowMain: START");
			
		// Create and Show a window
		final JFrame frame = new ProgramJFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
			
		System.out.println(" >> ProgramWindowMain: END");
	}
}