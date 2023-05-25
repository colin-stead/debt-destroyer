/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
 *  Assignment: Project 2														 *
 *  Date: 13 September 2022 													 *
 * 	Class: cube																	 *
 * 	Purpose: Class creates a cube shape object computes and returns the			 *	
 *  volume of the three dimensional shape. The object is created by having the	 * 
 *  user enter the length of one side of a square. The class utilizes			 * 
 *  CheckValue interface to promote code re-use to test that the value entered	 * 
 *  to create the shape is the correct type.									 *																	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.text.DecimalFormat;
import java.util.Scanner;

public class Cube extends ThreeDimensionalShape {
	Scanner input = new Scanner(System.in);
	double width;

	public Cube() {
		super.setName("Cube");
		super.setNumbDim(3);
//		setLength();
	}
	// Prompts user to enter a value for the length of one side of the cube. Entry
	// is check to ensure it is the correct entry type. If not user is prompted to
	// enter the correct value type.
	public void setWidth(String s) throws Exception {
//		System.out.print("\nEnter the length of the "+super.getName() + "'s side: ");
//		String s = input.nextLine();
try {
			width = Double.valueOf(s);
	}catch (Exception e) {
		throw new Exception(e);
	}

	}
	public double getwidth() {
		return this.width;
		
	}

	@Override
	public double getVolume() {
		return Math.pow(width, 3);
	}
	//Returns the Shape name, dimension type and area of the Shape object that was created.
	public String toString() {
		DecimalFormat formatVolume = new DecimalFormat("0.00");
		return "A " + super.getName() + " is " + super.toString().substring(0, 23) + ". The volume of the " + super.getName()
				+ " is: " + formatVolume.format(getVolume());
	}
	public String[] getVars() {
		String[] vars = new String[] { "1", "Width" };
		return vars;
	}

}
