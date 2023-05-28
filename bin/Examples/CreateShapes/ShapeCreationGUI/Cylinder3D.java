/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
  *  Assignment: Project 2														 *
 *  Date: 13 September 2022 													 *
* 	Class: Cylinder																 *
 *  Purpose: Class creates a cylinder shaped object, computes and returns the	 *
 *  volume of the three dimensional shape. The object is created by having the	 *
 *  user enter the radius of a the cone's top and height of the cone.			 *
 *  Once created the volume is returned.The class utilizes CheckValue	 		 *
 *  interface to promote code re-use to test that the value entered to create	 *
 *  the shape is the correct type.												 *																	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.text.DecimalFormat;
import java.util.Scanner;

public class Cylinder3D extends ThreeDimensionalShape {
	double height, radius;
	Scanner input = new Scanner(System.in);

	public Cylinder3D() {
		super.setName("Cylinder");
		super.setNumbDim(3);
//		setRadius();
//		setHeight();
	}
	// Prompts user to enter a value for the radius of the Cylinder. Entry
	// is check to ensure it is the correct entry type. If not user is prompted to
	// enter the correct value type.
	public void setRadius(String s) throws Exception {
//		String s;
//		System.out.print("\nEnter the radius for the " + super.getName() + ": ");
//		s = input.nextLine();
		// Check that the entry is a number.
try {
			radius = Double.valueOf(s);
	}catch (Exception e) {
		throw new Exception(e);
	}
	}
	public double getRadius() {
		return this.radius;
	}
	
	public double getHeight() {
		return this.height;
	}
	// Prompts user to enter a value for the height of the Cylinder. Entry
	// is check to ensure it is the correct entry type. If not user is prompted to
	// enter the correct value type.
	public void setHeight(String s) throws Exception {
//		System.out.print("\nEnter the height of the " + super.getName() + ": ");
//		String s = input.nextLine();
try {
			height = Double.valueOf(s);
}catch (Exception e) {
	throw new Exception(e);
}
	}

	@Override
	public double getVolume() {
		// TODO Auto-generated method stub
		return (Math.PI * Math.pow(radius, 2) * height);
	}
	//Returns the Shape name, dimension type and area of the Shape object that was created.
	public String toString() {
		DecimalFormat formatVolume = new DecimalFormat("0.00");
		return "A " + super.getName() + " is " + super.toString().substring(0, 23) + ". The volume of the " + super.getName()
				+ " is: " + formatVolume.format(getVolume());
	}
	public String[] getVars() {
		String[] vars = new String[] {"2","Radius", "Height"};
		return vars;
	}
}
