package controllers;

import constants.ErrorMsg;
import constants.Title;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import players.PlayerProfile;
import players.Profiles;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static controllers.StageController.home;
import static controllers.StageController.showError;

/**
 * Controls the Scoreboard scene
 *
 * @author Joseph Omar
 * @version 1.1
 */
public class ScoreboardController implements Initializable {
    @FXML
    private TableView                           tblTable;
    @FXML
    private TableColumn<PlayerProfile, String>  colNickname;
    @FXML
    private TableColumn<PlayerProfile, Integer> colGames;
    @FXML
    private TableColumn<PlayerProfile, Integer> colWins;
    @FXML
    private TableColumn<PlayerProfile, Integer> colLosses;
    @FXML
    private TableColumn<PlayerProfile, Float>   colPercentage;
    @FXML
    private Button                              cmdBack;

    /**
     * Goes back to the main menu
     *
     * @param actionEvent
     */
    public void cmdBackClick(ActionEvent actionEvent) {
        home();
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root
     *                  object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or
     *                  <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<PlayerProfile> profiles = null;
        try {
            profiles = Profiles.getProfiles();
        } catch (Exception e) {
            showError(ErrorMsg.PROFILE_LOAD_ERROR, Title.SCOREBOARD, false);
        } finally {
            if (profiles == null) {
                nullListHandler();
            } else {
                populate(profiles);
            }
        }
    }

    /**
     * Handles the if the profiles list read in is null
     */
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
