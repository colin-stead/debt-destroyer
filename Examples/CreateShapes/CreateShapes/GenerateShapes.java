
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
 *  Assignment: Project 1														 *
 *  Date: 30 August 2022 														 *
 * 	Class: GenerateShape														 *
 *  Purpose: Main class that is responsible for the console display and user	 *
 *  input. Once the program is launched the user is prompted with a menu for the *	
 *  creation of shapes or with the option to exit the program. When a shape is 	 *
 *  chosen the user is prompted to enter values to create the shape. After the 	 *
 *  creation of a two dimensional object the area of the object is returned. 	 *
 *  If a three dimensional object the volume is returned. The use is then 		 *
 *  prompted if the wish to continue or exit before getting to the console menu	 *															
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class GenerateShapes {
	public static void main(String[] args) {

		System.out.println("*********Welcome to the Java OO Shapes Program **********");
		// String array with menu options
		String[] menuOptions = new String[] { "Circle", "Rectangle", "Square", "Triangle", "Sphere", "Cube", "Cone",
				"Cylinder", "Torus", "Exit the program" };
		runTimeMenu(menuOptions);
	}

//TODO Create Method to generate menu options for user to choose from.
	private static void printMenu(String[] menuOptions) {
		System.out.println("\nSelect from the menu below:\n");

		for (int i = 0; i < menuOptions.length; i++) {
			if (i == menuOptions.length - 1)
				System.out.println((i + 1 + ". " + menuOptions[i]));
			else
				System.out.println(i + 1 + ".  Construct a " + menuOptions[i]);
		}

	}

//TODO create method to receive and return user input.
	private static int usrinput() {
		String s;
		int usrSelect = 0;
		@SuppressWarnings("resource")
		Scanner userSelection = new Scanner(System.in);
		// User need to enter a number and it need to be between 1 and 10. Invalid
		// entries will be caught and the user will be prompted again.
		while (usrSelect < 1 == true || usrSelect > 10 == true) {
			System.out.print("\nSelection: ");
			s = userSelection.nextLine();
			try {
				//Test is user entered and integer.
				usrSelect = Integer.valueOf(s);
				if (usrSelect > 10 ||usrSelect <1)
					System.err.println("Invalid selection. Select from options 1-10.");
			} catch (java.lang.NumberFormatException e) {
				//Non integer entered catch the exception and re-prompt. 
				System.err.println("Invalid numerical value Select from options 1-10.");

			}

		}
		return usrSelect;
	}

//TODO create method to return user selection and create object
	private static void shapeSelection(int usrSelect, String[] option) {
		if (usrSelect != 10)
			System.out.println("\n" + option[usrSelect - 1] + " was Selected");
		String query = "\nWould you like to continue? (Y or N)";
		// Create object, return print out with area or volume then prompt if they wish
		// to continue.
		switch (usrSelect) {
		case 1:
			Shape shape = new Circle();
			System.out.println(shape.toString());
			System.out.print(query + ":  ");
			continueShapeGeneration();
			break;
		case 2:
			shape = new Rectangle();
			System.out.println(shape.toString());
			System.out.print(query + ":  ");
			continueShapeGeneration();
			break;
		case 3:
			shape = new Square();
			System.out.println(shape.toString());
			System.out.print(query + ":  ");
			continueShapeGeneration();
			break;
		case 4:
			shape = new Triangle();
			System.out.println(shape.toString());
			System.out.print(query + ":  ");
			continueShapeGeneration();
			break;
		case 5:
			shape = new Sphere();
			System.out.println(shape.toString());
			System.out.print(query + ":  ");
			continueShapeGeneration();
			break;
		case 6:
			shape = new Cube();
			System.out.println(shape.toString());
			System.out.print(query + ":  ");
			continueShapeGeneration();
			break;
		case 7:
			shape = new Cone();
			System.out.println(shape.toString());
			System.out.print(query + ":  ");
			continueShapeGeneration();
			break;
		case 8:
			shape = new Cylinder();
			System.out.println(shape.toString());
			System.out.print(query + ":  ");
			continueShapeGeneration();
			break;
		case 9:
			shape = new Torus();
			System.out.println(shape.toString());
			System.out.print(query + ":  ");
			continueShapeGeneration();
			break;

		case 10:
			System.out.println(endProgramDisplay());
			System.exit(0);
			break;

		}

	}

	// Method to display date and time
	private static String endProgramDisplay() {
		Date endTime = new Date();
		return "\n Program will now exit. Today is " + DateFormat.getDateInstance().format(endTime).substring(0, 6)
				+ " at " + DateFormat.getTimeInstance(DateFormat.SHORT).format(endTime);
	}

	// TODO Create run time menu
	private static void runTimeMenu(String[] menuOptions) {
		int selection = 0;
		// If user enters 10 then they want to exit don't redisplay menu.
		while (selection != 10) {
			printMenu(menuOptions);
			selection = usrinput();
			shapeSelection(selection, menuOptions);

		}

	}

	// TODO add logic to ask user if they want to continue.
	private static void continueShapeGeneration() {
		int i = 0;
		@SuppressWarnings("resource")
		Scanner selection = new Scanner(System.in);
		String[] options = new String[] { "Y", "N" };
		Boolean correctSelection = false;
		String cont = selection.next();
		// Check to make sure the user entered a Y or N if not selection is invalid.
		if (cont.equalsIgnoreCase(options[0]) == true || cont.equalsIgnoreCase(options[1]) == true) {
			correctSelection = true;
		}
		while (correctSelection == false) {
			if (i > 0)
				System.err.println("Invalid selection. Select either Y or N");
			i++;
			cont = selection.nextLine();
			if (cont.equalsIgnoreCase(options[0]) == true || cont.equalsIgnoreCase(options[1]) == true) {
				correctSelection = true;
			}

		}
		// If user selects N exit the program.
		if (cont.equalsIgnoreCase(options[1]) == true) {
			System.out.println(endProgramDisplay());
			System.exit(0);
		}
	}
}
