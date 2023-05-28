
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
  *  Assignment: Project 2														 *
 *  Date: 13 September 2022 													 *
* 	Class: Rectangle															 *
 * 	Purpose: Class creates a rectangle shape object computes and returns the	 *
 *  area of the two dimensional shape. Utilizes CheckValue interface to promote  *
 *  code re-use to test that the value entered to create the shape is the 		 *
 *  correct type.																 *																	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.text.DecimalFormat;
import java.util.Scanner;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rectangle2D extends TwoDimensionalShape {
	Scanner input = new Scanner(System.in);
	private double length;
	private double width;

	public Rectangle2D() {
		super.setName("Rectangle");
		super.setNumbDim(2);
//		setLength();
//		setWidth();
	}
//Computes area of a rectangle.
	@Override
	public double getArea() {
		return length * width;
	}

	// Prompts user to enter a value for the width of the rectangle. Entry is check
	// to ensure it is the correct entry type. If not user is prompted to enter the
	// correct value type.
	public void setWidth(String s) throws Exception {

		try {
			width = Double.valueOf(s);
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	// Prompts user to enter a value for the length of the rectangle. Entry is check
	// to ensure it is the correct entry type. If not user is prompted to enter the
	// correct value type.
	public void setLength(String s) throws Exception {

		try {
			length = Double.valueOf(s);
		}catch(Exception e){
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
		String[] vars = new String[] {"2","Length", "Width"};
		return vars;
	}
	public Rectangle print(Color outline, Color fill) {
		Rectangle rectangle =  new Rectangle(this.width, this.length);
		rectangle.setStroke(outline);
		rectangle.setFill(fill);
		
		return rectangle;
	}
}
