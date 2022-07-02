package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskList {
	
	public static void main(String[] args) throws Exception {
	
		System.out.println("SELECT ACTION(S):" + "\n" + 
				"1 - Show File Names in Folder - Input(s): folder name"  + "\n" +
				"2 - Adder - Input(s): numbers"  + "\n" +
				"3 - Read File - Input(s): file name, in the 'pack' directory"  + "\n");
		
		// Get User Input
		System.out.print("Action(s) and Parameter(s), separate parameters with space(' ') and actions with comma(','): ");
		final String str = getUserInput();
		String[] actionList = str.split(",");
		
		for (int i = 0; i < actionList.length; i++) {
			String[] action =  actionList[i].split(" ");
			String act = action[0];
			int position = 0;
			
			if (act.equals("")) {
				act = action[1];
				position = 1;
			}
//			System.out.println(act);
			//see which action is, and execute it
			if (act.equals("1")) {
				File folder = new File(action[position + 1]);
				String[] folderCont  = folder.list();
				for (int a = 0; a < folderCont.length; a++) {
					System.out.println(folderCont[a]);
				}
				
			} else if (act.equals("2")) {
				int count = 0;
				for (int j = position + 1; j < action.length; j++) {
					count = count + Integer.parseInt(action[j]);
				}
				System.out.println(count);
				
			} else if (act.equals("3")) {
				try {
					File myObj = new File(action[position + 1]);
				    Scanner myReader = new Scanner(myObj);
				    while (myReader.hasNextLine()) {
				    	String data = myReader.nextLine();
				        System.out.println(data);
				    }
				    myReader.close();
				    
				} catch (FileNotFoundException e) {
					System.out.println("An error occurred.");
				    e.printStackTrace();
				}
				
			} else {
				
				System.out.println("Wrong Input!");
			}
		}
	}
	
	public static String getUserInput() throws IOException {
		
		// InputStreamReader: Reads bytes from Standard Input and Decodes them into characters
		// BufferedReader: Buffers the characters - Enable efficient reading of text data
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 

		// Read a line of text
		final String line = reader.readLine(); 
        
		return line;
	}
}