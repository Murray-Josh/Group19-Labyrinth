package core;

import controllers.StageController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class that starts the application
 * @author Joseph Omar
 * @version 1.0
 */
public class Main extends Application {


   /**
    * Starts the application
    * @param args Argument list
    */
   public static void main(String[] args) {

      launch(args);


   }


   /**
    * Starts the program
    *
    * @param primaryStage Stage passed from javafx
    */
   @Override
   public void start(Stage primaryStage) {
      StageController.start(primaryStage);
   }


}
