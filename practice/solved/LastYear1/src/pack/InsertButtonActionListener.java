package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class InsertButtonActionListener  implements ActionListener {

    final JTextField inputFileField;
    final JTextField inputPhraseField;
    final JTextArea outputTextArea;
    
	// List with the original text
	List<String> wordList = new ArrayList<String>();
	// List with the CAP text
	List<String> capList = new ArrayList<String>();;
	// Number of Mentions
	int count = 0;
    
    public InsertButtonActionListener(JTextField inputFileField, JTextField inputPhraseField, JTextArea outputTextArea) {
		this.inputFileField = inputFileField;
		this.inputPhraseField = inputPhraseField;
		this.outputTextArea = outputTextArea;
	}
    
    @Override
	public void actionPerformed(ActionEvent e) {
		final String filename = inputFileField.getText();
		final String phrase = inputPhraseField.getText();
		
		// CHECK & CONVERTDATA
		if (!isDataValid(filename, phrase)) {
			final String errmsg = "Invalid Data!";
			this.outputTextArea.setText(errmsg + "\n");
		
		} else {
			
			// Locate and read the file
			try {
				File myObj = new File(filename);
				Scanner myReader = new Scanner(myObj);
				
			    while (myReader.hasNextLine()) {
			    	String data = myReader.nextLine();
			    	
			    	// Set the Arrays with the text data splitted
			    	String[] wordArray = data.split(" ");
			    	String[] capArray = data.toUpperCase().split(" ");
			    	
////			    	if (wordList.isEmpty() == true && capList.isEmpty() == true) {
//			    	wordList.set(0, wordArray[0]);
//			    	capList.set(0, capArray[0]);
////			    	}
			    	for (int i = 0; i < wordArray.length; i++) {
			    		wordList.add(wordArray[i]);
			    		
			    		capList.add(capArray[i]);
			    	}
			    }
			    myReader.close();
 
			} catch (FileNotFoundException ex) {
				outputTextArea.setText("An error occurred wlile trying to find and read the file!" + "\n"
						+ "Propably your input is wrong!" + "\n");
			    ex.printStackTrace();
			}
			
			// Split the given phrase
			String[] phraseArray = phrase.toUpperCase().split(" ");
			
			for (int i = 0; i < capList.size(); i++) {
				
				if (capList.get(i).equals(phraseArray[0])) {
					boolean similar = true;
					
					for (int j = 1; j < phraseArray.length; j++) {
						similar = capList.get(i + j).equals(phraseArray[j]);
						if (similar == false) {
							break;
						}
					}
					
					// The phrase was found in the text
					if (similar == true) {
						count++;
												
						for (int j = 0; j < phraseArray.length; j++) {
							wordList.set(i + j, phraseArray[j]);
						}
					}				
				}
			}
			
			try {
				FileWriter myWriter = new FileWriter(filename);
	
				myWriter.write(wordList.get(0));
			    for (int i = 1; i < wordList.size(); i++) {
	                myWriter.write(" " + wordList.get(i));
			    }
			    myWriter.close();
			    
	            System.out.println("Successfull Replacement");
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			outputTextArea.append("Number of times mentioned:" + count + "\n");
		}
	} // END actionPerformed(..)

	private boolean isDataValid(String filename, String phrase) {
		final StringBuilder errorsSb = new StringBuilder();
		if (filename.isEmpty()) {
			errorsSb.append("No file path is given!");
		}
		if (phrase.isEmpty()) {
			if (!errorsSb.isEmpty()) errorsSb.append("\n");
			errorsSb.append("No input phrase is given!");
		}
		
		if (!errorsSb.isEmpty()) {
			JOptionPane.showMessageDialog(null, errorsSb.toString(), "Invalid Data", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
}