package controllers;

import static controllers.StageController.home;
import static controllers.StageController.showError;
import static java.lang.Integer.compare;

import constants.ErrorMessage;
import constants.LevelType;
import constants.Title;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
   private TableColumn<PlayerProfile, PlayerProfile> colNickname;
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
      Comparator<PlayerProfile> comparator = Comparator
              .comparingInt((PlayerProfile p) -> p.getWins(levelType));
      tblTable.getItems().addAll(profiles);
      /* Make the columns use the correct PlayerProfile attributes */
      try {
         colNickname.setCellValueFactory(
                 data -> new ReadOnlyObjectWrapper<>(data.getValue()));
         colGames.setCellValueFactory(data -> new ReadOnlyIntegerWrapper(
                 data.getValue().getGames(levelType)).asObject());
         colWins.setCellValueFactory(data -> new ReadOnlyIntegerWrapper(
                 data.getValue().getWins(levelType)).asObject());
         colLosses.setCellValueFactory(data -> new ReadOnlyIntegerWrapper(
                 data.getValue().getLosses(levelType)).asObject());
         colPercentage.setCellValueFactory(data -> new ReadOnlyFloatWrapper(
                 data.getValue().getWinPercentage(levelType)).asObject());
      }catch (NullPointerException e) {
         e.printStackTrace();
      }

   }


}
