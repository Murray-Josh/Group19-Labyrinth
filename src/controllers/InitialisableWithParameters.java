package controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Allows a FXML Controller Class to be initialised with arguments
 *
 * @author Joseph Omar
 * @version 1.0
 */
public interface InitialisableWithParameters {

    /**
     * Method to use the parameters passed to it
     *
     * @param parameters   Parameters needed to initialise the controller
     * @param primaryStage The stage of the new controller
     * @param scene        The controller scene
     */
    void initialiseWithParameters(Object[] parameters, Scene scene, Stage primaryStage);
}
