



import java.util.concurrent.atomic.AtomicBoolean;

public class Vehicle implements Runnable {
    // Thread to run vehicle
    private Thread vehicleThread;
    // Variables for vehicle inforamtion
    private String vehicleType = "";
    private int xPosit = 0;
    private final int yPosit = 0;
    private int vehicleSpeed = 0;
    private int numblights = 0;
    private int distanceToTravel = 3050;

    // variables to track/change running status
    private  AtomicBoolean running = new AtomicBoolean(true);
    private  AtomicBoolean stoppedForLight = new AtomicBoolean(false);
    private  AtomicBoolean paused = new AtomicBoolean(false);

    // Constructor to create a vehicle. All cars start at x,y position 0,0 at time
    // of creation.
    public Vehicle(String name) {
        vehicleType = name;
        System.out.println("New vehicle created " + name);
    }

    // Constructor to create a vehicle. If there are already intersections the
    // vehicle is notified about then at its time of creation.
    public Vehicle(String name, int numbLight) {
        vehicleType = name;
        this.numblights = numbLight;
        checkLightAmount(numbLight);
        System.out.println("New vehicle created " + name);
    }

    // ---------------Button Interactions -----------------------//

    // Create thread for the vehicle and start running when addCar button is
    // pressed.
    public void go() {
        if (vehicleThread == null) {
            vehicleThread = new Thread(this, vehicleType);
            vehicleThread.start();
        }
    }

  /*   // Kill the thread of stop button on GUI is pressed.
    public void exit() {
        vehicleThread.interrupt();
        running.set(false);

    } */

    // Pause the thread when pause button is pressed on the GUI
    public void pauseScenario() {
        paused.set(true);
        System.out.println(vehicleThread + " is sleeping");
    }

    // Continue demo when continue button is pressed on GUI or light has changed
    // from Red.
    public void continueScenario() {
        if (stoppedForLight.get() || paused.get()) {
            stoppedForLight.set(false);
            paused.set(false);
            this.notify();
        }
    }
    // ---------------Button Interactions End---------------------//

    // ------------Stoplight Logic---------------------------//
    // Method checks the status of the light. If a car is waiting or needs to
    // stop.
    public synchronized void pauseForLight(String lightColor) {
        // Car at light poition but hasnt stopped already.
        if (lightColor.equalsIgnoreCase("Red") && stoppedForLight.get() == false) {
            // System.out.println("Light color is " + lightColor + " " + vehicleType +
            // "stopping for light");
            stopForLight();
            // Car is waiting for light to turn green before preceeding.
        } else if (stoppedForLight.get() == true && lightColor.equalsIgnoreCase("Green")) {
            greenLight();
        }
    }

    // Stops vehicle at the light.
    private void stopForLight() {
        stoppedForLight.set(true);
        // System.out.println(vehicleType + " stopped for the light" + "Position = " +
        // xPosit);
    }

    // Starts vehicle motion again.
    private void greenLight() {
        // System.out.println(vehicleType + "Light is green driving again");
        stoppedForLight.set(false);
        this.notify();
    }
    // ------------Stoplight Logic---------------------------//

    // ----------------Vehicle Information ---------------------//

    // Method to return X positon of the vehicle
    public synchronized int getXPosit() {
        return xPosit;
    }

    // Method returns Y position of vehicle
    public synchronized int getYPosit() {
        return yPosit;
    }

    // Tell the thread to resume internally to aviod thread not owned error.
    public synchronized void continueButton() {
        continueScenario();
    }

    // Returns speed of vehicle if at red light or paused speed is 0 else its 25mps
    // or 56MPH
    public int vehicleSpeed() {
        if (stoppedForLight.get() || paused.get()) {
            vehicleSpeed = 0;
        } else {
            // 15 Meters per second is ~56mph. 1500 meters per minute/ 90 kilometers per
            // second
            vehicleSpeed = 56;// MPH
        }
        return vehicleSpeed;
    }

    // Returns name of vehicle(Thread)
    public String vehicleName() {
        return vehicleType;
    }

    // Checks how many lights are present at time of creation. If there are more
    // then 3 then the overall distance the car will travel is extended.
    private void checkLightAmount(int lights) {
        if (lights > 3) {
            distanceToTravel = (lights * 1000) + 50;
        }
    }

    // Increments number of lights after the car object has been created to account
    // for intersetions added during runtime.
    public void addIntersection() {
        numblights += 1;
        if (numblights > 3) {
            distanceToTravel = (numblights * 1000) + 50;
        }
    }

    // ----------------Vehicle Information End ---------------------//

    @Override
    public void run() {
        int speed = 25; // meters per second
        System.out.println(vehicleType + " is driving");
        // Initialize movement of the vehicle
        running.set(true);
        while (running.get()) {
            try {
                // Distance to travel is based on the amount of intersections. Standard travel
                // is set to 3200 meters. If more then 3 lights are added then distance is
                // extended 1000 meters per light that is added.
                while (xPosit < distanceToTravel) {
                    synchronized (this) {
                        // If scenario is paused via the button or the vehicle is waiting at a light x
                        // is not incrimented.
                        while (stoppedForLight.get() || paused.get()) {
                            System.out.println(vehicleType + " Thread paused");
                            if (stoppedForLight.get() == true) {
                                xPosit = xPosit - speed;
                            }
                            wait();
                        }
                    }
                    // If thread is not paused update the vehicle position every second.
                    if (running.get()) {
                        vehicleThread.sleep(1000);
                        xPosit += speed;
                        // System.out.println(vehicleType + " Position = " + xPosit);
                    }
                }

            } catch (InterruptedException exec) {

            }
            // When vehicle completes it run set the value to signify GUI that thread has
            // terminated.
            xPosit = 1;
            running.set(false);
            System.out.println("exiting thread");
        } // End of while

    }// End of run method

}// End of Vehicle class