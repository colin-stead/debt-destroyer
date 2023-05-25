/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
 *  Assignment: Project 1														 *
 *  Date: 30 August 2022 														 *
 * 	Interface: CheckValues														 *
 *  Purpose: Check that the user entered a double value. If they did return the  *
 *  value. if they didn't return -1 so it can be caught by the class logic.		 *													
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

interface CheckValue {
	
	public static double check(String val) {
		double converted;
		try {
			converted = Double.valueOf(val);
		} catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid numerical value");
			return -1;
		}
		return converted;
	}
	
	
}
