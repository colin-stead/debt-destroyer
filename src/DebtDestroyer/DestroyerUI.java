package DebtDestroyer;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DestroyerUI extends Application {

    @Override
    public void start(Stage stage) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("debtdestroyer.fxml"));
	        Scene scene = new Scene(root, 775, 600);
	        stage.setTitle("Debt Destroyer");
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }

     public static void main(String[] args) {
         launch();
     }

}
