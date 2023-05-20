import java.awt.Color;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
  *  Assignment: Project 2														 *
 *  Date: 13 September 2022 													 *
* 	Class: Shape																 *
 * 	Purpose: Base object for creating two and three dimensional shapes. Class	 *
 *  extends the Object Class.									  				 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class Shape extends Object {
private String name;
private int numberOfDimensions;
private boolean isFilled;
private Color fillColor = Color.BLACK;
private Color outlineColor = Color.BLACK;

//Creates a Shape template.
public Shape() {
	
}
//Set the name of the shape object
public void setName(String name) {
	this.name = name;
}
// Returns the name of the shape.
public String getName() {
	return name;
}
//Sets the type identifier for the type of object.
public void setNumbDim(int numb) {
	numberOfDimensions = numb;
}
//Returns the type of object
public int getNumDim() {
return numberOfDimensions;
}

public String toString() {
	return "A Shape is an Object.";
}
public Color getOutlineColor() {
	return outlineColor;
}
public void setOutlineColor(Color outlineColor) {
	this.outlineColor = outlineColor;
}
public Color getFillColor() {
	return fillColor;
}
public void setFillColor(Color fillColor) {
	this.fillColor = fillColor;
}
public boolean isFilled() {
	return isFilled;
}
public void setFilled(boolean isFilled) {
	this.isFilled = isFilled;
}
}
