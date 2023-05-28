
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
 *  Assignment: Project 1														 *
 *  Date: 30 August 2022 														 *
 * 	Class: Rectangle															 *
 * 	Purpose: Class creates a rectangle shape object computes and returns the	 *
 *  area of the two dimensional shape. Utilizes CheckValue interface to promote  *
 *  code re-use to test that the value entered to create the shape is the 		 *
 *  correct type.																 *																	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.text.DecimalFormat;
import java.util.Scanner;

public class Rectangle extends TwoDimensionalShape {
	Scanner input = new Scanner(System.in);
	private double length;
	private double width;

	public Rectangle() {
		super.setName("Rectangle");
		super.setNumbDim(2);
		setLength();
		setWidth();
	}
//Computes area of a rectangle.
	@Override
	public double getArea() {
		return length * width;
	}

	// Prompts user to enter a value for the width of the rectangle. Entry is check
	// to ensure it is the correct entry type. If not user is prompted to enter the
	// correct value type.
	private void setWidth() {
		System.out.print("\nEnter the width of the " + super.getName() + ": ");
		String s = input.nextLine();
		if (CheckValue.check(s) == -1)
			setWidth();
		else
			width = Double.valueOf(s);
	}
	// Prompts user to enter a value for the length of the rectangle. Entry is check
	// to ensure it is the correct entry type. If not user is prompted to enter the
	// correct value type.
	private void setLength() {
		System.out.print("\nEnter the length of the " + super.getName() + ": ");
		String s = input.nextLine();
		if (CheckValue.check(s) == -1)
			setLength();
		else
			length = Double.valueOf(s);

	}
//Returns the Shape name, dimension type and area of the Shape object that was created. 
	public String toString() {
		DecimalFormat formatArea = new DecimalFormat("0.00");
		return "A " + super.getName() + " is " + super.toString().substring(0, 21) + ". The area of the "
				+ super.getName() + " is: " + formatArea.format(getArea());
	}
}
