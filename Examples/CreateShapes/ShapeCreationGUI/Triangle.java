
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
  *  Assignment: Project 2														 *
 *  Date: 13 September 2022 													 *
* 	Class: Triangle																 *
 * 	Purpose: Class creates a triangle shape object by having the user enter the	 *
 *  length of a leg and creates and equilateral triangle.						 *
 
 *  The Class is Utilizes CheckValue interface to promote  						 *
 *  code re-use to test that the value entered to create the shape is the 		 *
 *  correct type.																 *																	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.awt.Polygon;
import java.text.DecimalFormat;
import java.util.Scanner;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Triangle extends TwoDimensionalShape {
	double side1, side2, side3;
	Scanner input = new Scanner(System.in);

	public Triangle() {
		super.setName("Triangle");
		super.setNumbDim(2);
//		setSide1();
		// Compute the third side of the triangle.
		side3 = Math.sqrt((Math.pow(side1, 2) + Math.pow(side2, 2)));
	}

	@Override
	public double getArea() {
		double s = getSemiperimeter();
		return Math.sqrt(((s - side1) * (s - side2) * (s - side3) * 9));
	}

	// Calculate Semiperimeter so that it can be used to calculate the are.
	private double getSemiperimeter() {
		return (side1 + side2 + side3) / 2;

	}

	// Prompts user to enter a value for the first side of the triangle. Entry is
	// check
	// to ensure it is the correct entry type. If not user is prompted to enter the
	// correct value type.
	public void setLength(String s) throws Exception {
//		System.out.print("\nEnter the length of the first side: ");
//		String s = input.nextLine();
try {
			side1 = Double.valueOf(s);
		side2 = Double.valueOf(s);
		side3 = Double.valueOf(s) / 2;
}catch (Exception e) {
	throw new Exception(e);
}
	}
	// Prompts user to enter a value for the second side of the triangle. Entry is
	// check
	// to ensure it is the correct entry type. If not user is prompted to enter the
	// correct value type

	// Returns the Shape name, dimension type and area of the Shape object that was
	// created.
	public String toString() {
		DecimalFormat formatArea = new DecimalFormat("0.00");
		return "A " + super.getName() + " is " + super.toString().substring(0, 21) + ". The area of the "
				+ super.getName() + " is: " + formatArea.format(getArea());
	}

	public String[] getVars() {
		String[] vars = new String[] { "1", "Length" };
		return vars;
	}

	public Group print(Color outline, Color fill) {
		double length = side1;
		javafx.scene.shape.Polygon triangle = new javafx.scene.shape.Polygon(0d, 0d, -(length * Math.cos(60)), length,
				(length * Math.cos(60)), length);
		triangle.setFill(fill);
		triangle.setStroke(outline);
		Group triGroup = new Group(triangle);
		return triGroup;
	}

}
