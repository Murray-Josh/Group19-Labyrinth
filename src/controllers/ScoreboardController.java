package controllers;

import static controllers.StageController.home;
import static controllers.StageController.showError;

import constants.ErrorMessage;
import constants.LevelType;
import constants.Title;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import players.Player;
import players.PlayerProfile;
import players.Profiles;

/**
 * Controls the Scoreboard scene
 *
 * @author Joseph Omar
 * @version 1.1
 */
@SuppressWarnings("unused")
public class ScoreboardController implements Initializable {

   @SuppressWarnings("rawtypes")
   @FXML
   private TableView tblTable;
   @FXML
   private TableColumn<PlayerProfile, String> colNickname;
   @FXML
   private TableColumn<PlayerProfile, Integer> colGames;
   @FXML
   private TableColumn<PlayerProfile, Integer> colWins;
   @FXML
   private TableColumn<PlayerProfile, Integer> colLosses;
   @FXML
   private TableColumn<PlayerProfile, Float> colPercentage;
   @FXML
   private Button cmdBack;
   private LevelType levelType;

   /**
    * Goes back to the main menu
    *
    * @param actionEvent Event
    */
   public void cmdBackClick(ActionEvent actionEvent) {
      home();
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
      showGameboardChoiceDialog();
      ArrayList<PlayerProfile> profiles = null;
      try {
         profiles = Profiles.getProfiles();
      } catch (Exception e) {
         showError(ErrorMessage.PROFILE_LOAD_ERROR, Title.SCOREBOARD, false);
      } finally {
         if (profiles == null) {
            nullListHandler();
         } else {
            populate(profiles);
         }
      }
   }

   private void showGameboardChoiceDialog() {
      ChoiceDialog dialog = new ChoiceDialog();
      dialog.setGraphic(null);
      dialog.setHeaderText("Select a Gameboard");
      dialog.setContentText(null);
      dialog.setTitle(Title.SCOREBOARD.toString());
      dialog.getDialogPane().getStylesheets().add("./resources/css/dialog.css");
      dialog.getItems().addAll(LevelType.BIG, LevelType.MEDIUM, LevelType.SMALL);
      Optional<LevelType> result = dialog.showAndWait();
      if (result.isPresent()) {
         this.levelType = result.get();
      } else {
         dialog.close();
         home();
      }
   }

   /**
    * Handles the if the profiles list read in is null
    */
   @SuppressWarnings("OptionalGetWithoutIsPresent")
   private void nullListHandler() {
      Alert alert = new Alert(Alert.AlertType.ERROR,
           "There are no profiles to display, would you " +
                "like to create one?",
           ButtonType.YES, ButtonType.NO);
      alert.setHeaderText("No Profile Data");
      alert.setTitle("Scoreboard");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.YES) {
         Profiles.showCreate();
      } else {
         home();
      }
   }

   /**
    * Populates the scoreboard with profiles
    *
    * @param profiles Profiles to be added to the scoreboard table
    */
   @SuppressWarnings("unchecked")
   private void populate(ArrayList<PlayerProfile> profiles) {
      tblTable.getItems().addAll(profiles);
      /* Make the columns use the correct PlayerProfile attributes */
      colNickname.setCellValueFactory(
           data -> new ReadOnlyStringWrapper(data.getValue().getName()));
      colGames.setCellValueFactory(data -> new ReadOnlyIntegerWrapper(
           data.getValue().getNumOfGames()).asObject());
      colWins.setCellValueFactory(data -> new ReadOnlyIntegerWrapper(
           data.getValue().getNumOfWins()).asObject());
      colLosses.setCellValueFactory(data -> new ReadOnlyIntegerWrapper(
           data.getValue().getNumOfLosses()).asObject());
      colPercentage.setCellValueFactory(data -> new ReadOnlyFloatWrapper(
           data.getValue().getWinPercentage()).asObject());

   }


}
