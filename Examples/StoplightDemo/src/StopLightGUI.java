




import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StopLightGUI {

    // Info pane components
    private FlowPane infoPane = new FlowPane();
    private Label rtcLabel = new Label("Time");
    private Label carNumLabel = new Label("# Cars");
    private Label lightNumLabel = new Label("# Stoplights");
    private TextField rtc = new TextField("00:00:00");
    private TextField carNumTField = new TextField("0");
    private TextField lightsNumTField = new TextField("0");

    // Stoplights pane components
    private GridPane stopLightPane = new GridPane();
    private Label statusLabel = new Label("   Status");
    ArrayList<TrafficLightSimulator> lights = new ArrayList<TrafficLightSimulator>();
    private ArrayList<TextField> lightArrayTF = new ArrayList<TextField>();
    private ArrayList<TextField> lightPOSArrayTF = new ArrayList<TextField>();

    // Vehicle pane components
    private GridPane vehiclePane = new GridPane();
    private Label speedLabel = new Label("   Speed");
    private ArrayList<Vehicle> cArrayList = new ArrayList<Vehicle>();
    private ArrayList<TextField> carSpeedArray = new ArrayList<TextField>();
    private ArrayList<TextField> carXPositArray = new ArrayList<TextField>();
    private ArrayList<TextField> carYPositArray = new ArrayList<TextField>();

    // Button pane components
    private FlowPane buttons = new FlowPane();
    private Button startStopButton = new Button("Start");
    private Button pauseContButton = new Button("Pause");
    private Button addCarButton = new Button("Add Car");
    private Button addLightButton = new Button("Add Light");

    // Shared components
    private BorderPane guiLayout = new BorderPane();
    private Label xPosLabel = new Label("    X Pos");
    private Label yPosLabel = new Label("    Y Pos");

    // Stopwatch to track time in seconds
    private int runTime = 0;
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;
    private boolean stopped = true;
    private boolean running = false;
    private String secondsformatted = String.format("%02d", seconds);
    private String minutesformatted = String.format("%02d", minutes);
    private String hoursformatted = String.format("%02d", hours);
    private  Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(java.awt.event.ActionEvent e) {

            runTime = runTime + 1000;
            hours = (runTime / 3600000);
            minutes = (runTime / 60000) % 60;
            seconds = (runTime / 1000) % 60;
            secondsformatted = String.format("%02d", seconds);
            minutesformatted = String.format("%02d", minutes);
            hoursformatted = String.format("%02d", hours);
            // Update GUI status everysecond
            rtc.setText(hoursformatted + ":" + minutesformatted + ":" + secondsformatted);
            // System.out.println(hoursformatted + ":" + minutesformatted + ":" +
            // secondsformatted);
            updateLights();
            checklights();
            updateVehicles();

        }

    });

    // Create pane for the buttons
    private FlowPane buttons() {
        // flowpane layout
        buttons.setAlignment(Pos.CENTER);
        buttons.setVgap(5);
        buttons.setHgap(10);

        // add buttons
        buttons.getChildren().addAll(startStopButton, pauseContButton, addCarButton,
                addLightButton);

        return buttons;
    }

    // Create information pane
    private FlowPane informationPane() {
        // format layout
        infoPane.setAlignment(Pos.CENTER);
        infoPane.setHgap(5);
        infoPane.setVgap(10);
        carNumTField.setMaxWidth(25);
        lightsNumTField.setMaxWidth(25);
        rtc.setMaxWidth(60);
        rtc.setEditable(false);

        // Add components
        infoPane.getChildren().addAll(rtcLabel, rtc, carNumLabel, carNumTField, lightNumLabel, lightsNumTField);
        return infoPane;

    }

    // Create vehicle pane
    private GridPane vehiclePaneCreation() {
        Label vehicles = new Label("   Cars");
        vehiclePane.setHgap(10);
        vehiclePane.setVgap(10);
        vehiclePane.add(vehicles, 0, 0);
        vehiclePane.add(speedLabel, 1, 0);
        vehiclePane.add(new Label("  X Posit"), 2, 0);
        vehiclePane.add(yPosLabel, 3, 0);
        // Initialize Vehicle textfields.
        for (int i = 0; i < 3; i++) {
            carSpeedArray.add(new TextField("N/A"));
            carXPositArray.add(new TextField("N/A"));
            carYPositArray.add(new TextField("N/A"));
            vehiclePane.add(new Label(String.valueOf("      " + (i + 1))), 0, i + 1);
            vehiclePane.add(carSpeedArray.get(i), 1, i + 1);
            vehiclePane.add(carXPositArray.get(i), 2, i + 1);
            vehiclePane.add(carYPositArray.get(i), 3, i + 1);
            carSpeedArray.get(i).setMaxWidth(60);
            carXPositArray.get(i).setMaxWidth(60);
            carYPositArray.get(i).setMaxWidth(60);

        }

        return vehiclePane;
    }

    // Create the pane to hold the intersection information
    private GridPane intersectionCreation() {
        stopLightPane.setHgap(10);
        stopLightPane.setVgap(10);
        // Create header
        stopLightPane.add(new Label("Stoplight"), 0, 0);
        stopLightPane.add(statusLabel, 1, 0);
        stopLightPane.add(xPosLabel, 2, 0);
        makelightTF();
        // Create header and initialize Textfields
        for (int i = 0; i < lightArrayTF.size(); i++) {
            lightArrayTF.get(i).setMaxWidth(60);
            lightPOSArrayTF.get(i).setMaxWidth(60);
            stopLightPane.add(new Label(String.valueOf("      " + (i + 1))), 0, i + 1);
            stopLightPane.add(lightArrayTF.get(i), 1, i + 1);
            stopLightPane.add(lightPOSArrayTF.get(i), 2, i + 1);

        }

        return stopLightPane;
    }

    public Stage priStage() {
        GridPane centerGroup = new GridPane();
        Stage carDemoStage = new Stage();
        carDemoStage.setTitle("Car Thread Project");
        // Call methods to create panes
        buttons();
        informationPane();
        vehiclePaneCreation();
        intersectionCreation();
        // Add panes to the GUI
        centerGroup.add(stopLightPane, 0, 0);
        centerGroup.add(vehiclePane, 1, 0);
        centerGroup.setHgap(10);
        guiLayout.setTop(infoPane);
        guiLayout.setBottom(buttons);
        guiLayout.setCenter(centerGroup);
        // Add actions handlers to the buttons.
        javafx.event.EventHandler<ActionEvent> swController = e -> startSWAction();
        startStopButton.setOnAction(swController);
        javafx.event.EventHandler<ActionEvent> pauseContController = e -> pauseContAction();
        pauseContButton.setOnAction(pauseContController);
        javafx.event.EventHandler<ActionEvent> addCar = e -> anotherCar(carNumTField.getText());
        addCarButton.setOnAction(addCar);
        javafx.event.EventHandler<ActionEvent> addLight = e -> addStoplight(lightsNumTField.getText());
        addLightButton.setOnAction(addLight);

        // Create Scene
        Scene scene = new Scene(guiLayout, 450, 200);

        // Assign Scene to stage
        carDemoStage.setScene(scene);
        carDemoStage.show();
        return carDemoStage;
    }

    // Action when start button is pressed.
    private void startSWAction() {
        // Start running the scenario.
        if (stopped == true) {
            stopped = false;
            running = true;
            // Change the start button to a stop button
            startStopButton.setText("Stop");
            timer.start();
            // addStoplight("0");

        } else {
            // When stop button is pressed end the program.
            System.out.println("Stop pressed Exiting program");
            timer.stop();
            System.exit(0);
        }

    }

    // Action taken when pause or continue button is pressed.
    private void pauseContAction() {
        // If scenario is running user can pause scenario.
        if (running == true) {
            running = false;
            // Change pause button to continue button.
            pauseContButton.setText("Continue");
            System.out.println("Pause pressed " + "Running = " + running);
            // Pause all running threads.
            timer.stop();
            changeLightStatus();
            changeCarStatus();
            updateVehicles();
        } else {
            // Continue was pressed change state.
            running = true;
            // Change button to a pause button.
            pauseContButton.setText("Pause");
            System.out.println("Continue pressed" + "Running = " + running);
            // resume all threads.
            timer.start();
            changeLightStatus();
            changeCarStatus();
        }
    }

    // Method to add cars to the demo.
    private void anotherCar(String cars) {
        // Get amount of cars that currently exits and increment.
        int numbOfCars = Integer.valueOf(carNumTField.getText()) + 1;
        // Create a new car with the id numnber
        Vehicle car = new Vehicle("car" + String.valueOf(numbOfCars), lights.size());
        // Add car to arraylist and start the thread.
        cArrayList.add(car);
        car.go();
        carNumTField.setText(String.valueOf(numbOfCars));

    }

    // Method add a stoplight to the scenario.
    private void addStoplight(String stoplights) {
        int numbLight = Integer.valueOf(stoplights) + 1;
        // Create a new light object place lights 1000 meters apart
        TrafficLightSimulator tls = new TrafficLightSimulator(TrafficLightColor.GREEN, (numbLight) * 1000,
                "Light" + String.valueOf(numbLight));
        // Update GUI to track how many light are active
        lightsNumTField.setText(String.valueOf(numbLight));
        tls.go();
        lights.add(tls);
        // Inform all running car threads that another light has been added to the
        // scenario.
        if (cArrayList.size() > 0) {
            for (int i = 0; i < cArrayList.size(); i++) {
                cArrayList.get(i).addIntersection();
            }
        }
    }

    // Update the light values on the GUI and Terminal
    private void updateLights() {
        int numbLight = Integer.valueOf(lightsNumTField.getText());
        // get and updated status for all of the lights.
        for (int i = 0; i < numbLight; i++) {
            // Only display the first three lights on the GUI.
            if (i < 2 || i == 2) {
                lightArrayTF.get(i).setText(lights.get(i).colortoString());
                lightPOSArrayTF.get(i).setText(lights.get(i).getXPosit());

            }
            // Any intersection over three is updated to the console every 5 seconds.
            else if (seconds > 0 && seconds % 5 == 0 && i > 2) {
                System.out.println(lights.get(i).name() + " color is " + lights.get(i).colortoString());
            }
        }
    }

    // Update light values on GUI and Terminal
    private void updateVehicles() {
        if (carNumTField.getText().equalsIgnoreCase("N/A") || carNumTField.getText().equalsIgnoreCase("Ended")) {
        } else {
            int numbCars = Integer.valueOf(carNumTField.getText());
            for (int i = 0; i < numbCars; i++) {
                if (i < 2 || i == 2) {
                    // Car has gone through all lights and reached max distance thread has stopped.
                    if (cArrayList.get(i).getXPosit() == 1) {
                        carSpeedArray.get(i).setText("Ended");
                        carXPositArray.get(i).setText("Ended");
                        carYPositArray.get(i).setText("Ended");

                    } else {
                        // If cars have not gone through all of the lights track status.
                        carSpeedArray.get(i).setText(String.valueOf(cArrayList.get(i).vehicleSpeed()) + " MPH");
                        carXPositArray.get(i).setText(String.valueOf(cArrayList.get(i).getXPosit()));
                        carYPositArray.get(i).setText(String.valueOf(cArrayList.get(i).getYPosit()));
                    }
                    // Any vechicle over three is updated to the console every 5 seconds.
                } else if (seconds > 0 && seconds % 5 == 0 && i > 2) {
                    System.out.println(cArrayList.get(i).vehicleName() + ":\nSpeed is "
                            + cArrayList.get(i).vehicleSpeed() + "\nXPosit is " + cArrayList.get(i).getXPosit());

                }
            }
        }
    }

    // Acton that loops through all of the vehicles and either pause or resume them.
    private void changeCarStatus() {
        for (int i = 0; i < cArrayList.size(); i++) {
            if (running == false) {
                cArrayList.get(i).pauseScenario();
            } else {
                cArrayList.get(i).continueButton();
            }
        }
    }

    // Acton that loops through all of the stopLights and either pause or resume
    // them.
    private void changeLightStatus() {
        for (int i = 0; i < lights.size(); i++) {
            if (running == false) {
                lights.get(i).pauseLight();
            } else if (running == false) {

            } else {
                lights.get(i).continueButton();
            }
        }
    }

    // Check each car against each light to see if they are at the light when its
    // red
    private void checklights() {
        // if the location of the car %1000 = 0 then car is at a stop light
        for (int i = 0; i < cArrayList.size(); i++) {
            // Check each car againt the stoplights
            if (cArrayList.get(i).getXPosit() % 1000 == 0) {
                // Check to see what light the car is at and what the status of the light is.
                for (int j = 0; j < lights.size(); j++) {
                    if (cArrayList.get(i).getXPosit() == Integer.valueOf(lights.get(j).getXPosit())) {
                        // System.out.println(cArrayList.get(i).vehicleName() + " is at light " + (j +
                        // 1) + " location is "
                        // + lights.get(j).getXPosit());
                        cArrayList.get(i).pauseForLight(lights.get(j).colortoString());
                    }

                }
            }
        }
    }

    // Initialize all light textfields
    private void makelightTF() {
        for (int i = 0; i < 3; i++) {
            lightArrayTF.add(new TextField("N/A"));
            lightPOSArrayTF.add(new TextField("N/A"));
        }
    }

}// End StopLightGUI
