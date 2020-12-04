package core;

import controllers.StageController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


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
