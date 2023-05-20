


import javafx.application.Application;
import javafx.stage.Stage;

public class CarDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    // Set stage for car demo to run in.
    @Override
    public void start(Stage primaryStage) throws Exception {
        StopLightGUI gui = new StopLightGUI();
        primaryStage = gui.priStage();

    }
}
