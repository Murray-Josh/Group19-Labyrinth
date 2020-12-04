package controllers;

import static controllers.StageController.changeScene;

import constants.Window;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import players.Profiles;

/**
 * Controls the Main Menu {@link javafx.scene.Scene}
 *
 * @author Joseph Omar
 * @version 2.1
 */
public class MainMenuController implements Initializable {

   @FXML
   private Button cmdNew;
   @FXML
   private Button cmdLoad;
   @FXML
   private Button cmdScoreboard;
   @FXML
   private Button cmdMakeProfile;
   @FXML
   private Button cmdDeleteProfile;
   @FXML
   private Button cmdQuit;
   @FXML
   private ImageView logo;
   @FXML
   private Label msgDayMessage;

   /**
    * Handles the Quit button click event
    *
    * @param mouseEvent
    */
   public void quitClicked(MouseEvent mouseEvent) {
      System.exit(0);
   }

   /**
    * Handles the Delete Profile button click event
    *
    * @param mouseEvent
    */
   public void cmdDeleteProfileClick(MouseEvent mouseEvent) {
      Profiles.showDelete();
   }

   /**
    * Handles the Create Profile button click event
    *
    * @param mouseEvent
    */
   public void cmdCreateProfileClick(MouseEvent mouseEvent) {
      Profiles.showCreate();
   }

   /**
    * Handles the Scoreboard button click event
    *
    * @param mouseEvent
    */
   public void cmdScoreboardClick(MouseEvent mouseEvent) {
      changeScene(Window.SCOREBOARD);
   }

   /**
    * Handles the Load button click event
    *
    * @param mouseEvent
    */
   public void cmdLoadClick(MouseEvent mouseEvent) {
      changeScene(Window.LOAD);
   }

   /**
    * Handles the new game button click event
    *
    * @param mouseEvent
    */
   public void cmdNewClick(MouseEvent mouseEvent) {
      changeScene(Window.SETUP);
   }

   /**
    * Called to initialize a controller after its root element has been completely processed.
    *
    * @param location  The location used to resolve relative paths for the root object, or
    *                  <tt>null</tt> if the location is not known.
    * @param resources The resources used to localize the root object, or
    *                  <tt>null</tt> if
    */
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      this.msgDayMessage.setText(FindMessage.getMessage());
   }
}
