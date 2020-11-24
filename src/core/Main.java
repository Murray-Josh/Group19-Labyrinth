package core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/MainMenu.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Labyrinth - Group 19");
        stage.setResizable(false);
        stage.show();
    }

    public void changeScene(String fxmlPath, String title) {
        Scene scene;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlPath"));
            Parent root = fxmlLoader.load();
            scene = new Scene(root);
            stage.hide();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showError("The program could not find the FXML file of this window.", "Error Displaying Window",
                    "Error");
        }


    }

    public void showConformation(String msg, String heading, String title) {
//
    }

    /**
     * Displays an Error Dialog containing the passed messages
     * @param msg Message to be displayed
     * @param heading The Heading to be displayed
     * @param title The title of the dialog box window
     */
    public void showError(String msg, String heading, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(heading);
        alert.setContentText(msg);
        alert.showAndWait();
        System.exit(0);
    }


}
