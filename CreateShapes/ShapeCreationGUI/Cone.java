
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
 *  Assignment: Project 2														 *
 *  Date: 13 September 2022 													 *
 * 	Class: Cone																 	 *
 *  Purpose: Class creates a cone shaped object, computes and returns the		 *
 *  volume of the three dimensional shape. The object is created by having the	 *
 *  user enter the radius of a the cone's top and height of the cone.			 *
 *  Once created the volume is returned.The class utilizes CheckValue	 		 *
 *  interface to promote code re-use to test that the value entered to create	 *
 *  the shape is the correct type.												 *																	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.text.DecimalFormat;
import java.util.Scanner;

public class Cone extends ThreeDimensionalShape {
	double radius, height;
	Scanner input = new Scanner(System.in);

	public Cone() {
		super.setName("Cone");
		super.setNumbDim(3);
//		setRadius();
//		setHeight();
	}

	// Prompts user to enter a value for the radius of the cone. Entry
	// is check to ensure it is the correct entry type. If not user is prompted to
	// enter the correct value type.
	private void setRadius() {
		String s;
		System.out.print("\nEnter the radius for the " + super.getName() + ": ");
		s = input.nextLine();
		// Check that the entry is a number.
		if (CheckValue.check(s) == -1)
			setRadius();
		else
			radius = Double.valueOf(s);
	}

	// Prompts user to enter a value for the height of the cone. Entry
	// is check to ensure it is the correct entry type. If not user is prompted to
	// enter the correct value type.
	private void setHeight() {
		System.out.print("\nEnter the height of the " + super.getName() + ": ");
		String s = input.nextLine();
		if (CheckValue.check(s) == -1)
			setHeight();
		else
			height = Double.valueOf(s);

	}

	@Override
	public double getVolume() {
		return ((Math.PI * Math.pow(radius, 2) * height) / 3);
	}

	// Returns the Shape name, dimension type and area of the Shape object that was
	// created.
	public String toString() {
		DecimalFormat formatVolume = new DecimalFormat("0.00");
		return "A " + super.getName() + " is " + super.toString().substring(0, 23) + ". The volume of the " + super.getName()
				+ " is: " + formatVolume.format(getVolume());
	}
	public String[] getColors() {
		String[] colors = new String[] {"Blue","Green","Yellow","Wood"};
		return colors;
	}
	public String[] getVars() {
		String[] vars = new String[] { "0", " " };
		return vars;
	}
}
