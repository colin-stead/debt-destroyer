import javax.swing.JFrame;
import javax.swing.JOptionPane;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 350 6380														 *
 *  Assignment: Project 2														 *
 *  Date: 13 September 2022 													 *
 * 	Class: InvalidNumberEntered													 *
 * 	Purpose: Class used to throw an exception in a JOptionPane to alert the	 	 *
 *  user of that an incorrect value has been entered as a dimension				 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class InvalidNumberEntered extends Exception {
	JFrame frame;

	public InvalidNumberEntered() {

	}

	public InvalidNumberEntered(String s) {
		JOptionPane.showMessageDialog(frame, "Invalid. non-number value entered.", s + " Shape Creation Error", JOptionPane.ERROR_MESSAGE);
	}

}