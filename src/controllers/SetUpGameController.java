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
import players.PlayerProfile;
import players.Profiles;
import styles.CarStyle;
import styles.MouseTrapStyle;
import styles.PirateStyle;
import styles.Style;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static controllers.StageController.home;
import static controllers.StageController.showError;

public class SetUpGameController implements Initializable {
    public static final  String        CARS       = "Cars";
    public static final  String        MOUSE_TRAP = "Mouse Trap";
    public static final  String        PIRATE     = "Pirate";
    private static final PlayerProfile none       = new PlayerProfile("--None--");

    private final ObservableList<PlayerProfile> profiles = FXCollections.observableArrayList(Profiles.getProfiles());
    private final ArrayList<PlayerProfile>      players  = new ArrayList<>();

    @FXML
    private ChoiceBox<PlayerProfile> comOne;
    @FXML
    private ChoiceBox<PlayerProfile> comTwo;
    @FXML
    private ChoiceBox<PlayerProfile> comThree;
    @FXML
    private ChoiceBox<PlayerProfile> comFour;

    @FXML
    private ChoiceBox                           comBoard;
    @FXML
    private ChoiceBox<String>                   comStyle;
    @FXML
    private Button                              cmdStart;
    @FXML
    private Button                              cmdBack;
    @FXML
    private Button                              cmdLockOne;
    @FXML
    private Button                              cmdLockTwo;
    @FXML
    private Button                              cmdLockThree;
    @FXML
    private Button                              cmdLockFour;
    private ArrayList<ChoiceBox<PlayerProfile>> boxes;
    private ArrayList<Button>                   buttons;
    private URL                                 location;
    private ResourceBundle                      resourceBundle;

    /**
     * Handles the quit click event
     *
     * @param actionEvent
     */
    public void cmdBackClick(ActionEvent actionEvent) {
        home();
    }

    /**
     * Handles the start click event
     *
     * @param actionEvent
     */
    public void cmdStartClick(ActionEvent actionEvent) {
        Style style = createStyle(comStyle.getSelectionModel().getSelectedItem());
        /* method call to send/create the game */
    }

    /**
     * Creates a style based on the users selection from the Styles Choice box
     *
     * @param selected The selected Style
     *
     * @return An instance of the selected style
     */
    private Style createStyle(String selected) {
        switch (selected) {
            case CARS:
                return new CarStyle();
            case MOUSE_TRAP:
                return new MouseTrapStyle();
            case PIRATE:
                return new PirateStyle();
            default:
                showError(ErrorMsg.STYLE_NOT_VALID, Title.SETUP, false);
                return null;
        }
    }

    /**
     * Initializes the window
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.location = location;
        this.resourceBundle = resources;
        initialisePlayers();
        initialiseStyles();
    }

    /**
     * Gets the profiles from the saved file and adds them to the Choice Boxes
     */
    private void initialisePlayers() {
        /*
        Adds the locking buttons and the Player choice boxes to lists
         */
        this.buttons = new ArrayList<>(Arrays.asList(cmdLockOne, cmdLockTwo, cmdLockThree, cmdLockFour));
        this.boxes = new ArrayList<>(Arrays.asList(comOne, comTwo, comThree, comFour));

        /*
        Adds the default profile none to the list, used to signify that the player slot isn't being used
         */
        profiles.add(none);
        /*
        add profiles list data to each list box, and set the selected item to the default
         */
        boxes.forEach(box -> {
            box.setItems(profiles);
            box.getSelectionModel().select(none);
        });
        /*
        disable all other player selection controls other than player one
         */
        boxes.remove(comOne);
        buttons.remove(cmdLockOne);
        boxes.forEach(box -> box.setDisable(true));
        buttons.forEach(button -> button.setDisable(true));
    }

    /**
     * Adds styles to the styles Choice Box
     */
    private void initialiseStyles() {
        this.comStyle.setItems(FXCollections.observableArrayList(CARS, MOUSE_TRAP, PIRATE));
        comStyle.setValue(CARS);
    }

    /**
     * Handles Choice Box Four's click event
     *
     * @param actionEvent
     */
    public void cmdLockFourClick(ActionEvent actionEvent) {
        lock(cmdLockFour, null, comFour, null, 4);
    }

    /**
     * Adds the locked player to the list of players for the game, disables the current selection control and enables
     * the next players seletion control.
     *
     * @param currentButton    Current Player Selection Control's lock button
     * @param nextButton       Next Player Selection Control's lock button
     * @param currentChoiceBox Current Player Selection Control's Choice Box
     * @param nextChoiceBox    Next Player Selection Control's Choice Box
     * @param slotNumber       Current Player number
     */
    private void lock(Button currentButton, Button nextButton, ChoiceBox<PlayerProfile> currentChoiceBox,
                      ChoiceBox<PlayerProfile> nextChoiceBox, int slotNumber) {
        PlayerProfile selection = currentChoiceBox.getSelectionModel().getSelectedItem();
        boolean isNone = selection.equals(none);
        /* If the user selected none for either player one or two */
        if (isNone && (slotNumber == 1 || slotNumber == 2)) {
            showError(ErrorMsg.PROFILE_NOT_VALID, Title.SETUP, false);
            currentChoiceBox.getSelectionModel().select(none);

            /* If the user selected none as player 3 */
        } else if (isNone && slotNumber == 3) {
            /* Disable both player 3 and player 4 selection controls */
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

    /**
     * Updates the profiles list, removing the selected one
     *
     * @param selection        Selected {@link PlayerProfile}
     * @param currentButton    Current Player Selection Control's lock button
     * @param nextButton       Next Player Selection Control's lock button
     * @param currentChoiceBox Current Player Selection Control's Choice Box
     * @param nextChoiceBox    Next Player Selection Control's Choice Box
     */
    private void updateChoiceBoxes(PlayerProfile selection, Button currentButton,
                                   ChoiceBox<PlayerProfile> currentChoiceBox, Button nextButton,
                                   ChoiceBox<PlayerProfile> nextChoiceBox) {
        /* Disable Current Player Selection Control */
        currentButton.setDisable(true);
        currentChoiceBox.setDisable(true);

        /*Remove Current Player Selection Controls */
        this.profiles.remove(selection);
        buttons.remove(currentButton);
        boxes.remove(currentChoiceBox);

        /* Update all other Player Selection Controls */
        boxes.forEach(box -> box.setItems(profiles));

        /*Enable next Selection Control if there is one */
        if (nextButton != null && nextChoiceBox != null) {
            nextButton.setDisable(false);
            nextChoiceBox.setDisable(false);
        }
        /* Creates a new Instance of the selected profile with the same name so that it can be displayed after being
        removed from profiles */
        PlayerProfile forDisplay = new PlayerProfile(selection.getName());
        currentChoiceBox.setItems(FXCollections.observableArrayList(forDisplay));
        currentChoiceBox.setValue(forDisplay);
    }

    /**
     * Handles Choice Box Three's click event
     *
     * @param actionEvent
     */
    public void cmdLockThreeClick(ActionEvent actionEvent) {
        lock(cmdLockThree, cmdLockFour, comThree, comFour, 3);
    }

    /**
     * Handles Choice Box Two's click event
     *
     * @param actionEvent
     */
    public void cmdLockTwoClick(ActionEvent actionEvent) {
        lock(cmdLockTwo, cmdLockThree, comTwo, comThree, 2);
    }

    /**
     * Handles Choice Box One's click event
     *
     * @param actionEvent
     */
    public void cmdLockOneClick(ActionEvent actionEvent) throws Exception {
        lock(cmdLockOne, cmdLockTwo, comOne, comTwo, 1);
    }


}

