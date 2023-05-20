
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
 *  Assignment: Project 1														 *
 *  Date: 30 August 2022 														 *
 * 	Class: TwoDimensionalShape													 *
 * 	Purpose: Class extends the Shape class. two dimensional shapes extend this 	 *
 *  class to ensure that all compute the area of a two dimensional shape.		 *																	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public abstract class TwoDimensionalShape extends Shape {

	public abstract double getArea();

	// Return that a ThreeDimensionalShape is a shape.
	public String toString() {
		String s = "a TwoDimensionalShape is a Shape.";
		return s;
	}
}
