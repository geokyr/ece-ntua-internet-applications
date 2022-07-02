package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class InsertButtonActionListener  implements ActionListener {

    final JComboBox<String> actionDropList;
    final JTextField inputTextField;
    final JTextArea outputTextArea;
    
    public InsertButtonActionListener(JComboBox<String> actionDropList, JTextField inputTextField, JTextArea outputTextArea) {
		this.actionDropList = actionDropList;
		this.inputTextField = inputTextField;
		this.outputTextArea = outputTextArea;
	}
    
    @Override
	public void actionPerformed(ActionEvent e) {
		final String action = (String) actionDropList.getSelectedItem();
		final String input = inputTextField.getText().trim();
		
		// CHECK & CONVERTDATA
		if (!isDataValid(action, input)) {
			final String errmsg = "Invalid Data!";
			this.outputTextArea.setText(errmsg + "\n");
		} 
		
		else {

			char act = action.charAt(0);
			if (act == '1') {
				
				try {
					File folder = new File(input);
					String[] folderCont  = folder.list();
					for (int i = 0; i < folderCont.length; i++) {
						outputTextArea.append(folderCont[i] + "\n");
					}
				}
				catch (Exception ex) {
					outputTextArea.setText("An error occurred wlile trying to find and read the folder!" + "\n"
							+ "Propably your input is wrong!" + "\n");
					ex.printStackTrace();
				}

			} else if (act == '2') {
				
				try {
					String[] numbers = input.split(" ");
					int count = 0;
					for (int j = 0; j < numbers.length; j++) {
						count = count + Integer.parseInt(numbers[j]);
					}
					outputTextArea.setText(count + "\n");
				}
				catch (Exception ex) {
					outputTextArea.setText("Propably your input is wrong!" + "\n");
					ex.printStackTrace();
				} 
						
			} else if (act == '3') {
				
				try {
					File myObj = new File(input);
				    Scanner myReader = new Scanner(myObj);
				    while (myReader.hasNextLine()) {
				    	String data = myReader.nextLine();
				    	outputTextArea.append(data + "\n");
				    }
				    myReader.close();
				    
				} catch (FileNotFoundException ex) {
					outputTextArea.setText("An error occurred wlile trying to find and read the file!" + "\n"
							+ "Propably your input is wrong!" + "\n");
				    ex.printStackTrace();
				}
				
			} else {
				outputTextArea.setText("Something went wrong!" + "\n");
			}
		}
		
	} // END actionPerformed(..)

	private boolean isDataValid(String action, String input) {
		final StringBuilder errorsSb = new StringBuilder();
		if (action.isEmpty()) {
			errorsSb.append("No Action is selected!");
		}
		if (input.isEmpty()) {
			if (!errorsSb.isEmpty()) errorsSb.append("\n");
			errorsSb.append("No Input is given!");
		}
		
		if (!errorsSb.isEmpty()) {
			JOptionPane.showMessageDialog(null, errorsSb.toString(), "Invalid Data", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
}