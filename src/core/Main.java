package core;

import fxml.StageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

public class Main extends Application {



    public static void main(String[] args) {
        launch(args);
    }



    /**
     * Starts the program
     * @param primaryStage Stage passed from javafx
     */
    @Override
    public void start(Stage primaryStage) {
        StageController.start(primaryStage);
    }


}
