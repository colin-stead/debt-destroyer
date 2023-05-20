
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
 *  Assignment: Project 2														 *
 *  Date: 13 September 2022 													 *
 * 	Class: Sphere																 *
 * 	Purpose: Class creates a sphere shape object computes and returns the		 *
 *  volume of the three dimensional shape. The object is created by having the	 *
 *  user enter the radius of a sphere. Once created the volume is returned. 	 *
 *  The class utilizes CheckValue	 											 *
 *  interface to promote code re-use to test that the value entered to create	 *
 *  the shape is the correct type.												 *																	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.text.DecimalFormat;
import java.util.Scanner;

public class SphereShape extends ThreeDimensionalShape {
	private double radius;
	Scanner input = new Scanner(System.in);

	public SphereShape() {
		super.setName("Sphere");
		super.setNumbDim(3);
//		setRadius();
	}

	// Prompts user to enter a value for the radius of a sphere. Entry
	// is check to ensure it is the correct entry type. If not user is prompted to
	// enter the correct value type.
	public void setRadius(String s) throws Exception {
//		String s;
//		System.out.print("\nEnter the radius of the " + super.getName() + " : ");
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

	@Override
	public double getVolume() {
		// TODO Auto-generated method stub
		return ((4 / 3) * Math.PI * Math.pow(radius, 3));
	}

	// Returns the Shape name, dimension type and area of the Shape object that was
	// created.
	public String toString() {
		DecimalFormat formatVolume = new DecimalFormat("0.00");
		return "A " + super.getName() + " is " + super.toString().substring(0, 23) + ". The volume of the "
				+ super.getName() + " is: " + formatVolume.format(getVolume());
	}
public String[] getVars() {
	String[] vars = new String[] { "1", "Radius" };
	return vars;
}
}
