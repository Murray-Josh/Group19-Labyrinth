package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ExitDialogController implements InitialisableWithParameters {

   @FXML
   private Button cmdSave;
   @FXML
   private Button cmdExit;

   @FXML
   private Button cmdCancel;

   private GameboardController controller;

   public void cmdSaveClick(MouseEvent mouseEvent) {
      controller.saveAndExit();
      Stage stage = (Stage) cmdExit.getScene().getWindow();
      stage.close();
   }

   public void cmdExitClick(MouseEvent mouseEvent) {
      controller.exit();
      Stage stage = (Stage) cmdExit.getScene().getWindow();
      stage.close();
   }

   @Override
   public void initialiseWithParameters(Object[] parameters) {
      this.controller = (GameboardController) parameters[0];
   }

   public void cmdCancelClick(MouseEvent mouseEvent) {
      Stage stage = (Stage) cmdExit.getScene().getWindow();
      stage.close();
   }
}
