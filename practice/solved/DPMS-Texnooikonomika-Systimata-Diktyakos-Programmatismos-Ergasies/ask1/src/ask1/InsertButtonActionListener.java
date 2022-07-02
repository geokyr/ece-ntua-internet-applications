package ask1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;

import java.util.Date;
import java.util.Scanner;

class InsertButtonActionListener  implements ActionListener {

    final JTextField codeTextField;
    final JTextField nameTextField;
    final JTextArea outputTextArea;
	
    public InsertButtonActionListener(JTextField codeTextField, JTextField nameTextField,  JTextArea outputTextArea) {
		this.codeTextField = codeTextField;
		this.nameTextField = nameTextField;
		this.outputTextArea = outputTextArea;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String file = codeTextField.getText();
		final String phrase = nameTextField.getText();
		
		try{
			FileInputStream file1 = new FileInputStream(file);			//Creates a FileInputStream
			BufferedInputStream input = new BufferedInputStream(file1);			//Creates a BufferedInputStream
			String phr1 = "(?i)"+phrase;
			byte[] bytes = input.readAllBytes();												//Reads first byte from file
			String str = new String(bytes);
			String phr = phrase.toUpperCase();
			String str2 = str.replaceAll(phr1, phr);
			FileOutputStream file2 = new FileOutputStream("test1.txt");			// Creates a FileOutputStream
			BufferedOutputStream output = new BufferedOutputStream(file2);		// Creates a BufferedOutputStream
			
			byte[] array = str2.getBytes();
			output.write(array);												// Writes data to the output stream
			
			output.close();
			int lastIndex = 0;
			int count=0;
			
			while ((lastIndex = str2.indexOf(phr,lastIndex)) != -1) {
				
				if (lastIndex != -1) {count++;}
				
				lastIndex += phr.length();
			}
	
			outputTextArea.setText("Number of occurencies:"+String.valueOf(count));
			input.close();
		}
		
		catch (Exception ex) {
			ex.getStackTrace();
		}
		
		
	} // END actionPerformed(..)

	
		
		
}
	

