
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
 *  Assignment: Project 2														 *
 *  Date: 13 September 2022 													 *
 * 	Class: Square																 *
 * 	Purpose: Class creates a square shape object computes and returns the		 *
 *  area of the two dimensional shape. The object is created by having the user	 *
 *  enter the length of one side of the square. The class utilizes CheckValue	 *
 *  interface to promote code re-use to test that the value entered to create	 *
 *  the shape is the correct type.												 *																	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.naming.InvalidNameException;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends TwoDimensionalShape {
	Scanner input = new Scanner(System.in);
	private double length;

	public Square() {
		// TODO Auto-generated constructor stub
		super.setName("Square");
		super.setNumbDim(2);
//		setLength();
	}

	@Override
	public double getArea() {
		return Math.pow(length, 2);
	}

	// Prompts user to enter a value for the length of one side of the square. Entry
	// is check to ensure it is the correct entry type. If not user is prompted to
	// enter the correct value type.
	public void setLength(String s) throws Exception {
//		System.out.print("\nEnter the length of the " + super.getName() + "'s side: ");
//		String s = input.nextLine();
		try {
			length = Double.valueOf(s);
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	//Returns the Shape name, dimension type and area of the Shape object that was created.
	public String toString() {
		DecimalFormat formatArea = new DecimalFormat("0.00");
		return "A " + super.getName() + " is " + super.toString().substring(0, 21) + ". The area of the "
				+ super.getName() + " is: " + formatArea.format(getArea());
	}
	public String[] getVars() {
		String[] vars = new String[] {"1","Length"};
		return vars;
	}
	public Rectangle print(Color outline, Color fill) {
		Rectangle rectangle =  new Rectangle(length, length);
		System.out.println(" length = " + this.length);
		rectangle.setStroke(outline);
		rectangle.setFill(fill);
		
		return rectangle;
	}
}
