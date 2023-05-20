/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
 *  Assignment: Project 1														 *
 *  Date: 30 August 2022 														 *
 * 	Class: ThreeDimensionalShape												 *
 * 	Purpose: Class extends the Shape class. Three dimensional shapes extend this *
 *  class to ensure that all compute the volume of a three dimensional shape.	 *																	
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

public abstract class ThreeDimensionalShape extends Shape{
	
	public abstract double getVolume();

	// Return that a ThreeDimensionalShape is a shape.
	public String toString() {
		String s = "a ThreeDimensionalShape is a Shape";
		return s;
	}
}
