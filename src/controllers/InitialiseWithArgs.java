package controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Allows a FXML Controller Class to be initialised with arguments
 */
public interface InitialiseWithArgs {
    /**
     * Method to use the arguments passed to it
     * @param args
     */
    void initializeWithArgs(Object[] args);
}
