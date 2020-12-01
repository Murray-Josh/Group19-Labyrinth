package controllers;

/**
 * Allows a FXML Controller Class to be initialised with arguments
 */
public interface InitialisableWithParameters {
    /**
     * Method to use the parameters passed to it
     * @param parameters
     */
    void initialiseWithParameters(Object[] parameters);
}
