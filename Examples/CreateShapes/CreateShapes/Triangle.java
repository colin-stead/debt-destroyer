/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
 *  Assignment: Project 1														 *
 *  Date: 30 August 2022 														 *
 * 	Class: Triangle																 *
 * 	Purpose: Class creates a triangle shape object by having the user enter two	 *
 *  sides of a triangle. The pythagorean theorem is used to compute the length	 *
 *  of the last side. Class utilizes the semiperimeter to calculate the are 	 *
 *  instead of the base/height area calculation for a triangle. 				 *
 *  The Class is Utilizes CheckValue interface to promote  						 *
 *  code re-use to test that the value entered to create the shape is the 		 *
 *  correct type.																 *																	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.text.DecimalFormat;
import java.util.Scanner;

public class Triangle extends TwoDimensionalShape{
	double side1,side2,side3;
	Scanner input = new Scanner(System.in);
	
	
	public Triangle() {
		super.setName("Triangle");
		super.setNumbDim(2);
		setSide1();
		setSide2();
		//Compute the third side of the triangle. 
		side3 = Math.sqrt((Math.pow(side1, 2 ) + Math.pow(side2, 2 )));
	}
	@Override
	public double getArea() {
		double s = getSemiperimeter();
		return Math.sqrt(((s-side1)*(s-side2)*(s-side3)*9));
	}
	//Calculate Semiperimeter so that it can be used to calculate the are. 
	private double getSemiperimeter() {
		return (side1 + side2 + side3)/2;
		
	}
	// Prompts user to enter a value for the first side of the triangle. Entry is check
	// to ensure it is the correct entry type. If not user is prompted to enter the
	// correct value type.
	private void setSide1() {
		System.out.print("\nEnter the length of the first side: ");
		String s = input.nextLine();
		if(CheckValue.check(s)==-1)
			setSide1();
		else
			side1 = Double.valueOf(s);
	}	
	// Prompts user to enter a value for the second side of the triangle. Entry is check
	// to ensure it is the correct entry type. If not user is prompted to enter the
	// correct value type
	private void setSide2() {
		System.out.print("\nEnter the length of the second side: ");
		String s = input.nextLine();
		if(CheckValue.check(s)==-1)
			setSide2();
		else
			side2 = Double.valueOf(s);
	}
		
	

	//Returns the Shape name, dimension type and area of the Shape object that was created.
	public String toString() {
		DecimalFormat formatArea = new DecimalFormat("0.00");
		return "A " +  super.getName() + " is " + super.toString().substring(0, 21) + ". The area of the "+ super.getName()+" is: " + formatArea.format(getArea());
	}

}
