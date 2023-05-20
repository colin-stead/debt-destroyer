/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Author: Ryan Bramich	 													 *
 *  Course: CMSC 355 6381														 *
 *  Assignment: Project 2														 *
 *  Date: 13 September 2022 													 *
 * 	Class: ShapeCreatioGUI														 *
 * 	Purpose: Create GUI interface so that the user can select the shape and 	 *	
 *  colors to be used to generate a shape. 2D objects users can choose the 		 *
 *  outline color as well as the fill color. For 3D objects all objects are 	 *
 *  either filled or created by images so user can enter dimensions and select	 *
 *  the fill color of the 3D object. Some 3D objects have the ability to be		 *
 *  rotated when the user selects the shape and moves his/her mouse.			 *													
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.awt.Checkbox;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.EventHandler;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.naming.InvalidNameException;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class ShapeCreationGUI extends Application {

	private String[] menuOptions = new String[] { " ", "Circle", "Rectangle", "Square", "Triangle", "Sphere", "Cube",
			"Cone", "Cylinder", "Torus" };
	private ArrayList<String> menuArray = new ArrayList<>();
	private ArrayList<String> colorArray = new ArrayList<>();
	private String[] colorOptions = new String[] { " ", "Black", "Blue", "Cyan", "Dark Grey", "Grey", "Light Grey",
			"Green", "Magenta", "Orange", "Pink", "Red", "White", "Yellow" };
	private Label lab, lab1;
	private TextField dim, dim1;
	String[] color, option;

	// Create ComboBoxes
	private ComboBox<String> options = new ComboBox<String>();
	private ComboBox<String> colorsOutline = new ComboBox<String>();
	private ComboBox<String> colorsFill = new ComboBox<String>();
	private ComboBox<String> imageColors = new ComboBox<String>();
	private GridPane dimLayout = new GridPane();

	// Create Buttons
	private Button createShape = new Button("Create Shapes");
	private Button exit = new Button("Exit");

	// Create Panes
	private GridPane guiLayout = new GridPane();
	private FlowPane shapePane = new FlowPane();
	private GridPane initialCombo = new GridPane();
	private GridPane shapePaneSelection;

	private double mouseOldX, mouseOldY = 0;
	private Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
	private Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
	private Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);
	// Build shape choice arrayList to get reference index.
	public void makeChoiceArrayList(String[] options) {
		for (int i = 0; i < options.length; i++) {
			this.menuArray.add(options[i]);
		}
	}

	// Build color choice arrayList to get reference index.
	public void makeColorArrayList(String[] colors) {
		for (int i = 0; i < colors.length; i++) {
			this.colorArray.add(colors[i]);
		}
	}

	// launch the JavaFX GUI application
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {

		// set title for the stage
		primaryStage.setTitle("Shape Creation GUI");

		// Create drop down options
		setShapeChoice(menuOptions);
		setColorOptions(colorOptions);
		// Set arraylist to get combo reference values.
		makeChoiceArrayList(menuOptions);
		makeColorArrayList(colorOptions);
		guiLayout.setHgap(5);
		guiLayout.setVgap(5);

		// Generate initial GUI.
		guiLayout.add(initialGuiSelection(), 0, 1);
		guiLayout.add(buttons(), 0, 3);

		// Add action event handlers and assign actions.
		javafx.event.EventHandler<ActionEvent> createDimensionPane = e -> createDimensionPane();
		javafx.event.EventHandler<ActionEvent> createSelectedShape = e -> {
			try {
				createSelectedShape();
			} catch (InvalidNumberEntered e1) {
			}
		};
		javafx.event.EventHandler<ActionEvent> exitProgram = e -> System.exit(0);
		this.options.setOnAction(createDimensionPane);
		this.createShape.setOnAction(createSelectedShape);
		this.exit.setOnAction(exitProgram);

		// create a scene for main GUI.
		Scene mainScene = new Scene(guiLayout, 400, 300);

		// set the scene to the stage for GUI
		primaryStage.setScene(mainScene);
		// display GUI.
		primaryStage.show();
	}

	// Initial start selection with just the shape comboBox
	public GridPane initialGuiSelection() {
//		ComboBox<String> items = this.options;
		initialCombo = new GridPane();
		initialCombo.setAlignment(Pos.CENTER);
		initialCombo.add(new Label("Shape"), 0, 0);
		initialCombo.add(this.options, 0, 1);
		return initialCombo;
	}

	// Pane for 2 dimensional shapes with the ability to change outline and fill
	// color.
	private GridPane create2DShapeSelection() {
		// Create Border for Shape creation section.
		shapePaneSelection = new GridPane();

		shapePaneSelection.setAlignment(Pos.CENTER);

		// Set Spacing
		shapePaneSelection.setHgap(5.5);
		shapePaneSelection.setVgap(5.5);

		// Add labels and ComboBoxes
		shapePaneSelection.add(new Label("Shape"), 0, 0);
		shapePaneSelection.add(new Label("Outline Color"), 1, 0);
		shapePaneSelection.add(new Label("Fill Color"), 2, 0);
		shapePaneSelection.add(this.options, 0, 1);
		shapePaneSelection.add(this.colorsOutline, 1, 1);
		shapePaneSelection.add(this.colorsFill, 2, 1);
		return shapePaneSelection;

	}

	// Pane for 3 dimensional shapes user can select fill color.
	private GridPane create3DShapeSelection() {
		// Create Border for Shape creation section.
		shapePaneSelection = new GridPane();
		// Center in GUI
		shapePaneSelection.setAlignment(Pos.CENTER);
		// Set Spacing
		shapePaneSelection.setHgap(5.5);
		shapePaneSelection.setVgap(5.5);
		// Add labels and ComboBoxes
		shapePaneSelection.add(new Label("Shape"), 0, 0);
		shapePaneSelection.add(new Label("Fill Color"), 1, 0);
		shapePaneSelection.add(this.options, 0, 1);
		shapePaneSelection.add(this.colorsFill, 1, 1);
		return shapePaneSelection;

	}

	// Pane for 3 dimensional shapes user can select fill color.
	private GridPane create3Dimages(String[] colorsimg) {
		// Create Border for Shape creation section.
		this.imageColors = new ComboBox<>();
		this.imageColors.getItems().addAll(colorsimg);
		shapePaneSelection = new GridPane();
		// Center in GUI
		shapePaneSelection.setAlignment(Pos.CENTER);
		// Set Spacing
		shapePaneSelection.setHgap(5.5);
		shapePaneSelection.setVgap(5.5);
		// Add labels and ComboBoxes
		shapePaneSelection.add(new Label("Shape"), 0, 0);
		shapePaneSelection.add(new Label("Fill Color"), 1, 0);
		shapePaneSelection.add(this.options, 0, 1);
		shapePaneSelection.add(this.imageColors, 1, 1);
		return shapePaneSelection;

	}

	// Generate Shape fill and dimension requirements based on dimensional values
	// and line/fill options.
	private EventHandler createDimensionPane() {
		// Remove existing shape and dimensional panes so the new ones can be added
		this.guiLayout.getChildren().remove(shapePane);
		this.guiLayout.getChildren().remove(initialCombo);
		this.guiLayout.getChildren().remove(shapePaneSelection);
		this.guiLayout.getChildren().remove(initialCombo);
		switch (this.menuArray.indexOf(this.options.getValue())) {
		case 0:
			guiLayout.add(initialCombo, 0, 1);
			this.guiLayout.getChildren().remove(shapePaneSelection);
			this.guiLayout.getChildren().remove(dimLayout);
			break;
		case 1:
			Circle2D circle = new Circle2D();
			dimLayout = dimensionPane(circle.getVars());
			this.guiLayout.add(create2DShapeSelection(), 0, 1);

			break;
		case 2:
			Rectangle2D rect = new Rectangle2D();
			dimLayout = dimensionPane(rect.getVars());
			this.guiLayout.add(create2DShapeSelection(), 0, 1);
			break;
		case 3:
			Square square = new Square();
			dimLayout = dimensionPane(square.getVars());
			this.guiLayout.add(create2DShapeSelection(), 0, 1);

			break;
		case 4:
			Triangle triangle = new Triangle();
			dimLayout = dimensionPane(triangle.getVars());
			this.guiLayout.add(create2DShapeSelection(), 0, 1);
//			System.out.println(triangle.toString());
			break;
		case 5:
			SphereShape sphere = new SphereShape();
			this.guiLayout.add(create3DShapeSelection(), 0, 1);
			dimLayout = dimensionPane(sphere.getVars());
			break;
		case 6:
			Cube cube = new Cube();
			dimLayout = dimensionPane(cube.getVars());
			this.guiLayout.add(create3DShapeSelection(), 0, 1);
			break;
		case 7:
			Cone cone = new Cone();
			this.guiLayout.add(create3Dimages(cone.getColors()), 0, 1);
			dimLayout = dimensionPane(cone.getVars());
			break;
		case 8:
			Cylinder3D cylinder = new Cylinder3D();
			this.guiLayout.add(create3DShapeSelection(), 0, 1);
			dimLayout = dimensionPane(cylinder.getVars());
			break;
		case 9:
			Torus torus = new Torus();
			this.guiLayout.add(create3Dimages(torus.getColors()), 0, 1);
			dimLayout = dimensionPane(torus.getVars());
			break;

		}
		// Add dimension pane to the main GUI.
		this.guiLayout.add(dimLayout, 0, 2);
		return null;

	}

	// Method to convert color combo box to color
	private Color getColor(int colorSelect) {
		Color selected = null;
		switch (colorSelect) {
		case 1:
			selected = Color.BLACK;
			break;
		case 2:
			selected = Color.BLUE;
			break;
		case 3:
			selected = Color.CYAN;
			break;
		case 4:
			selected = Color.DARKGRAY;
			break;
		case 5:
			selected = Color.GRAY;
			break;
		case 6:
			selected = Color.LIGHTGRAY;
			break;
		case 7:
			selected = Color.GREEN;
			break;
		case 8:
			selected = Color.MAGENTA;
			break;
		case 9:
			selected = Color.ORANGE;
			break;
		case 10:
			selected = Color.PINK;
			break;
		case 11:
			selected = Color.RED;
			break;
		case 12:
			selected = Color.WHITE;
			break;
		case 13:
			selected = Color.YELLOW;
			break;
		}
		return selected;
	}

	// EventHandeler to create the selected shape with selected options.
	private EventHandler createSelectedShape() throws InvalidNumberEntered {
		// Remove previously painted shape if it exists.
		this.guiLayout.getChildren().remove(shapePane);
		// Make a new shapePane
		shapePane = new FlowPane();

		// Set 2D outline color.
		Color fill = getColor(this.colorArray.indexOf(this.colorsFill.getValue()));
		// Set 2D and 3D fill color.
		Color outline = getColor(this.colorArray.indexOf(this.colorsOutline.getValue()));
		// Set 3D shape properties
		Group pane3D = new Group();
		Group shape3Dpane = new Group();
		SubScene shapeCameraScene;
		PhongMaterial color = new PhongMaterial();
		PerspectiveCamera camera = new PerspectiveCamera(false);
		switch (this.menuArray.indexOf(this.options.getValue())) {
		case 1:
			Circle2D circle = new Circle2D();
		try {
			
			circle.setRadius(this.dim.getText());
		} catch (Exception e) {
			throw new InvalidNumberEntered(circle.getName());
		}
			shapePane.getChildren().add(circle.print(outline, fill));
			break;
		case 2:
			Rectangle2D rect = new Rectangle2D();
			try {
				rect.setLength(this.dim.getText());
				rect.setWidth(this.dim1.getText());
			} catch (Exception e) {
				throw new InvalidNumberEntered(rect.getName());
			}
			shapePane.getChildren().add(rect.print(outline, fill));
			break;
		case 3:
			Square square = new Square();
			try {
				square.setLength(this.dim.getText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new InvalidNumberEntered(square.getName());
			}
			shapePane.getChildren().add(square.print(outline, fill));
			break;
		case 4:
			Triangle triangle = new Triangle();
			try {
				triangle.setLength(this.dim.getText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new InvalidNumberEntered(triangle.getName());
			}
			shapePane.getChildren().add(triangle.print(outline, fill));

			break;
		case 5:
			SphereShape sphere = new SphereShape();
			try {
				sphere.setRadius(this.dim.getText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new InvalidNumberEntered(sphere.getName());
			}
			Sphere sphere3D = new Sphere(sphere.getRadius());

			// Set type and color for 3d Shapes
			color.setDiffuseColor(fill);
			sphere3D.setMaterial(color);

			// Center shape in the scene
			sphere3D.setTranslateX(sphere3D.getRadius());
			sphere3D.setTranslateY(sphere3D.getRadius());
			sphere3D.setTranslateZ(sphere3D.getRadius());

			// set pivot point for rotating shape.
			rotateX.setPivotX(sphere3D.getRadius());
			rotateX.setPivotY(sphere3D.getRadius());
			rotateX.setPivotZ(sphere3D.getRadius());

			rotateY.setPivotX(sphere3D.getRadius());
			rotateY.setPivotY(sphere3D.getRadius());
			rotateY.setPivotZ(sphere3D.getRadius());

			rotateZ.setPivotX(sphere3D.getRadius());
			rotateZ.setPivotY(sphere3D.getRadius());
			rotateZ.setPivotZ(sphere3D.getRadius());

			// Assign camera values
			camera.getTransforms().addAll(rotateX, rotateY, new Translate(0, 0, 0));

			// re-initialize groups;
			pane3D = new Group();
			shape3Dpane = new Group();

			// Create sub scene for shape and camera
			shapeCameraScene = new SubScene(shape3Dpane, sphere3D.getRadius() * 2, sphere3D.getRadius() * 2, true,
					SceneAntialiasing.BALANCED);

			// Add components to sub scene
			shapeCameraScene.setCamera(camera);
			shape3Dpane.getChildren().add(sphere3D);

			// Add sub scene to main scene
			pane3D.getChildren().add(shapeCameraScene);

			// events for rotation
			shapeCameraScene.setOnMousePressed(event -> {
				mouseOldX = event.getSceneX();
				mouseOldY = event.getSceneY();
			});

			shapeCameraScene.setOnMouseDragged(event -> {
				if (event.isPrimaryButtonDown()) {
					rotateX.setAngle(rotateX.getAngle() - (event.getSceneY() - mouseOldY));
					rotateY.setAngle(rotateY.getAngle() + (event.getSceneX() - mouseOldX));
					mouseOldX = event.getSceneX();
					mouseOldY = event.getSceneY();
				}
			});
			// Add 3d Object to shape pane to be added to main GUI.
			shapePane.getChildren().add(pane3D);

			break;
		case 6:
			Cube cube = new Cube();
			try {
				cube.setWidth(this.dim.getText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new InvalidNumberEntered(cube.getName());
			}
			Box box = new Box();
			// Set Dimensions of the box
			box.setWidth(cube.getwidth());
			box.setHeight(cube.getwidth());
			box.setDepth(cube.getwidth());

			// Set type and color for 3d Shapes
			color.setDiffuseColor(fill);
			box.setMaterial(color);

			// Center shape in the scene
			box.setTranslateX(cube.width);
			box.setTranslateY(cube.width);
			box.setTranslateZ(cube.width);

			// Set initial rotational view of cube
			rotateX.setAngle(-45);
			rotateY.setAngle(-45);

			// set pivot point for rotating shape.
			rotateX.setPivotX(cube.width);
			rotateX.setPivotY(cube.width);
			rotateX.setPivotZ(cube.width);

			rotateY.setPivotX(cube.width);
			rotateY.setPivotY(cube.width);
			rotateY.setPivotZ(cube.width);

			rotateZ.setPivotX(cube.width);
			rotateZ.setPivotY(cube.width);
			rotateZ.setPivotZ(cube.width);

			// Assign camera values.
			camera.getTransforms().addAll(rotateX, rotateY, new Translate(0, 0, 0));

			// Recreate groups/
			pane3D = new Group();
			shape3Dpane = new Group();

			// Create subScene for the shape and camera
			shapeCameraScene = new SubScene(shape3Dpane, cube.width * 2, cube.width * 2, true,
					SceneAntialiasing.BALANCED);

			// Add components to sub scene.
			shapeCameraScene.setCamera(camera);
			shape3Dpane.getChildren().add(box);

			// Add sub scene to main scene
			pane3D.getChildren().add(shapeCameraScene);

			// events for rotation
			shapeCameraScene.setOnMousePressed(event -> {
				mouseOldX = event.getSceneX();
				mouseOldY = event.getSceneY();
			});

			shapeCameraScene.setOnMouseDragged(event -> {
				if (event.isPrimaryButtonDown()) {
					rotateX.setAngle(rotateX.getAngle() - (event.getSceneY() - mouseOldY));
					rotateY.setAngle(rotateY.getAngle() + (event.getSceneX() - mouseOldX));
					mouseOldX = event.getSceneX();
					mouseOldY = event.getSceneY();
				}
			});
			// Add 3d Object to shape pane to be added to main GUI.
			shapePane.getChildren().add(pane3D);
			break;
		case 7:
			shapePane.getChildren().add(returnCone(this.imageColors.getValue()));
			break;
		case 8:
			Cylinder3D cylinder = new Cylinder3D();
			try {
			cylinder.setHeight(this.dim1.getText());
			cylinder.setRadius(this.dim.getText());
			}catch(Exception e) {
				throw new InvalidNumberEntered(cylinder.getName());
			}
			Cylinder draw = new Cylinder(cylinder.getRadius(), cylinder.getHeight());

			color.setDiffuseColor(fill);
			draw.setMaterial(color);

			draw.setTranslateX(draw.getRadius());
			draw.setTranslateY(draw.getHeight());
			draw.setTranslateZ(draw.getRadius());

			// Set initial rotational view of cube
			rotateX.setAngle(-45);

			// set pivot point for rotating shape.
			rotateX.setPivotX(draw.getRadius());
			rotateX.setPivotY(draw.getHeight());
			rotateX.setPivotZ(draw.getRadius());

			rotateY.setPivotX(draw.getRadius());
			rotateY.setPivotY(draw.getHeight());
			rotateY.setPivotZ(draw.getRadius());

			rotateZ.setPivotX(draw.getRadius());
			rotateZ.setPivotY(draw.getHeight());
			rotateZ.setPivotZ(draw.getRadius());

			// Assign camera values
			camera.getTransforms().addAll(rotateX, rotateY, new Translate(0, 0, 0));

			// Recreate groups
			pane3D = new Group();
			shape3Dpane = new Group();

			// Create sub scene for shape and camera
			shapeCameraScene = new SubScene(shape3Dpane, draw.getRadius() * 3, draw.getHeight() * 2, true,
					SceneAntialiasing.BALANCED);

			// Add shape and camera to sub scene.
			shapeCameraScene.setCamera(camera);
			shape3Dpane.getChildren().add(draw);

			// Add sub scene to main scene
			pane3D.getChildren().add(shapeCameraScene);

			// events for rotation
			shapeCameraScene.setOnMousePressed(event -> {
				mouseOldX = event.getSceneX();
				mouseOldY = event.getSceneY();
			});

			shapeCameraScene.setOnMouseDragged(event -> {
				if (event.isPrimaryButtonDown()) {
					rotateX.setAngle(rotateX.getAngle() - (event.getSceneY() - mouseOldY));
					rotateY.setAngle(rotateY.getAngle() + (event.getSceneX() - mouseOldX));
					mouseOldX = event.getSceneX();
					mouseOldY = event.getSceneY();
				}
			});

			// Add 3D object to shape pane to be added to main GUI.
			shapePane.getChildren().add(pane3D);
			break;
		case 9:
//			Torus torusShape = new Torus();
			shapePane.getChildren().add(returnTorus(this.imageColors.getValue()));

			break;

		}
		shapePane.setAlignment(Pos.CENTER);
		shapePane.setHgap(10);
		shapePane.setVgap(10);

		// Add the shape to the main GUI.
		this.guiLayout.add(shapePane, 0, 0);
		return null;

	}

	// Creates a pane that accepts user dimensions.
	public GridPane dimensionPane(String[] vars) {
		this.guiLayout.getChildren().remove(this.dimLayout);
		dimLayout = new GridPane();
		dimLayout.setHgap(5);
		this.dim = new TextField();
		this.lab = new Label(vars[1]);
		this.dim1 = new TextField();
		// If there are 2 dimensions return pane with two dimension fields.
		if (Integer.valueOf(vars[0]) == 2) {
			this.lab1 = new Label(vars[2]);
			dimLayout.add(this.lab, 0, 0);
			dimLayout.add(this.dim, 1, 0);
			dimLayout.add(this.lab1, 0, 1);
			dimLayout.add(this.dim1, 1, 1);
			// If there is only one dimensions return pane with one dimension fields.
		} else if (Integer.valueOf(vars[0]) == 0) {
			dimLayout.add(this.lab, 0, 0);
		} else {
			dimLayout.add(this.lab, 0, 0);
			dimLayout.add(this.dim, 1, 0);
		}

		dimLayout.setAlignment(Pos.CENTER);
		return dimLayout;

	}

	// Set Shape options choices from string array.
	public void setShapeChoice(String[] options) {
		this.options.getItems().addAll(options);
	}

	public FlowPane buttons() {
		FlowPane buttons = new FlowPane();
		buttons.setAlignment(Pos.CENTER);
		buttons.setVgap(5);
		buttons.setHgap(10);

		buttons.getChildren().add(createShape);
		buttons.getChildren().add(exit);

		return buttons;

	}

	// Set the color selection options from string array outline and fill.
	public void setColorOptions(String[] colors) {
		this.colorsOutline.getItems().addAll(colors);
		this.colorsFill.getItems().addAll(colors);
	}

	private Group returnTorus(String selectedColor) {
		String image = null;

		try {
			if (selectedColor.equalsIgnoreCase("Red")) {
				image = "..\\Shapes\\torus_Red.JPG";
			} else if (selectedColor.equalsIgnoreCase("Blue")) {
				image = "..\\Shapes\\torus_Blue.JPG";
			} else if (selectedColor.equalsIgnoreCase("Yellow")) {
				image = "..\\Shapes\\torus_Yellow.JPG";
			} else {
				image = "..\\Shapes\\torus_Green.JPG";
			}
			Image torus = new Image(new FileInputStream(image));
			ImageView torusView = new ImageView(torus);
			Group torusGroup = new Group(torusView);
			return torusGroup;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private Group returnCone(String selectedColor) {

		String image = null;

		try {
			if (selectedColor.equalsIgnoreCase("Wood")) {
				image = "..\\Shapes\\cone_Wood.JPG";
			} else if (selectedColor.equalsIgnoreCase("Blue")) {
				image = "..\\Shapes\\cone_Blue.JPG";
			} else if (selectedColor.equalsIgnoreCase("Yellow")) {
				image = "..\\Shapes\\cone_Yellow.JPG";
			} else {
				image = "..\\Shapes\\cone_Green.JPG";
			}
			Image cone = new Image(new FileInputStream(image));
			ImageView coneView = new ImageView(cone);
			Group coneGroup = new Group(coneView);
			return coneGroup;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
