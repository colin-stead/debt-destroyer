


import java.util.concurrent.atomic.AtomicBoolean;

// A simulation of a traffic light that uses 
// an enumeration to describe the light's color. 

// An enumeration of the colors of a traffic light. 
enum TrafficLightColor {
  RED, GREEN, YELLOW
}

// A computerized traffic light.
public class TrafficLightSimulator implements Runnable {
  // Thread
  private Thread trafficLight;
  private String lightName = " ";
  private TrafficLightColor tlc; // holds the current traffic light color
  private AtomicBoolean running = new AtomicBoolean(false); // set to true to stop the simulation
  private AtomicBoolean paused = new AtomicBoolean(false);
  private int distance = 0;

  //Constructor to create a stoplight and place it at a x location. Intersections are placed 1000 meters apart. 
  TrafficLightSimulator(TrafficLightColor init, int distance, String lightName) {
    this.lightName = lightName;
    tlc = init;
    this.distance = distance;
  }

  TrafficLightSimulator() {
    tlc = TrafficLightColor.RED;
  }

  // Start up the light.
  public void run() {
    System.out.println("Running " + lightName);
    running.set(true);
    while (running.get()) {
      try {
        //If paused button is pressed at GUI wait until told to continue.
        synchronized (this) {
          while (paused.get()) {
            wait();
          }
        }
        switch (tlc) {
          case GREEN:
            // System.out.println(lightName + " color is " + colortoString());
            Thread.sleep(10000); // green for 10 seconds
            break;
          case YELLOW:
            // System.out.println(lightName + " color is " + colortoString());
            Thread.sleep(2000); // yellow for 2 seconds
            break;
          case RED:
            // System.out.println(lightName + " color is " + colortoString());
            Thread.sleep(12000); // red for 12 seconds
            break;
        }
      } catch (InterruptedException exc) {
        System.out.println(exc);
      }
      changeColor();
    }
  }

  // Change color.
  synchronized void changeColor() {
    switch (tlc) {
      case RED:
        tlc = TrafficLightColor.GREEN;
        break;
      case YELLOW:
        tlc = TrafficLightColor.RED;
        break;
      case GREEN:
        tlc = TrafficLightColor.YELLOW;
    }

  }

  // Stop the traffic light.
  public synchronized void stopLight() {
    running.set(false);
  }

  // start the traffic light
  public void go() {
    // System.out.println("Starting " + lightName);
    if (trafficLight == null) {
      trafficLight = new Thread(this, lightName);
      trafficLight.start();
    }
  }
  //Returns light color in sting form.
  public synchronized String colortoString() {
    return tlc.toString();
  }
  public synchronized String name(){
    return lightName;
  }

  // Returns x position of the stoplight so a car can figure out if it is at a
  // stoplight and stop if the light is Red.
  public String getXPosit() {
    return String.valueOf(distance);
  }

  public synchronized void pauseLight() {
    paused.set(true);

  }

  // Public method to tell the thread to coninue. When calling notify from the GUI
  // I was getting an error because I didnt own the thread.
  public synchronized void continueButton() {
    continueLight();
  }

  // Tells the light to resume operations
  private synchronized void continueLight() {
    paused.set(false);
    this.notify();
  }


}