package controllers;

import constants.ErrorMsg;
import constants.Title;
import constants.Window;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Controls all operations on the {@link Stage}
 *
 * @author Joseph Omar
 * @version 2.0
 */
public class StageController {
    private static Stage  stage;
    private static Scene  previousScene;
    private static String previousTitle;

    /**
     * Navigates to the previous scene
     */
    public static void previousScene() {
        setNewScene(previousScene, previousTitle);
    }

    /**
     * Sets the scene on the stage to the specified scene
     *
     * @param scene New scene to be shown
     * @param title Title of the window, as string
     *
     * @throws IllegalStateException If the scene parameter is null
     */
    private static void setNewScene(Scene scene, String title) {
        if (scene != null) {
            previousScene = stage.getScene();
            previousTitle = stage.getTitle();
            stage.hide();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            throw new IllegalStateException("Cannot display null scene");
        }
    }

    /**
     * Displays a Conformation Dialog containing the passes strings
     *
     * @param msg     Message to be displayed
     * @param heading The Heading to be displayed
     * @param title   The title of the dialog box window
     */
    public static void showConfirmation(String msg, String heading, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, msg, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(heading);
        alert.getDialogPane().getStylesheets().add("/resources/css/dialog.css");
        alert.showAndWait();
    }

    /**
     * Opens the main menu on startup
     *
     * @param primaryStage
     */
    public static void start(Stage primaryStage) {
        stage = primaryStage;
        home();
    }

    /**
     * changes the scene to the main menu
     */
    public static void home() {
        stage.getIcons().add(new Image(StageController.class.getResourceAsStream("/resources/me/Gun_Raccoon.png")));
        changeScene(Window.HOME);
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    /**
     * Changes the scene to a specified Window
     *
     * @param window {@link Window} to open
     */
    public static void changeScene(Window window) {
        Scene scene = null;
        try {
            Parent root = FXMLLoader.load(StageController.class.getResource(window.getPath()));
            scene = new Scene(root);
            setNewScene(scene, window.getTitle());
        } catch (IOException e) {
            e.printStackTrace();
            showError(ErrorMsg.FXML_NOT_FOUND, Title.ERROR, false);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays an Error Dialog containing the passed strings
     *
     * @param error {@link ErrorMsg} template to use
     * @param quit  Whether to quit the application
     *
     * @parm title The title to use (member of {@link Title})
     */
    public static void showError(ErrorMsg error, Title title, boolean quit) {
        Alert alert = new Alert(Alert.AlertType.ERROR, error.getMessage(), ButtonType.OK);
        alert.setTitle(title.toString());
        alert.setHeaderText(error.getHeader());
        alert.getDialogPane().getStylesheets().add("/resources/css/dialog.css");
        Optional<ButtonType> result = alert.showAndWait();
        if (quit && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
}
