package controllers;

import constants.ErrorMsg;
import constants.Title;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import players.PlayerProfile;
import players.Profiles;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static controllers.StageController.home;
import static controllers.StageController.showError;

public class SetUpGameController implements Initializable {
    private static final PlayerProfile none = new PlayerProfile("--None--");
    private final ObservableList<PlayerProfile> profiles = FXCollections.observableArrayList(Profiles.getProfiles());
    private final ArrayList<PlayerProfile> players = new ArrayList<PlayerProfile>();
    @FXML
    private ChoiceBox<PlayerProfile> comOne;
    @FXML
    private ChoiceBox<PlayerProfile> comTwo;
    @FXML
    private ChoiceBox<PlayerProfile> comThree;
    @FXML
    private ChoiceBox<PlayerProfile> comFour;
    private ArrayList<ChoiceBox<PlayerProfile>> boxes;
    @FXML
    private ChoiceBox comBoard;
    @FXML
    private ChoiceBox comStyle;
    @FXML
    private Button cmdStart;
    @FXML
    private Button cmdBack;
    @FXML
    private Button cmdLockOne;
    @FXML
    private Button cmdLockTwo;
    @FXML
    private Button cmdLockThree;
    @FXML
    private Button cmdLockFour;
    private ArrayList<Button> buttons;
    private URL location;
    private ResourceBundle resourceBundle;

    public void cmdBackClick(ActionEvent actionEvent) {
        home();
    }

    public void cmdStartClick(ActionEvent actionEvent) {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.buttons = new ArrayList<>(Arrays.asList(cmdLockOne, cmdLockTwo, cmdLockThree, cmdLockFour));
        this.boxes = new ArrayList<>(Arrays.asList(comOne, comTwo, comThree, comFour));
        this.location = location;
        this.resourceBundle = resources;
        profiles.add(none);
        boxes.forEach(box -> {
            box.setItems(profiles);
            box.getSelectionModel().select(none);
        });
        boxes.remove(comOne);
        buttons.remove(cmdLockOne);
        boxes.forEach(box -> box.setDisable(true));
        buttons.forEach(button -> button.setDisable(true));
    }

    public void cmdLockFourClick(ActionEvent actionEvent) {
        lock(cmdLockFour, null, comFour, null, 4);
    }

    public void cmdLockThreeClick(ActionEvent actionEvent) {
        lock(cmdLockThree, cmdLockFour, comThree, comFour, 3);
    }

    public void cmdLockTwoClick(ActionEvent actionEvent) {
        lock(cmdLockTwo, cmdLockThree, comTwo, comThree, 2);
    }

    public void cmdLockOneClick(ActionEvent actionEvent) throws Exception {
        lock(cmdLockOne, cmdLockTwo, comOne, comTwo, 1);
    }

    private void lock(Button currentButton, Button nextButton, ChoiceBox<PlayerProfile> currentChoiceBox, ChoiceBox<PlayerProfile> nextChoiceBox, int slotNumber) {
        PlayerProfile selection = currentChoiceBox.getSelectionModel().getSelectedItem();
        boolean isNone = selection.equals(none);
        if (isNone && (slotNumber == 1 || slotNumber == 2)) {
            showError(ErrorMsg.PROFILE_NOT_VALID, Title.SETUP, false);
            currentChoiceBox.getSelectionModel().select(none);
        } else if (isNone && slotNumber == 3) {
            currentButton.setDisable(true);
            currentChoiceBox.setDisable(true);
            nextButton.setDisable(true);
            nextChoiceBox.setDisable(true);
            currentChoiceBox.setValue(none);
            nextChoiceBox.setValue(none);
        } else {

            this.players.add(selection);
            updateChoiceBoxes(selection, currentButton, currentChoiceBox, nextButton, nextChoiceBox);
        }
    }

    private void updateChoiceBoxes(PlayerProfile selection,Button currentButton, ChoiceBox<PlayerProfile> currentChoiceBox, Button nextButton, ChoiceBox<PlayerProfile> nextChoiceBox) {
        currentButton.setDisable(true);
        currentChoiceBox.setDisable(true);
        this.profiles.remove(selection);
        buttons.remove(currentButton);
        boxes.remove(currentChoiceBox);
        boxes.forEach(box -> box.setItems(profiles));
        if (nextButton != null && nextChoiceBox != null) {
            nextButton.setDisable(false);
            nextChoiceBox.setDisable(false);
        }
        /* Creates a new Instance of the selected profile with the same name so that it can be displayed after being removed from profiles */
        PlayerProfile forDisplay = new PlayerProfile(selection.getName());
        currentChoiceBox.setItems(FXCollections.observableArrayList(forDisplay));
        currentChoiceBox.setValue(forDisplay);
    }
}

