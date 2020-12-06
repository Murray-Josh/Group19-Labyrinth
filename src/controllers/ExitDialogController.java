package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Allows the user the option of exiting the game or exiting and saving the game
 *
 * @author Joseph Omar
 * @version 1.1
 */
@SuppressWarnings("unused")
public class ExitDialogController implements InitialisableWithParameters {

    @FXML
    private Button cmdSave;
    @FXML
    private Button cmdExit;
    @FXML
    private Button cmdCancel;

    private GameboardController controller;
    private Stage stage;

    /**
     * Handles the event that occurs when the user clicks on the save and exit button
     *
     * @param mouseEvent Event
     */
    public void cmdSaveClick(MouseEvent mouseEvent) {
        controller.saveAndExit();
        stage.close();
    }

    /**
     * Handles the event that occurs when the user clicks on the exit button
     *
     * @param mouseEvent Event
     */
    public void cmdExitClick(MouseEvent mouseEvent) {
        controller.exit();

    }

    /**
     * Allows a controller to initialise this controller with parameters.
     *
     * @param parameters The parameters needed by this controller (Calling controller)
     * @param scene      This window's scene
     * @param stage      This window's stage
     */
    @Override
    public void initialiseWithParameters(Object[] parameters, Scene scene, Stage stage) {
        this.controller = (GameboardController) parameters[0];
        this.stage = stage;
    }

    /**
     * Handles the event that occurs when the user clicks on the cancel button
     *
     * @param mouseEvent Event
     */
    public void cmdCancelClick(MouseEvent mouseEvent) {
        stage.close();
    }
}
