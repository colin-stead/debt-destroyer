/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
 *  Assignment: Project 1														 *
 *  Date: 20 August 2022 														 *
 * 	Class: Circle																 *
 * 	Purpose: Class creates a circle shape object computes and returns the		 *
 *  area of the two dimensional shape. The object is created by having the user	 *
 *  enter the radius of a circle. Once created the area is returned. 			 *
 *  The class utilizes CheckValue	 											 *
 *  interface to promote code re-use to test that the value entered to create	 *
 *  the shape is the correct type.												 *																	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.text.DecimalFormat;
import java.util.Scanner;

public class Circle extends TwoDimensionalShape {
	private double radius;
	Scanner input = new Scanner(System.in);

	public Circle() {
		super.setName("Circle");
		super.setNumbDim(2);
		setRadius();
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return Math.PI * Math.pow(radius, 2);
	}
	// Prompts user to enter a value for the radius of a circle. Entry
	// is check to ensure it is the correct entry type. If not user is prompted to
	// enter the correct value type.
	private void setRadius() {
		String s;
		System.out.print("\nEnter the radius of the " + super.getName() + " : ");
		s = input.nextLine();
		// Check that the entry is a number.
		if (CheckValue.check(s) == -1)
			setRadius();
		else
			radius = Double.valueOf(s);
	}
	//Returns the Shape name, dimension type and area of the Shape object that was created.
	public String toString() {
		DecimalFormat formatArea = new DecimalFormat("0.00");
		return "A " + super.getName() + " is " + super.toString().substring(0, 21) + ". The area of the "
				+ super.getName() + " is: " + formatArea.format(getArea());
	}

}
