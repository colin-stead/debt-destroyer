
import java.text.DecimalFormat;
import java.util.Scanner;

public class Torus extends ThreeDimensionalShape {
	double radius, rotRadius;
	Scanner input = new Scanner(System.in);

	public Torus() {
		super.setName("Torus");
		super.setNumbDim(3);
//		setRadius();
//		setRotRadius();
	}

	// Prompts user to enter a value for the minor radius of the torus. The minor
	// radius is the radius for the cylinder that is wrapped around the center point
	// of the torus.
	// Entry is check to ensure it is the correct entry type. If not user is prompted to
	// enter the correct value type.
	private void setRadius(String s) {
//		System.out.print("\nEnter the Minor radius for the " + super.getName() + ": ");
//		s = input.nextLine();
		// Check that the entry is a number.
		if (CheckValue.check(s) == -1)
			setRadius(s);
		else
			radius = Double.valueOf(s);
	}
	// Prompts user to enter a value for the major radius of the torus. The major
	// radius is the length of cylinder that is wrapped around the center point
	// of the torus.
	// Entry is check to ensure it is the correct entry type. If not user is prompted to
	// enter the correct value type.
	private void setRotRadius() {
		String s;
		System.out.print("\nEnter the Major radius for the " + super.getName() + ": ");
		s = input.nextLine();
		// Check that the entry is a number.
		if (CheckValue.check(s) == -1)
			setRotRadius();
		else
			rotRadius = Double.valueOf(s);
	}
	public String[] getColors() {
		String[] colors = new String[] {"Red","Blue","Green","Yellow"};
		return colors;
	}

	@Override
	public double getVolume() {
		// TODO Auto-generated method stub
		return Math.round((2 * Math.PI * rotRadius) * (Math.PI * Math.pow(radius, 2)));
	}

	// Returns the Shape name, dimension type and area of the Shape object that was
	// created.
	public String toString() {
		DecimalFormat formatVolume = new DecimalFormat("0.00");
		return "A " + super.getName() + " is " + super.toString().substring(0, 23) + ". The volume of the " + super.getName()
				+ " is: " + formatVolume.format(getVolume());
	}
	public String[] getVars() {
		String[] vars = new String[] { "0", " " };
		return vars;
	}

}
