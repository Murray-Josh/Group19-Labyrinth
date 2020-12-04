package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
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
   private Stage stage;

   public void cmdSaveClick(MouseEvent mouseEvent) {
      controller.saveAndExit();
      stage.close();
   }

   public void cmdExitClick(MouseEvent mouseEvent) {
      controller.exit();

   }

   @Override
   public void initialiseWithParameters(Object[] parameters, Scene scene, Stage stage) {
      this.controller = (GameboardController) parameters[0];
      this.stage = stage;
   }

   public void cmdCancelClick(MouseEvent mouseEvent) {
      stage.close();
   }
}
